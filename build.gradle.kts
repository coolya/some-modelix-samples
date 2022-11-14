/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/7.5/samples
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    kotlin("jvm") version "1.7.10" apply false
    kotlin("kapt") version "1.7.21" apply false
    id("com.specificlanguages.mps") version "1.5.0" apply false
    id ("com.github.node-gradle.node") version "3.2.1" apply false
}


subprojects {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/modelix/mps-rest-model-access")
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
        maven(url = "https://projects.itemis.de/nexus/content/repositories/mbeddr")

    }
}
