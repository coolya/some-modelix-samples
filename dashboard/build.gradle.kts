plugins {
    application
    kotlin("jvm")
}

val ktor_version : String by project
val rest_access_version: String by project

dependencies {
    implementation(project(":mps:solutions:University.Schedule.api"))
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("org.modelix.mps-rest-model-access:model-client:1.1")
    implementation("org.modelix.mps-rest-model-access:model-api:1.0")
}

application {
    mainClass.set("org.modelix.sample.dashboard.ApplicationKt")
}