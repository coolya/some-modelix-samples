plugins {
    application
    kotlin("jvm")
    id("org.openapi.generator") version "6.2.1"
}

val ktor_version : String by project
val json_bulk_access_version: String by project
val api_gen_version: String by project

val openApiFile = layout.projectDirectory.file("../openapi/openapi.yaml")

dependencies {
    implementation(project(":mps:solutions:University.Schedule.api"))

    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("io.ktor:ktor-server-default-headers:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-auto-head-response:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("io.ktor:ktor-server-locations:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")

    testImplementation("junit:junit:4.13.2")

    implementation("org.modelix.mps-json-bulk-model-access:model-client:$json_bulk_access_version")
    implementation("org.modelix.mps-json-bulk-model-access:model-api:$json_bulk_access_version")
    implementation("org.modelix.mps.api-gen:runtime:$api_gen_version")
}

val basePackage = "org.modelix.sample.restapijsonbulk"

// Use the OpenAPI generator to generate data classes representing the REST response data types.
// Unfortunately, other generated artifacts cannot be used, because the generator still assumes
// ktor 1.x and doesn't have an interfaceOnly mode such as in the Quarkus example.
openApiGenerate {
    generatorName.set("kotlin-server")
    inputSpec.set(openApiFile.toString())
    outputDir.set("$buildDir/openapi-generator")
    packageName.set(basePackage)
    // The OpenAPI generator gradle plugin is strange. According to the documentation of the generator itself,
    // one has to specify `true` to generate all models. However, the plugin does something such that `true`
    // is assumed to be the name of a specific model to generate only then. Strangely, specifying an empty
    // string configures the plugin in such a way that all available models are generated and nothing else.
    globalProperties.set(
        mapOf(
            "models" to "",
        )
    )
    configOptions.set(
        mapOf(
            "library" to "ktor",
        )
    )
}

// Ensure that the OpenAPI generator runs before starting to compile
tasks.named("processResources") {
    dependsOn("openApiGenerate")
}
tasks.named("compileKotlin") {
    dependsOn("openApiGenerate")
}

java.sourceSets.getByName("main").java.srcDir(file("$buildDir/openapi-generator/src/main/kotlin"))

application {
    mainClass.set("org.modelix.sample.restapijsonbulk.ApplicationKt")
}
