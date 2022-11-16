plugins {
    kotlin("jvm")
    kotlin("plugin.allopen") version "1.7.20"
    id("io.quarkus")
    id("org.openapi.generator") version "6.2.1"
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val ktor_version: String by project
val api_gen_version: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("org.modelix:model-client:1.3.2")
    implementation("org.modelix.mps.api-gen:runtime:$api_gen_version")
    implementation(project(":mps:solutions:University.Schedule.api"))
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

val basePackage = "org.modelix.sample"

val openApiFile = layout.projectDirectory.file("../openapi/openapi.yaml")

// We let the Gradle OpenAPI generator plugin build data classes and API interfaces based on the provided
// OpenAPI specification. That way, the code in this example is force to stay in sync with the API specification.
openApiGenerate {
    generatorName.set("kotlin-server")
    inputSpec.set(openApiFile.toString())
    outputDir.set("$buildDir/openapi-generator")
    apiPackage.set(basePackage)
    modelPackage.set(basePackage)
    configOptions.set(
        mapOf(
            "library" to "jaxrs-spec",
            "interfaceOnly" to "true",
        )
    )
}

// Provide the OpenAPI definition declared up-front to the Swagger UI served by Quarkus.
// Cf. https://quarkus.io/guides/openapi-swaggerui#loading-openapi-schema-from-static-files
tasks.register<Copy>("installOpenAPISchema") {
    from(openApiFile)
    into(layout.buildDirectory.dir("classes/kotlin/main/META-INF"))
}

// Ensure that the OpenAPI generator runs before starting to compile
tasks.named("processResources") {
    dependsOn("openApiGenerate")
    dependsOn("installOpenAPISchema")
}
tasks.named("compileKotlin") {
    dependsOn("openApiGenerate")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

java.sourceSets.getByName("main").java.srcDir(file("$buildDir/openapi-generator/src/main/kotlin"))

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}
