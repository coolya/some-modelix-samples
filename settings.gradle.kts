rootProject.name = "modelix-samples"

pluginManagement {
    val quarkus_plugin_version: String by settings
    val quarkus_plugin_id: String by settings

    val modelix_version: String by settings

    val modelix_metamodel_version: String by settings

    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://artifacts.itemis.cloud/repository/maven-mps/") }
        mavenLocal()
    }
    plugins {
        id(quarkus_plugin_id) version quarkus_plugin_version

        id("org.modelix.metamodel.gradle") version modelix_metamodel_version
    }
}

include("mps:project-modelserver-backend")
include("mps:project-mps-backend")
include("mps:metamodel")

include("rest-api-json-bulk")
include("rest-api-model-server")
include("rest-api-model-ql")

include("spa-dashboard-angular")

include("model-server")
