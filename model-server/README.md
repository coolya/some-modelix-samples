# `model-server` start helper

This 'component' is not a real component but rather a start helper - the `model-server` is started using the gradle application plugin.

To start the `model-server`, simply call the following in the repository root:

```
./gradlew model-server:run --args="-inmemory -dumpin courses.modelserver.dump"
```


As a result we do not need to use docker to run the `model-server`.
In case you want to use docker anyways, you could do something like this:

```
docker run  --rm -p 28101:28101 -d modelix/modelix-model:1.3.2 java -XX:MaxRAMPercentage=85 -Djdbc.url=$jdbc_url -cp "model-server/build/libs/*" org.modelix.model.server.Main -inmemory
```
