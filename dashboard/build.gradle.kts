plugins {
    application
    kotlin("jvm")
    id ("com.github.node-gradle.node")
}

val ktor_version : String by project
val json_bulk_access_version: String by project
val api_gen_version: String by project

dependencies {
    implementation(project(":mps:solutions:University.Schedule.api"))
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("org.modelix.mps-json-bulk-model-access:model-client:$json_bulk_access_version")
    implementation("org.modelix.mps-json-bulk-model-access:model-api:$json_bulk_access_version")
    implementation("org.modelix.mps.api-gen:runtime:$api_gen_version")
}

node {
    download.set(true)

    // Version of node to download and install (only used if download is true)
    // It will be unpacked in the workDir
    version.set("16.14.2")
}

val generateStyles by tasks.creating(com.github.gradle.node.npm.task.NpxTask::class) {
    dependsOn(tasks.getByName("yarn"))
    inputs.file("tailwind.config.js").withPropertyName("config")
    inputs.files(fileTree(projectDir).include("src/**/*.{kt,js,html,css}"))
    command.set("tailwindcss")
    args.addAll("-i", "./src/main/styles/styles.css", "-o", "./src/main/resources/styles/styles.css")
}

tasks.getByName("processResources").dependsOn(generateStyles)

application {
    mainClass.set("org.modelix.sample.dashboard.ApplicationKt")
}
