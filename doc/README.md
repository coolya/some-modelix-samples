# System architecture and sample use cases

## System architecture

[<img src="./images/system-diagram-full.svg" width=80% >](https://app.diagrams.net/#Hmodelix/modelix-samples/main/doc/images/system-diagram-full.svg)


The full architecture includes components for multiple use cases.
One does not need all components to realize the individual use cases described below.


### Domain-specific API

In this example, an extra domain-specific API layer is added which is defined in the [openAPI specification](/openapi).
This layer is meant educational as there are no noteworthy abstractions happen in this definition.
It intends to show how one introduces a clearly defined domain-specific abstraction isolating the language engineering (meta-modeling) and the web engineering.

We provide two implementations of the API layer: The [`rest-api-json-bulk`](/rest-api-json-bulk) and [`rest-api-model-server`](/rest-api-model-server) components.

#### `rest-api-json-bulk` component

This API implementation provides access to the model by obtaining the model knowledge directly from a running MPS instance.
It is implemented using ktor and connects to the [`json-bulk-model-access`](https://github.com/modelix/mps-rest-model-access) plugin running inside of MPS.
This component can only provide **read only access** as the `json-bulk-model-access` is read only.


#### `rest-api-model-server` component

This API implementation provides access to the model by connecting to a running [`modelix model-server`](https://github.com/modelix/modelix.core/tree/main/model-server).
It is implemented using Quarkus and can provide **read an write access** to the underlying model.
This is realized using websockets exposed by the `model-server`.



### Dashboard

The dashboard provides access to model knowledge through a browser.
As it is conforming to the openAPI specification, the dashboard is able to obtain the model content from both API implementations.
However, the dashboard is consequently limited by the chosen API implementation.


### "Real-time" collaboration web app

> ⚠  TBD


## Use cases

To illustrate different use cases, this section provides a short description of each use case alongside with the required system architecture.


### UC 1: Dashboard view

The imaginary domain use-case is a display next to each room that shows the upcoming lectures in that room or a display in the main hall showing all the lectures of the current day.
This use case envisions a scenario where a system/service outside of MPS wants to consume the content of models defined in MPS (i.e. read only access).

The [dashboard](/spa-dashboard-angular), an angular app that serves the content of a model, implements such a simple application.
In this simple case, a user does not need to edit these models from the browser.
As a result, the simple openAPI implementation `rest-api-json-bulk` is used.


[<img src="./images/uc-1-read-only-dashboard.svg" width=80% >](https://app.diagrams.net/#Hmodelix/modelix-samples/main/doc/images/uc-1-read-only-dashboard.svg)

Note:
  The depicted system architecture shows one way to realize this use case with this repository.
  Alternatively, one could also obtain the model data from the model server using the `rest-api-model-server`.

#### Start UC 1

*Note: All gradle commands assume you are in the top level folder of this repository.*

To start up the system as described in UC 1, you first have to have built the entire project :

```
./gradlew
```

Once done, you need to start all components involved, these are:

1. **MPS**: Start `MPS 2020.3.6` without any global plugins and open the project in the [mps](../mps) folder.
   The gradle build process will have downloaded all plugins needed to `mps/build/dependencies`.
   This includes the `json-bulk-model-access`, `api-gen`, and `modelix-cloud-access`.

2. **API layer**: The `rest-api-json-bulk` provides the models from the running MPS instance, simply run (it will be a blocking call):
   ```
   $ ./gradlew rest-api-json-bulk:run
   ```
   <details>
   <summary>🧾 You can expect output similar to this (unfold to see details)</summary>

   ```
   > Task :rest-api-json-bulk:run
   2022-12-07 10:12:38.874 [DefaultDispatcher-worker-11] INFO  ktor.application - Autoreload is disabled because the development mode is off.
   2022-12-07 10:12:39.009 [DefaultDispatcher-worker-11] INFO  ktor.application - Application started in 0.14 seconds.
   2022-12-07 10:12:39.131 [DefaultDispatcher-worker-1] INFO  ktor.application - Responding at http://0.0.0.0:8090
   <===========--> 91% EXECUTING [2m 20s]
   > :rest-api-json-bulk:run
   ```
   </details>

3. **Dashboard**: The dashboard itself is a node application which can be run via (it will be a blocking call):
   ```
   $ ./gradlew spa-dashboard-angular:npmRun
   ```
   <details>
   <summary>🧾 You can expect output similar to this (unfold to see details)</summary>

   ```
      > Task :spa-dashboard-angular:npmRun

      > angular.io-example@0.0.0 ng
      > ng serve

      - Generating browser application bundles (phase: setup)...
      ✔ Browser application bundle generation complete.

      Initial Chunk Files   | Names         |  Raw Size
      vendor.js             | vendor        |   2.47 MB |
      polyfills.js          | polyfills     | 318.03 kB |
      styles.css, styles.js | styles        | 211.31 kB |
      main.js               | main          |  86.71 kB |
      runtime.js            | runtime       |   6.53 kB |

      | Initial Total |   3.08 MB

      Build at: 2022-12-07T09:18:02.345Z - Hash: 186b24edf20c1c4a - Time: 13776ms

      ** Angular Live Development Server is listening on localhost:4200, open your browser on http://localhost:4200/ **


      ✔ Compiled successfully.
      ✔ Browser application bundle generation complete.

      5 unchanged chunks

      Build at: 2022-12-07T09:18:02.740Z - Hash: 186b24edf20c1c4a - Time: 324ms

      ✔ Compiled successfully.
      <============-> 95% EXECUTING [29s]
      > :spa-dashboard-angular:npmRun
      ```
   </details>

4. Explore the dashboard at [http://localhost:4200/](http://localhost:4200/)

   Note: Changes to the model in MPS will not automatically synchronize to the dashboard, you will have to manually reload the model.



### UC 2: Dashboard view and edit

This use case envisions a room and lecture planner who needs to update and modify the schedule.
Besides the obvious solution to simply use MPS to edit the model, an alternative requirement of this use case is the concurrent modification by different users via a browser and MPS alike.
This use case thus covers a scenario where a system/service outside of MPS wants to consume and modify the content of models defined in MPS in "real-time" (similar to functionalities provided by shared pads or google docs).


[<img src="./images/uc-2-read-write-dashboard.svg" width=80% >](https://app.diagrams.net/#Hmodelix/modelix-samples/main/doc/images/uc-2-read-write-dashboard.svg)

Note:
  Unlike UC1, this use case **requires** the usage of a `model-server` and the `rest-api-model-server` because the alternative `rest-api-json-bulk` only provides read access to models.


#### Start UC 2

*Note: All gradle commands assume you are in the top level folder of this repository.*

To start up the system as described in UC 2, you first have to have built the entire project :

```
./gradlew
```

Once done, you need to start all components involved, these are:

1. **modelix model-server**: Model knowledge is supplied by the `model-sever` in this use case.
   To avoid complicated setups, we simply start the model-server in memory using docker.
   Remember to stop the container once you are done.

   ```
   docker run  --rm -p 28101:28101 -d modelix/modelix-model:1.3.2 java -XX:MaxRAMPercentage=85 -Djdbc.url=$jdbc_url -cp "model-server/build/libs/*" org.modelix.model.server.Main -inmemory
   ```

   You can check that everything went fine via

   ```
   $ docker ps -a
   ```

   <details>
   <summary>🧾 You can expect output similar to this (unfold to see details)</summary>

   ```
   $ docker run  --rm -p 28101:28101 -d modelix/modelix-model:1.3.2 java -XX:MaxRAMPercentage=85 -Djdbc.url=$jdbc_url -cp "model-server/build/libs/*" org.modelix.model.server.Main -inmemory
   4db556f706530958da6f20eb907d8bb81e2e573068bf305fccf379ed99beb860


   $ docker ps -a
   CONTAINER ID   IMAGE                         COMMAND                  CREATED         STATUS                     PORTS                                           NAMES
   4db556f70653   modelix/modelix-model:1.3.2   "java -XX:MaxRAMPerc…"   3 seconds ago   Up 2 seconds               0.0.0.0:28101->28101/tcp, :::28101->28101/tcp   pedantic_euclid
   ```

   </details>

2. **API layer**: The `rest-api-model-server` provides an abstraction of the model from the previously started `model-server`, simply run (it will be a blocking call):

   ```
   $ ./gradlew rest-api-model-server:quarkusDev
   ```

   <details>
   <summary>🧾 You can expect output similar to this (unfold to see details)</summary>

   ```
    $ ./gradlew rest-api-model-server:quarkusDev

   > Task :rest-api-model-server:quarkusDev
   Listening for transport dt_socket at address: 5005
   Press [h] for more options>NG [8s]
   Tests paused
   Press [r] to resume testing, [h] for more options>
   Press [r] to resume testing, [o] Toggle test output, [h] for more options>
   __  ____  __  _____   ___  __ ____  ______
    --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
    -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
   --\___\_\____/_/ |_/_/|_/_/|_|\____/___/
   2022-12-07 14:02:16,002 INFO  [io.und.websockets] (Quarkus Main Thread) UT026003: Adding annotated server endpoint class org.modelix.sample.restapimodelserver.UpdateSocket for path /updates
        2022-12-07 14:02:16,464 INFO  [io.quarkus] (Quarkus Main Thread) rest-api-model-server unspecified on JVM (powered by Quarkus 2.14.0.Final) started in 2.922s. Listening on: http://localhost:8090
   2022-12-07 14:02:16,464 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
   2022-12-07 14:02:16,465 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, kotlin, resteasy-reactive, resteasy-reactive-jackson, smallrye-context-propagation, smallrye-openapi, swagger-ui, vertx, websockets, websockets-client]

   <============-> 95% EXECUTING [16s]
   > :rest-api-model-server:quarkusDev
   ```

   </details>

3. **Collaborative web app**: ⚠  TODO: Not finished yet, still in development ⚠
   <details>
   <summary>🧾 You can expect output similar to this (unfold to see details)</summary>

   ```
   TODO
   ```

   </details>

4. **MPS [optional]**: Start `MPS 2020.3.6` without any global plugins and open the project in the [mps](../mps) folder.
   The gradle build process will have downloaded all plugins needed to `mps/build/dependencies` (for this use case `modelix-cloud-access` is required).
   MPS behaves just like the web client and obtains model knowledge from the `model-server`.
   MPS is thus not required but an optional client to try the collaborative 'real-time' feature.

5. Explore the app at [http://localhost:????/](http://localhost:????/)

   Note: Changes to the model from any client will automatically synchronize with all other clients, you will **not** have to manually reload the model.

