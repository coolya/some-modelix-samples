package org.modelix.sample.restapimodelql

import com.typesafe.config.ConfigFactory
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.locations.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.routing.*
import org.modelix.sample.restapijsonbulk.models.apis.ModelQLAPI

suspend fun main() {
    val config = ConfigFactory.load()
    val host = config.getString("mps.model-ql.host")
    val port = config.getInt("mps.model-ql.port")
    val models = config.getStringList("mps.model-ql.models")

    // initialize the client which handles the connection to the
    // lightModelServer plugin running in our MPS instance
    val lightModelClientWrapper = LightModelClientWrapper(host, port, models.first())
    lightModelClientWrapper.createConnection()


    // start the embedded server to serve the API
    val deploymentPort = config.getInt("ktor.deployment.port")
    embeddedServer(Netty, port = deploymentPort) {
        install(CORS) {
            anyHost()
        }
        install(DefaultHeaders)
        install(ContentNegotiation) { gson() }
        install(AutoHeadResponse)
        install(Locations)
        install(Routing) {
            // the BulkApi provides the routs defined in our OpenAPI specification
            ModelQLAPI(lightModelClientWrapper)
        }
    }.start(wait = true)
}