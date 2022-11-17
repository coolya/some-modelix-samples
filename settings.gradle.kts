rootProject.name = "modelix-samples"

pluginManagement {
    val quarkus_plugin_version: String by settings
    val quarkus_plugin_id: String by settings
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
    plugins {
        id(quarkus_plugin_id) version quarkus_plugin_version
    }
}

include("mps")
include("mps:solutions:University.Schedule.api")

include("rest-api-json-bulk")
include("rest-api-model-server")

include("spa-dashboard-angular")