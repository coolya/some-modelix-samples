package org.modelix.sample.dashboard

import MPSRemoteClient
import University.Schedule.api.gen.University_Schedule
import University.Schedule.api.gen.jetbrains_mps_lang_core
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.modelix.model.lazy.IConceptReferenceSerializer
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

    if(models.size != modelsToLoad.size) {
        logger.error("Not all models available!")
        logger.error("""Missing models: ${models.filterNot { modelsToLoad.map { it.name }.contains(it) }.joinToString()}""")
        return
    }

    embeddedServer(Netty, port = 8080) {

    }.start(wait = true)
}

fun registerLanguages() {
    MPSLanguageRegistry.register(jetbrains_mps_lang_core.INSTANCE)
    MPSLanguageRegistry.register(University_Schedule.INSTANCE)
    IConceptReferenceSerializer.register(MPSLanguageRegistry())
}