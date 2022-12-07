# Some modelix samples

>  ⚠️ **The examples in this repository are currently work in progress** ⚠️

This repository contains a couple of examples that show various aspects of the modelix platform.
Not all features of modelix are shown here so this is not a complete demo of everything modelix can do.
The primary focus of the examples is to show how you can use modelix to work with language and models defined in
MPS outside of MPS using JVM (Java/Kotlin) or web frameworks/tools.

None of the examples try to reuse editors defined in MPS.
If your goal is to reuse MPS editors as is then modelix itself ships with a projector integration to achieve this.

While all the examples use the same language defined in MPS they are not meant as one complete example
but rather as individual parts. The purpose of the examples is to give inspiration of what is possible with modelix
and make you think and envision your own use cases.

## Repository Structure

The repository is a single gradle project to make building everything at once easy. The examples are organized by the technology they use into the various sub-folders:

| Id / Link                            | Components/Technologies              | Description                                                                                                                                  | Status | Folder                                                           |
|--------------------------------------|--------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|--------|------------------------------------------------------------------|
| [1](mps/README.md#language)          | MPS + `cloud-plugin`                 | MPS language definition that is used by all examples. The MPS language structure is used to generate a Java API consumed by all examples.    | ✅      | [MPS](mps)                                                       |
| [2](mps/README.md#generated-api)     | MPS, `api-gen`                       | Generated Java API from the MPS language.                                                                                                    | ✅      | [University.Schedule.api](mps/solutions/University.Schedule.api) |
| [3](openapi/README.md)               | openAPI                              | A hand-crafted openAPI specification that defines domain-specific REST endpoints which expose the model contents.                            | ✅      | [openapi](openapi)                                               |
| [4a](rest-api-json-bulk/README.md)   | MPS w/ `mps-json-bulk-access` + Ktor | An implementation of the openAPI that exposes the model contents via REST. Obtains model data from MPS using the `mps-json-bulk-access` plugin. | ✅      | [rest-api-json-bulk](rest-api-json-bulk)                         |
| [4b](rest-api-model-server/README.md) | `model-server` + Quarkus             | An implementation of the openAPI that exposes the model contents REST. Obtains model data from a running `model-server`.                     | ✅      | [rest-api-model-server](rest-api-model-server)                   |
| [5](spa-dashboard-angular/README.md) | Angular via REST                     | A single page app that realizes a read-only dashboard. Can connect to either of the openAPI implementations.                                 | ✅      | [spa-dashboard-angular](spa-dashboard-angular)                   |
| `6`                                  | ❔ + websockets                       | A web application that allows editing of MPS models and realtime collaboration.                                                              | ❌      | collaboration-web-app                                            |
| `7`                                  | docker / kubernetes                  |                                                                                                                                              | ❌      | deployment                                                       |

Each sub-folder contains its own `README.md` with component specific documentation.


## Getting Started

To get started with the project we need to set up the gradle project.
At the moment most modelix artifacts are stored on the [itemis nexus](https://artifacts.itemis.cloud/#browse/browse:maven-mps:org%2Fmodelix) with no access restriction.
Some are also stored on GitHub packages.

<details>
<summary>Unfold for details on how to set up GitHub packages authentication</summary>
To access GitHub packaged you need to specify your credentials.
First of all you will need to generate a [personal access token](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#authenticating-to-github-packages) with access to GitHub Packages. The project assumes that your username is available as the variable `gpr.user` and the token as `gpr.key`.
The easiest way to configure the credentials is copy the example below, paste it into the [`gradle.properties`](gradle.properties) file in the repository and replace the values with your credentials:

```
gpr.user=<your GitHub login>
gpr.key=<your personal access token>
```

Gradle also supports [other locations](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties) for specifying these properties.

After you have set up your credentials you can build all examples.

</details>


To build all examples via gradle, simply call:

```
./gradlew build # mac/linux

gradlew.bat build # windows
```

Once the initial build has completes feel free to inspect the [use cases](/doc/README.md#use-cases) and start components as described for each use case.
Alternatively, you can inspect and edit the project with the code editor of your choice.
 - The top repository provides `IntelliJ` configurations,
 - the [mps](mps) sub-project can be opened using `MPS 2020.3.6`, and
 - the [dashboard](spa-dashboard-angular) is a `WebStorm` project.



## System architecture and components in this repository

This project allows you to run different use cases.
Depending on the chosen use case, only a subset of the components in this repository are used.


## Full System architecture

The full architecture includes components for multiple use cases.
One does not need all components to realize individual use cases.

[<img src="/doc/images/system-diagram-full.svg" width=80% >](https://app.diagrams.net/#Hmodelix/modelix-samples/main/doc/images/system-diagram-full.svg)

In the following a short overview is given on each component.



  1. **The MPS Language**

     The language (meta-model) and model used in all examples.

     [See the 'Language' section in the MPS README.md](mps/README.md#language)


  2. **Generated model API (domain API)**

     An API based on the meta-model generated with the [api-gen](https://github.com/modelix/api-gen) plugin from modelix.

     [See the 'Generated API' section in the MPS README.md](mps/README.md#generated-api)

  3. **Domain-specific openAPI**

     In this example project, an extra domain-specific API layer is added which is defined in the [openAPI](https://www.openapis.org/) [specification](/openapi).
     This layer is meant educational as there are no noteworthy abstractions happen in this definition.
     It intends to show how one introduces a clearly defined domain-specific abstraction isolating the language engineering (meta-modeling) and the web development.

     We provide two implementations of the API layer: The [`rest-api-json-bulk`](/rest-api-json-bulk) and [`rest-api-model-server`](/rest-api-model-server) components.

     For more details, also see the ['OpenAPI of the Courses domain' section in the MPS README.md](openapi/README.md)



  4. **openAPI implementation**

     This project provides two implementations of the [openAPI](openapi) domain abstraction.

     - A. MPS as a source (`rest-api-json-bulk` component)

          This API implementation provides access to the model by obtaining the model knowledge directly from a running MPS instance.
          It is implemented using ktor and connects to the [`json-bulk-model-access`](https://github.com/modelix/mps-rest-model-access) plugin running inside of MPS.
          This component can only provide **read only access** as the `json-bulk-model-access` is read only.

          For more details, also see the [`rest-api-json-bulk` README.md](rest-api-json-bulk/README.md) for details.



     - B. model-server as a source (`rest-api-model-server` component)

          This API implementation provides access to the model by connecting to a running [`modelix model-server`](https://github.com/modelix/modelix.core/tree/main/model-server).
          It is implemented using Quarkus and can provide **read an write access** to the underlying model.
          This is realized using websockets exposed by the `model-server`.

          For more details, also see the [`rest-api-model-server` README.md](/rest-api-model-server/README.md) for details.




  5. **Single-page application (SPA) Dashboard**

     The dashboard provides access to model knowledge through a browser.
     As it is conforming to the openAPI specification, the dashboard is able to obtain the model content from both openAPI implementations.
     However, the dashboard is consequently limited by the chosen API implementation.

     For more details, also see the [`spa-dashboard-angular` README.md](spa-dashboard-angular/README.md) for details.

     Note: Requires an openAPI implementation to obtain model knowledge from.

  6. **'Real-time' collaboration web application**

     > ⚠️ TBD

  7. **Deploying to Docker / Kubernetes**

     > ⚠️ TBD


## Use cases

To illustrate different use cases, this section provides a short description of each use case alongside with the required system architecture.


## UC 1: Dashboard view

The imaginary domain use-case is a display next to each room that shows the upcoming lectures in that room or a display in the main hall showing all the lectures of the current day.
This use case envisions a scenario where a system/service outside of MPS wants to consume the content of models defined in MPS (i.e. read only access).

The [dashboard](/spa-dashboard-angular), an angular app that serves the content of a model, implements such a simple application.
In this simple case, a user does not need to edit these models from the browser.
As a result, the simple openAPI implementation `rest-api-json-bulk` is used.


[<img src="/doc/images/uc-1-read-only-dashboard.svg" width=80% >](https://app.diagrams.net/#Hmodelix/modelix-samples/main/doc/images/uc-1-read-only-dashboard.svg)

Note:
  The depicted system architecture shows one way to realize this use case with this repository.
  Alternatively, one could also obtain the model data from the model server using the `rest-api-model-server`.

### How to start UC 1

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



## UC 2: Dashboard view and edit

This use case envisions a room and lecture planner who needs to update and modify the schedule.
Besides the obvious solution to simply use MPS to edit the model, an alternative requirement of this use case is the concurrent modification by different users via a browser and MPS alike.
This use case thus covers a scenario where a system/service outside of MPS wants to consume and modify the content of models defined in MPS in "real-time" (similar to functionalities provided by shared pads or google docs).


[<img src="/doc/images/uc-2-read-write-dashboard.svg" width=80% >](https://app.diagrams.net/#Hmodelix/modelix-samples/main/doc/images/uc-2-read-write-dashboard.svg)

Note:
  Unlike UC1, this use case **requires** the usage of a `model-server` and the `rest-api-model-server` because the alternative `rest-api-json-bulk` only provides read access to models.


### How to start UC 2

*Note: All gradle commands assume you are in the top level folder of this repository.*

To start up the system as described in UC 2, you first have to have built the entire project :

```
./gradlew
```

Once done, you need to start all components involved, these are:

1. **modelix model-server**: Model knowledge is supplied by the `model-sever` in this use case.
   To avoid complicated setups, we simply start the model-server in memory and load the model content from the included dump file, all using gradle:

   ```
   ./gradlew model-server:run --args="-inmemory -dumpin courses.modelserver.dump"
   ```

   <details>
   <summary>🧾 You can expect output similar to this (unfold to see details)</summary>

   ```
      ./gradlew model-server:run --args="-inmemory -dumpin courses.modelserver.dump"

   > Task :model-server:run
   18:33:16,185 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.xml]
   18:33:16,186 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback.groovy]
   18:33:16,186 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Found resource [logback.xml] at [jar:file:/home/nkoester/.gradle/caches/modules-2/files-2.1/org.modelix/model-server-fatjar/1.3.2/1e6502c0e8282b1fe2c06824ad43f4d7270f20d7/model-server-fatjar-1.3.2.jar!/logback.xml]
   18:33:16,194 |-INFO in ch.qos.logback.core.joran.spi.ConfigurationWatchList@f4168b8 - URL [jar:file:/home/nkoester/.gradle/caches/modules-2/files-2.1/org.modelix/model-server-fatjar/1.3.2/1e6502c0e8282b1fe2c06824ad43f4d7270f20d7/model-server-fatjar-1.3.2.jar!/logback.xml] is not of type file
   18:33:16,269 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - About to instantiate appender of type [ch.qos.logback.core.ConsoleAppender]
   18:33:16,270 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - Naming appender as [console]
   18:33:16,272 |-INFO in ch.qos.logback.core.joran.action.NestedComplexPropertyIA - Assuming default type [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
   18:33:16,282 |-INFO in ch.qos.logback.classic.joran.action.LoggerAction - Setting level of logger [org.modelix] to DEBUG
   18:33:16,282 |-INFO in ch.qos.logback.core.joran.action.AppenderRefAction - Attaching appender named [console] to Logger[org.modelix]
   18:33:16,282 |-INFO in ch.qos.logback.classic.joran.action.RootLoggerAction - Setting level of ROOT logger to INFO
   18:33:16,282 |-INFO in ch.qos.logback.core.joran.action.AppenderRefAction - Attaching appender named [console] to Logger[ROOT]
   18:33:16,282 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - End of configuration.
   18:33:16,283 |-INFO in ch.qos.logback.classic.joran.JoranConfigurator@7ff95560 - Registering current configuration as safe fallback point
   18:33:16.295 [main] INFO  org.modelix.model.server.Main - Max memory (bytes): 32178700288
   18:33:16.295 [main] INFO  org.modelix.model.server.Main - Max memory (bytes): 32178700288
   18:33:16.295 [main] INFO  org.modelix.model.server.Main - Server process started
   18:33:16.295 [main] INFO  org.modelix.model.server.Main - Server process started
   18:33:16.295 [main] INFO  org.modelix.model.server.Main - In memory: true
   18:33:16.295 [main] INFO  org.modelix.model.server.Main - In memory: true
   18:33:16.296 [main] INFO  org.modelix.model.server.Main - Path to secret file: /secrets/modelsecret/modelsecret.txt
   18:33:16.296 [main] INFO  org.modelix.model.server.Main - Path to secret file: /secrets/modelsecret/modelsecret.txt
   18:33:16.296 [main] INFO  org.modelix.model.server.Main - Path to JDBC configuration file: null
   18:33:16.296 [main] INFO  org.modelix.model.server.Main - Path to JDBC configuration file: null
   18:33:16.296 [main] INFO  org.modelix.model.server.Main - Schema initialization: false
   18:33:16.296 [main] INFO  org.modelix.model.server.Main - Schema initialization: false
   18:33:16.296 [main] INFO  org.modelix.model.server.Main - Set values: []
   18:33:16.296 [main] INFO  org.modelix.model.server.Main - Set values: []
   18:33:16.296 [main] INFO  org.modelix.model.server.Main - Port: 28101
   18:33:16.296 [main] INFO  org.modelix.model.server.Main - Port: 28101
   Values loaded from /home/nkoester/git/modelix/modelix-sample/model-server/courses.modelserver.dump (73)
   18:33:16.364 [main] INFO  ktor.application - Autoreload is disabled because the development mode is off.
   18:33:16.428 [main] INFO  ktor.application - Application started in 0.088 seconds.
   18:33:16.518 [DefaultDispatcher-worker-1] INFO  ktor.application - Responding at http://0.0.0.0:28101
   <===========--> 85% EXECUTING [7s]
   > :model-server:run

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


