plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.micronaut.application") version "3.6.3"

}

//group = "org.modelix.samples.some"
val json_bulk_access_version: String by project
val api_gen_version: String by project


dependencies {
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.security:micronaut-security-annotations")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.serde:micronaut-serde-jsonp")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
    implementation("org.modelix.mps.api-gen:runtime:$api_gen_version")
    implementation("org.modelix.mps-json-bulk-model-access:model-client:$json_bulk_access_version")
    implementation("org.modelix.mps-json-bulk-model-access:model-api:$json_bulk_access_version")
    implementation(project(":mps:solutions:University.Schedule.api"))
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    kapt("io.micronaut:micronaut-inject-java")
}


application {
    mainClass.set("org.modelix.samples.some.Application")
}

java {
    sourceCompatibility = JavaVersion.toVersion("11")
    targetCompatibility = JavaVersion.toVersion("11")
}
graalvmNative {
    toolchainDetection.set(false)
}

micronaut {
    runtime("jetty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("org.modelix.samples.some.*")
    }
}


configurations.all {
    resolutionStrategy.dependencySubstitution {
        substitute(module("io.micronaut:micronaut-jackson-databind"))
                .using(module("jakarta.json.bind:jakarta.json.bind-api:2.0.0"))
        substitute(module("io.micronaut:micronaut-jackson-core"))
                .using(module("io.micronaut.serde:micronaut-serde-jsonp:1.1.0"))
        substitute(module("io.micronaut:micronaut-jackson-databind"))
                .using(module("io.micronaut.serde:micronaut-serde-jackson:1.1.0"))
    }
}

