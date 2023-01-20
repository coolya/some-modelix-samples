plugins {
    kotlin("jvm")
}

val modelix_metamodel_version: String by project

dependencies {
    api("org.modelix:metamodel-runtime:$modelix_metamodel_version")
}
