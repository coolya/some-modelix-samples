plugins {
    java
    `java-library`
}

val api_gen_version: String by project
val model_api_version: String by project


repositories {
    maven {
        url = uri("https://maven.pkg.github.com/modelix/api-gen")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
    maven { url = uri("https://artifacts.itemis.cloud/repository/maven-mps/") }
    mavenCentral()
}

dependencies {
    api("org.modelix.mps.api-gen:runtime:$api_gen_version")
}

sourceSets {
    main {
        java {
            srcDir("source_gen")
        }
    }
}

