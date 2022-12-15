plugins {
    kotlin("jvm")
}

val modelix_metamodel_version: String by project

dependencies {
    implementation("org.modelix:metamodel-runtime:$modelix_metamodel_version")
}
