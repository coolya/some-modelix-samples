plugins {
    kotlin("jvm") version "1.7.10" apply false
    kotlin("kapt") version "1.7.10" apply false
    id("com.specificlanguages.mps") version "1.5.0" apply false
    id ("com.github.node-gradle.node") version "3.2.1" apply false
}

subprojects {
    repositories {
        mavenCentral()

        maven { url = uri("https://artifacts.itemis.cloud/repository/maven-mps/") }

        maven {
             url = uri("https://maven.pkg.github.com/modelix/mps-json-bulk-model-access")
             credentials {
                 username = project.findProperty("gpr.user")?.toString() ?: System.getenv("USERNAME")
                 password = project.findProperty("gpr.key")?.toString() ?: System.getenv("TOKEN")
             }
        }

        maven {
            url = uri("https://maven.pkg.github.com/modelix/api-gen")
            credentials {
                username = project.findProperty("gpr.user")?.toString() ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key")?.toString() ?: System.getenv("TOKEN")
            }
        }
    }
}
