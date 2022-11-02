plugins {
    java
    `java-library`
}

val api_gen_version: String by project
val model_api_version: String by project
val json_bulk_access_version: String by project

repositories {
    maven { url = uri("https://artifacts.itemis.cloud/repository/maven-mps/") }
    maven {
        url = uri("https://maven.pkg.github.com/modelix/api-gen")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
    mavenCentral()
}

dependencies {
    implementation("org.modelix.mps.api-gen:runtime:$api_gen_version")
    implementation("org.modelix:model-api:1.3.2")
    implementation("org.modelix.mps-json-bulk-model-access:model-client:$json_bulk_access_version")
    implementation("org.modelix.mps-json-bulk-model-access:model-api:$json_bulk_access_version")
    implementation("org.modelix:model-api-jvm:$model_api_version")
}

sourceSets {
    main {
        java {
            srcDir("source_gen")
        }
    }
}

