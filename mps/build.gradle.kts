plugins {
    java
    id("org.modelix.model-api-gen")
}

val mps: Configuration by configurations.creating
val mps_version: String by project
val mpsDependencies: Configuration by configurations.creating
val mpsExtensions_version: String by project
dependencies {
    mps("com.jetbrains:mps:$mps_version")
    mpsDependencies("de.itemis.mps:extensions:$mpsExtensions_version")
}

val mpsDir = file("$buildDir/mps")
val extractMps by tasks.registering(Copy::class) {
    from({ mps.resolve().map { zipTree(it) } })
    into(mpsDir)
}

val dependenciesDir = file("$buildDir/dependencies")
val extractMpsDependencies by tasks.registering(Copy::class) {
    from({ mpsDependencies.resolve().map { zipTree(it) } })
    into(dependenciesDir)
}

// Generate the API classes
metamodel {
    dependsOn(extractMps)
    dependsOn(extractMpsDependencies)

    mpsHome = mpsDir

    modulesFrom(projectDir.resolve("languages"))
    modulesFrom(projectDir.resolve("solutions"))
    includeNamespace("University.Schedule.sandbox")
    includeLanguage("University.Schedule")

    modulesFrom(projectDir.resolve("build/dependencies"))
    includeLanguage("org.modelix.model.repositoryconcepts")

    kotlinDir = project(":mps:metamodel").projectDir.resolve("src/main/kotlin")

    registrationHelperName = "University.Schedule.GeneratedLanguages"
}