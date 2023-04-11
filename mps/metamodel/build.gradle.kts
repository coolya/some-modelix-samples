plugins {
    kotlin("jvm")
}

val modelix_metamodel_version: String by project

dependencies {
    api("org.modelix:model-api-gen-runtime:$modelix_metamodel_version")
}
