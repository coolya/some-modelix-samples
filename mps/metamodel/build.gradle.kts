plugins {
    kotlin("jvm")
}

val modelix_version: String by project

dependencies {
    implementation("org.modelix:metamodel-runtime:$modelix_version")
}
