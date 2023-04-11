plugins {
    kotlin("jvm")
}

val modelix_core_version: String by project

dependencies {
    api("org.modelix:model-api-gen-runtime:$modelix_core_version")
}
