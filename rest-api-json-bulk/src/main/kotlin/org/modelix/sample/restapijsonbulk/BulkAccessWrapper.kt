import University.Schedule.GeneratedLanguages
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.mps.rest.model.access.api.ModelView
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = LoggerFactory.getLogger("BulkAccessWrapper")

object BulkAccessWrapper {

    private lateinit var client: MPSRemoteClient
    private lateinit var modelsToLoad: List<ModelView>

    suspend fun initialize(host: String, port: Int, models: List<String>) {

        GeneratedLanguages.registerAll()

        client = MPSRemoteClient(host, port)
        modelsToLoad = client.getViewModels().filter { models.contains(it.name) }

        if (models.size != modelsToLoad.size) {
            logger.error("Not all models available!")
            logger.error(
                    """Missing models: ${
                        models.filterNot { modelsToLoad.map { it.name }.contains(it) }.joinToString()
                    }"""
            )
            return
        }
    }

    val loadRoots = suspend { modelsToLoad.map { it.modelId }.let { client.loadModelAreas(it) }.map { it.getRoot() } }

    val resolve: suspend (INodeReference) -> INode? = { ref: INodeReference ->
        // The name "loadModelSafe" is kinda misleading. What this function is doing, is to run the closure
        // that is passed to it and catch any ModelNotLoadedException during the execution and load the missing model.
        // That way we don't need to load the model, where the reference is pointing to, explicitly.
        client.loadModelSafe {
            client.resolveReference(ref)
        }
    }
}