package org.modelix.sample.restapimodelql

import University.Schedule.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import jetbrains.mps.lang.core.N_BaseConcept
import kotlinx.coroutines.delay
import org.modelix.client.light.LightModelClient
import org.modelix.client.light.WebsocketConnection
import org.modelix.metamodel.typed
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.model.repositoryconcepts.N_Module
import org.modelix.model.repositoryconcepts.N_Repository
import org.modelix.model.server.api.buildModelQuery
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

val logger: Logger = LoggerFactory.getLogger("org.modelix.sample.restapimodelql.ModelServerLightWrapper")

class ModelServerLightWrapper {

    lateinit var MPS_MODEL_NAME: String
    private lateinit var WS_CONNECTION: String

    lateinit var globalModelClient: LightModelClient

    constructor(host: String = "localhost", port: Int = 48302, models: String) {
        GeneratedLanguages.registerAll()
        WS_CONNECTION = "ws://$host:$port/ws"
        MPS_MODEL_NAME = models
    }

    suspend fun createConnection() {
        // we require a http client with WS support for the connection
        val modelClient = LightModelClient(WebsocketConnection(HttpClient(CIO) { install(WebSockets) }, WS_CONNECTION))

        logger.info("Connecting to light model-server at ${WS_CONNECTION}")
        kotlinx.coroutines.withTimeout(5.seconds) {
            while (true) {
                if (modelClient != null) {
                    break
                }
                delay(10.milliseconds)
            }
        }
        globalModelClient = modelClient
        logger.info("Connection successful")

        // the modelQL query to send
        globalModelClient.changeQuery(buildModelQuery {
            root {
                children("modules") {
                    whereProperty("name").contains(MPS_MODEL_NAME)
                    children("models") {
                        children("rootNodes") {
                            descendants { }
                        }
                    }
                }
            }
        })

        // TODO: to geht the actual query JSON content string use:
        // MessageFromClient(query = qqq).toJson()
        // {"query":{"queries":[{"type":"root"}]}} ...
    }

    suspend fun getAllLectures(): List<N_Lecture> {
        //TODO: can we avoid this cast by using generics in runAsyncRead?
        return runAsyncRead(loadLectures) as List<N_Lecture>
    }

    suspend fun getAllRooms(): List<N_Room> {
        return runAsyncRead(loadRooms) as List<N_Room>
    }

    suspend fun runAsyncRead(loadStuff: (node: N_Repository) -> List<N_BaseConcept>?): List<N_BaseConcept>? {
        var result: List<N_BaseConcept>? = null
        kotlinx.coroutines.withTimeout(5.seconds) {
            while (true) {
                globalModelClient!!.checkException()
                val node = globalModelClient!!.runRead { globalModelClient!!.getRootNode() }
                if (node != null && globalModelClient!!.runRead { node.isValid }) {
                    globalModelClient!!.runRead {
                        result = loadStuff(node.typed<N_Repository>())
                    }
                    break
                }
                delay(10.milliseconds)
            }
        }
        return result
    }

    private var loadRooms: (N_Repository) -> List<N_Room>? = Any@{ node: N_Repository ->
        return@Any node.unwrap().allChildren.filter { it.isValid }.map { it.typed<N_Module>() }.flatMap { it.models }.flatMap { it.rootNodes }.filterIsInstance<N_Rooms>().flatMap { it.rooms }

//        var universitySandboxModule: N_Module = node.allChildren.filter { it.isValid && it.getPropertyValue("name")?.contains(MPS_MODEL_NAME)!! }.first().typed<N_Module>()
//        if (universitySandboxModule != null) {
////                println("node $node")
////                println("universityModule $universitySandboxModule $node")
//
//            var rooms: List<C_Room>? = universitySandboxModule.models.find { it.name.equals(MPS_MODEL_NAME) }?.rootNodes?.filter { it.instanceOf(C_Rooms) }?.flatMap { it.unwrap().allChildren as List<C_Room> }
////                var lectures: List<C_Lecture>? = universitySandboxModule.models.find { it.name.equals(MPS_MODULE_NAME) }?.rootNodes?.filter { it.instanceOf(C_Courses) }?.flatMap { it.unwrap().allChildren as List<C_Lecture> }
////
////                println("rooms are: $rooms")
////                println("lecutres are: $lectures")
//            return@Any rooms
//
//        } else {
//            return@Any null
//        }
    }

    private var loadLectures: (N_Repository) -> List<N_Lecture>? = Any@{ node: N_Repository ->
        //todo replace flatmap with .models in 1.4.18
        return@Any node.unwrap().allChildren.filter { it.isValid }.map { it.typed<N_Module>() }.flatMap { it.models }.flatMap { it.rootNodes }.filterIsInstance<N_Courses>().flatMap { it.lectures }
    }

    val resolve2: suspend (String) -> N_BaseConcept? = Any@{ ref: String ->
        return@Any globalModelClient!!.runRead {globalModelClient!!.getNodeIfLoaded(ref)?.typed() as N_BaseConcept}
    }
}