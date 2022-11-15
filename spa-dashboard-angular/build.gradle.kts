plugins {
    application
    kotlin("jvm")
    id ("com.github.node-gradle.node")
}

dependencies {

}

node {
    download.set(true)
    version.set("16.18.0")
}

val npmRun by tasks.creating(com.github.gradle.node.npm.task.NpmTask::class) {
    dependsOn(tasks.getByName("build"))
    args.addAll("run-script", "ng", "serve")
}