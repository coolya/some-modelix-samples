pluginManagement {
    val quarkusPluginVersion: String by settings
    val quarkusPluginId: String by settings
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion
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
