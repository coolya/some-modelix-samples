import University.Schedule.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import jetbrains.mps.lang.core.N_BaseConcept
import kotlinx.coroutines.delay
import org.modelix.client.light.LightModelClient
import org.modelix.client.light.WebsocketConnection
import org.modelix.metamodel.ITypedConcept
import org.modelix.metamodel.instanceOf
import org.modelix.metamodel.typed
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.model.repositoryconcepts.N_Model
import org.modelix.model.repositoryconcepts.N_Module
import org.modelix.model.repositoryconcepts.N_Repository
import org.modelix.model.server.api.buildModelQuery
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.LinkedList
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

val logger: Logger = LoggerFactory.getLogger("ModelServerLightWrapper")

class ModelServerLightWrapper {

    lateinit var MPS_MODEL_NAME: String
    private var WS_CONNECTION: String
    var connected = false

    lateinit var globalModelClient: LightModelClient


    constructor(host: String = "localhost", port: Int = 48302, models: String) {
        GeneratedLanguages.registerAll()
        WS_CONNECTION = "ws://$host:$port/ws"
        MPS_MODEL_NAME = models
    }

    suspend fun createConnection() {
        connected = false
        val httpClient = HttpClient(CIO) {
            install(WebSockets)
        }

        val modelClient = LightModelClient(WebsocketConnection(httpClient, WS_CONNECTION))
        println("Connecting to WS")
        kotlinx.coroutines.withTimeout(5.seconds) {
            while (true) {
                if (modelClient != null) {
                    break
                }
                delay(10.milliseconds)
            }
        }
        println(" ... connected!")

        modelClient.changeQuery(buildModelQuery {
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

        globalModelClient = modelClient

        // get query content
        // MessageFromClient(query = qqq).toJson()
        // {"query":{"queries":[{"type":"root"}]}}
    }
suspend fun getAllLectures(): List<N_Lecture> {
        var lol = runAsyncRead(loadLectures)
        return lol as List<N_Lecture>
    }

//    suspend fun getAllRooms(): List<N_BaseConcept>? {
//        return runAsyncRead(loadRooms)
//    }

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

    var loadRooms: (INode) -> List<C_Room>? = Any@{ node: INode ->

        var universitySandboxModule: N_Module = node.allChildren.filter { it.isValid && it.getPropertyValue("name")?.contains(MPS_MODEL_NAME)!! }.first().typed<N_Module>()

        if (universitySandboxModule != null) {
//                println("node $node")
//                println("universityModule $universitySandboxModule $node")

            var rooms: List<C_Room>? = universitySandboxModule.models.find { it.name.equals(MPS_MODEL_NAME) }?.rootNodes?.filter { it.instanceOf(C_Rooms) }?.flatMap { it.unwrap().allChildren as List<C_Room> }
//                var lectures: List<C_Lecture>? = universitySandboxModule.models.find { it.name.equals(MPS_MODULE_NAME) }?.rootNodes?.filter { it.instanceOf(C_Courses) }?.flatMap { it.unwrap().allChildren as List<C_Lecture> }
//
//                println("rooms are: $rooms")
//                println("lecutres are: $lectures")
            return@Any rooms

        } else {
            return@Any null
        }
    }

    var loadLectures: (N_Repository) -> List<N_Lecture>? = Any@{ node: N_Repository ->
        //todo replace flatmap with .models in 1.4.18
        var universitySandboxModule: List<N_Lecture> = node.unwrap().allChildren.filter { it.isValid }.map { it.typed<N_Module>() }.flatMap { it.models }.flatMap { it.rootNodes }.filterIsInstance<N_Courses>().flatMap { it.lectures }

        return@Any universitySandboxModule
    }


    var loadRoots: suspend () -> List<INode> = Any@{ ->
        var result: List<INode>? = null
        kotlinx.coroutines.withTimeout(5.seconds) {
            while (true) {
                globalModelClient!!.checkException()
                val universitySandboxModule = globalModelClient!!.runRead { globalModelClient!!.getRootNode()?.allChildren?.filter { it.isValid && it.getPropertyValue("name")?.contains(MPS_MODEL_NAME)!! }?.first()?.typed<N_Module>() }
                if (universitySandboxModule != null) {
                    result = globalModelClient!!.runRead{universitySandboxModule.models.find { it.name.equals(MPS_MODEL_NAME) }?.rootNodes?.map { it.unwrap() }?.toList()}
                    break
                }
                delay(10.milliseconds)
            }
        }
        return@Any result!!
    }

    val resolve: suspend (INodeReference) -> INode? = Any@{ ref: INodeReference ->
        var result: INode? = null
        kotlinx.coroutines.withTimeout(5.seconds) {
            while (true) {
                globalModelClient!!.checkException()
                //todo use getNodeIfLoaded
                val node = globalModelClient!!.runRead { globalModelClient!!.getRootNode() }
                if (node != null && globalModelClient!!.runRead { node.isValid }) {
                    result = node
                    break
                }
                delay(10.milliseconds)
            }
        }
        return@Any result
    }
}