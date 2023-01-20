package org.modelix.sample.restapimodelql

import ModelServerLightWrapper
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

    // initialize the object which handles the connection to the
    // mps-json-bulk-access plugin running in our MPS instance
    val mslw =  ModelServerLightWrapper(host, port, models.first())

//    val mslw = ModelServerLightWrapper(models)
    mslw.createConnection()


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
            // the BulkApi provides the routs defined in our openapi specification
            ModelQLAPI(mslw)
        }
    }.start(wait = true)
}