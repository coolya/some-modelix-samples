package org.modelix.sample.dashboard

import MPSRemoteClient
import ReferenceSerializer
import University.Schedule.api.gen.University_Schedule
import University.Schedule.api.gen.jetbrains_mps_lang_core
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import loadModelSafe
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.model.lazy.INodeReferenceSerializer
import org.modelix.mps.apigen.runtime.MPSLanguageRegistry
import org.slf4j.LoggerFactory

val logger = LoggerFactory.getLogger("main")

suspend fun main() {
    registerLanguages()
    val host = System.getenv().getOrDefault("MPS_HOST", "localhost")
    val port = System.getenv().getOrDefault("MPS_PORT", "63320")
    val models = System.getenv().getOrDefault("MODELS_TO_LOAD", "University.Schedule.sandbox").split(",")
    val client = MPSRemoteClient(host, port.toInt())
    val modelsToLoad = client.getViewModels().filter { models.contains(it.name) }

    if (models.size != modelsToLoad.size) {
        logger.error("Not all models available!")
        logger.error(
            """Missing models: ${
                models.filterNot { modelsToLoad.map { it.name }.contains(it) }.joinToString()
            }"""
        )
        return
    }

    val loadRoots = suspend { modelsToLoad.map { it.modelId }.let { client.loadModelAreas(it) }.map { it.getRoot() } }
    val resolve: suspend (INodeReference) -> INode? =  {  ref: INodeReference ->
        // The name "loadModelSafe" is kinda misleading. What this function is doing, is to run the closure that is passed
        // that is passed to it and catch any ModelNotLoadedException during the execution and load the missing model.
        // That way we don't need to load the model, where the reference is pointing to, explicitly.
        client.loadModelSafe {
            client.resolveReference(ref)
        }
    }
    INodeReferenceSerializer.register(ReferenceSerializer.Companion)
    embeddedServer(Netty, port = 8090) {
        configureRouting(loadRoots, resolve)
    }.start(wait = true)
}

fun registerLanguages() {
    MPSLanguageRegistry.register(jetbrains_mps_lang_core.INSTANCE)
    MPSLanguageRegistry.register(University_Schedule.INSTANCE)
}
