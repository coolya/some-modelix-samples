plugins {
    application
    kotlin("jvm")
}


val ktor_version : String by project
val json_bulk_access_version: String by project
val api_gen_version: String by project

dependencies {
    implementation(project(":mps:solutions:University.Schedule.api"))

    implementation("ch.qos.logback:logback-classic:1.2.1")
    implementation("io.ktor:ktor-server-default-headers:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-auto-head-response:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("io.ktor:ktor-server-locations:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")

    testImplementation("junit:junit:4.13.2")

    implementation("org.modelix.mps-json-bulk-model-access:model-client:$json_bulk_access_version")
    implementation("org.modelix.mps-json-bulk-model-access:model-api:$json_bulk_access_version")
    implementation("org.modelix.mps.api-gen:runtime:$api_gen_version")
}

application {
    mainClass.set("org.modelix.sample.restapijsonbulk.AppMainKt")
}
//mainClassName = "org.openapitools.server.AppMainKt"


//apply(plugin = "com.github.johnrengelman.shadow")
//
//sourceCompatibility = 1.8
//
//compileKotlin {
//    kotlinOptions.jvmTarget = "1.8"
//}
//
//compileTestKotlin {
//    kotlinOptions.jvmTarget = "1.8"
//}
//
//shadowJar {
//    baseName = "kotlin-server"
//    classifier = null
//    version = null
//}

