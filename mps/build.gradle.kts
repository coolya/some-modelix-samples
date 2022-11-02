import de.itemis.mps.gradle.BuildLanguages

plugins {
    java
}

buildscript {
    repositories {
        maven { url = uri("https://artifacts.itemis.cloud/repository/maven-mps/") }
        mavenCentral()
    }

    dependencies {
        classpath("de.itemis.mps:mps-gradle-plugin:1.7.288.4ea765f")
    }
}
repositories {
    mavenCentral()
}

val api_gen_version: String by project
val json_bulk_access_version: String by project
val mps_version: String by project
val mpsExtensions_version: String by project

val mps: Configuration by configurations.creating
val buildDependencies: Configuration by configurations.creating
val mpsDependencies: Configuration by configurations.creating

dependencies {
    buildDependencies("org.apache.ant:ant-junit:1.10.12")
    mps("com.jetbrains:mps:$mps_version")
    mpsDependencies("de.itemis.mps:extensions:$mpsExtensions_version")
    mpsDependencies("org.modelix.mps.api-gen:mps-plugin:$api_gen_version")
    mpsDependencies("org.modelix.mps-json-bulk-model-access:mps-plugin:$json_bulk_access_version")
}

val mpsDir = file("$buildDir/mps")
val artifactsDir = file("$buildDir/artifacts")
val dependenciesDir = file("$buildDir/dependencies")

val extractMps by tasks.registering(Copy::class) {
    from({ mps.resolve().map { zipTree(it) } })
    into(mpsDir)
}

val extractMpsDependencies by tasks.registering(Copy::class) {
    from({ mpsDependencies.resolve().map { zipTree(it) } })
    into(dependenciesDir)
}

fun antVar(name: String, value: String)  = "-D$name=$value"

ext["itemis.mps.gradle.ant.defaultScriptArgs"] =
    listOf(
        antVar("mps_home", mpsDir.absolutePath),
        antVar("artifacts_home", artifactsDir.absolutePath),
        antVar("mps.generator.skipUnmodifiedModels", "true"),
        antVar("sample.home", projectDir.absolutePath),
        antVar("dependencies.home", dependenciesDir.absolutePath)
    )
ext["itemis.mps.gradle.ant.defaultScriptClasspath"] = buildDependencies.fileCollection { true }

val setup by tasks.registering {
    group = "setup"
    description = "Download and extract MPS and the projects MPS dependencies."
    dependsOn(extractMps)
    dependsOn(extractMpsDependencies)
}

val buildLanguages by tasks.registering(BuildLanguages::class) {
    group = "build"
    description = "Build all languages in the MPS project"
    script = "$projectDir/build.xml"
    inputs.file(file("$projectDir/build.xml"))
    inputs.files(fileTree("$projectDir/solutions").include("**/*.mps", "**/*.msd")).withPropertyName("mps-solution")
    inputs.files(fileTree("$projectDir/languages").include("**/*.mps", "**/*.msd")).withPropertyName("mps-languages")
    outputs.dir("$projectDir/languages/University.Schedule/source_gen")
    dependsOn(setup)
}


val genApi by tasks.registering(BuildLanguages::class) {
    group = "build"
    description = "Generate the API classes"
    script = "$projectDir/build-gen-api.xml"
    inputs.file(file("$projectDir/build-gen-api.xml"))
    inputs.files(fileTree("$projectDir/solutions").include("**/*.mps", "**/*.msd")).withPropertyName("mps-solution")
    outputs.dir("$projectDir/solutions/University.Schedule.api/source_gen")
    dependsOn(buildLanguages)
}

tasks.getByName("build").dependsOn(buildLanguages)