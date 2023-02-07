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
import org.modelix.model.repositoryconcepts.N_Module
import org.modelix.model.repositoryconcepts.N_Repository
import org.modelix.model.repositoryconcepts.models
import org.modelix.model.repositoryconcepts.rootNodes
import org.modelix.model.server.api.buildModelQuery
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

val logger: Logger = LoggerFactory.getLogger("org.modelix.sample.restapimodelql.ModelServerLightWrapper")

class LightModelClientWrapper(host: String = "localhost", port: Int = 48302, modelNameSubstring: String) {

    private var modelNameSubstring: String = modelNameSubstring
    private var wsConnection: String
    private lateinit var lightModelClient: LightModelClient

    init {
        GeneratedLanguages.registerAll()
        wsConnection = "ws://$host:$port/ws"
    }

    suspend fun createConnection() {

        // we require a http client with WS support for the connection
        logger.info("Connecting to light model-server at $wsConnection")
        this.lightModelClient = LightModelClient(WebsocketConnection(HttpClient(CIO) { install(WebSockets) }, wsConnection))

        // the modelQL query
        this.lightModelClient.changeQuery(buildModelQuery {
            // we traverse from the root (a repository)
            root {
                // to all modules
                children("modules") {
                    // selecting the ones that contain our desired model name
                    whereProperty("name").contains(modelNameSubstring)
                    children("models") {
                        // and extract all root nodes, so Rooms and Courses, and their descendants
                        children("rootNodes") {
                            descendants { }
                        }
                    }
                }
            }
        })
        logger.info("Connection successful")
    }

    suspend fun getAllLectures(): List<N_Lecture> {
        // TODO: can we avoid this cast by using generics in runAsyncRead?
        logger.info("Loading all lectures")
        return runAsyncRead(loadLectures) as List<N_Lecture>
    }

    suspend fun getAllRooms(): List<N_Room> {
        logger.info("Loading all rooms")
        return runAsyncRead(loadRooms) as List<N_Room>
    }

    // TODO: This or parts of this should move to the LightModelClient itself. Replace it here once this code was migrated, e.g. into `client.waitForRootNode()`
    private suspend fun runAsyncRead(givenFunction: (node: N_Repository) -> List<N_BaseConcept>?): List<N_BaseConcept>? {
        var result: List<N_BaseConcept>? = null
        kotlinx.coroutines.withTimeout(5.seconds) {
            while (true) {
                lightModelClient.checkException()
                val node = lightModelClient.runRead { lightModelClient.getRootNode() }
                if (node != null && lightModelClient.runRead { node.isValid }) {
                    lightModelClient.runRead {
                        logger.info("Reading repository")
                        result = givenFunction(node.typed<N_Repository>())
                    }
                    break
                }
                delay(10.milliseconds)
            }
        }
        return result
    }

    private var loadRooms: (N_Repository) -> List<N_Room>? = Any@{ node: N_Repository ->
        return@Any getAllRootNodesOfTypeInRepository<N_Rooms>(node).rooms
    }

    private var loadLectures: (N_Repository) -> List<N_Lecture>? = Any@{ node: N_Repository ->
        return@Any getAllRootNodesOfTypeInRepository<N_Courses>(node).lectures
    }

    private inline fun <reified R> getAllRootNodesOfTypeInRepository(node: N_Repository) =
            node.unwrap().allChildren.filter { it.isValid }.map { it.typed<N_Module>() }.models.rootNodes.filterIsInstance<R>()

    val resolveNodeIdToConcept: suspend (String) -> N_BaseConcept? = Any@{ ref: String ->
        logger.info("Resolving node $ref")
        return@Any lightModelClient.runRead {
            lightModelClient.getNodeIfLoaded(ref)?.typed()?.let { it as N_BaseConcept }
        }
    }

    fun <T> runRead(body: () -> T): T {
        return lightModelClient.runRead {
            body()
        }
    }

}