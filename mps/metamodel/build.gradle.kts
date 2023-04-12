plugins {
    kotlin("jvm")
}

val modelix_platform_version: String by project

dependencies {
    api(enforcedPlatform("org.modelix:platform:$modelix_platform_version"))
    api("org.modelix:model-api-gen-runtime")
}
