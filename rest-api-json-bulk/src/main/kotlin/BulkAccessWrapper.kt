import University.Schedule.api.gen.University_Schedule
import University.Schedule.api.gen.jetbrains_mps_lang_core
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.model.lazy.INodeReferenceSerializer
import org.modelix.mps.apigen.runtime.MPSLanguageRegistry
import org.modelix.mps.rest.model.access.api.ModelView
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = LoggerFactory.getLogger("BulkAccessWrapper")

object BulkAccessWrapper {

    private lateinit var client: MPSRemoteClient
    private lateinit var modelsToLoad: List<ModelView>

    suspend fun initialize(host: String, port: Int, models: List<String>){
        registerLanguages()

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
        INodeReferenceSerializer.register(ReferenceSerializer.Companion)
    }

    private fun registerLanguages() {
        MPSLanguageRegistry.register(jetbrains_mps_lang_core.INSTANCE)
        MPSLanguageRegistry.register(University_Schedule.INSTANCE)
    }

    val loadRoots = suspend { modelsToLoad.map { it.modelId }.let { client.loadModelAreas(it) }.map { it.getRoot() } }

    val resolve: suspend (INodeReference) -> INode? =  { ref: INodeReference ->
        // The name "loadModelSafe" is kinda misleading. What this function is doing, is to run the closure that is passed
        // that is passed to it and catch any ModelNotLoadedException during the execution and load the missing model.
        // That way we don't need to load the model, where the reference is pointing to, explicitly.
        client.loadModelSafe {
            client.resolveReference(ref)
        }
    }
}