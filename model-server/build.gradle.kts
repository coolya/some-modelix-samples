

val startModelServer by tasks.creating(){
    exec {
        commandLine("ls", "-la")
//        commandLine("docker", "-d", "run", "--rm", "-p", "28101:28101", "-v", " \$(realpath modelserver.dump):/tmp/modelserver.dump:ro", "-d", "modelix/modelix-model:1.3.2", "java", "-XX:MaxRAMPercentage=85", "-Djdbc.url=\$jdbc_url", "-cp", "\"model-server/build/libs/*\"", "org.modelix.model.server.Main", "-inmemory", "-dumpin", "/tmp/modelserver.dump" )
    }
}
