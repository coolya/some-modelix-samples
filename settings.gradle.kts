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

rootProject.name = "some-modelix-samples"

include("rest-api-json-bulk")
include("dashboard")
include("mps")
include("mps:solutions:University.Schedule.api")
include("rest-api")
include("spa-dashboard-angular")
include("rest-api-model-server")
