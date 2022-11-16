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
and make you think and envision your own use-cases.

## Repository Structure

The repository is a single gradle project to make building everything at once easy. The examples are organized by the technology they use into the various sub-folders:

| Id / Link                             | Components/Technologies              | Description                                                                                                                                     | Status | Folder                                                           |
|---------------------------------------|--------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|--------|------------------------------------------------------------------|
| [1](mps/README.md#language)           | MPS + `cloud-plugin`                 | MPS language definition that is used by all examples. The MPS language structure is used to generate a Java API consumed by all examples.       | ✅      | [MPS](mps)                                                       |
| [2](mps/README.md#generated-api)      | MPS, `api-gen`                       | Generated Java API from the MPS language.                                                                                                       | ✅      | [University.Schedule.api](mps/solutions/University.Schedule.api) |
| [3a](openapi/README.md)               | openAPI                              | A hand-crafted openAPI specification that defines domain-specific REST endpoints which expose the model contents.                               | ✅      | [rest-api-json-bulk](rest-api-json-bulk)                         |
| [3b](rest-api-json-bulk/README.md)    | MPS w/ `mps-json-bulk-access` + Ktor | An implementation of the openAPI that exposes the model contents via REST. Obtains model data from MPS using the `mps-json-bulk-access` plugin. | ✅      | [rest-api-json-bulk](rest-api-json-bulk)                         |
| [3c](rest-api-model-server/README.md) | `model-server` + Quarkus             | An implementation of the openAPI that exposes the model contents REST. Obtains model data from a running `model-server`.                        | ✅      | [rest-api-model-server](rest-api-model-server)                   |
| [4](spa-dashboard-angular/README.md)  | Angular via REST                     | A single page app that realizes a read-only dashboard. Can connect to either of the openAPI implementations.                                    | ✅      | [spa-dashboard-angular](spa-dashboard-angular)                   |
| `5`                                   | TODO via websockets                  | A web application that allows editing of MPS models and realtime collaboration.                                                                 | ❌      | [collaboration-web-app](collaboration-web-app)                          |

Each sub-folder contains 

## Getting Started

To get started with the project we need to set up the gradle project. At the moment most modelix artifacts are stored on
the [itemis nexus](https://artifacts.itemis.cloud/#browse/browse:maven-mps:org%2Fmodelix) with no access restriction.

Some are also stored on GitHub packages. To access GitHub packaged you need to specify your credentials. First of all
you will need to generate a [personal access token](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#authenticating-to-github-packages)
with access to GitHub Packages. The project assumes that your username is available as the variable `gpr.user` and the
token as `gpr.key`. The easiest way to configure the credentials is copy the example below, paste it into the [`gradle.properties`](gradle.properties)
file in the repository and replace the values with your credentials:

```
gpr.user=<your GitHub login>
gpr.key=<your personal access token>
```

Gradle also supports [other locations](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties) for specifying these properties.

After you have set up your credentials you can build all examples via gradle:
```
./gradlew build # mac/linux

gradlew.bat build # windows
```

Once the initial build has completes feel free to edit the project with the code editor of your choice.

## The MPS Language

[See the 'Language' section in the MPS README.md](mps/README.md#language)


## Generated API

[See the 'Generated API' section in the MPS README.md](mps/README.md#generated-api)

## Domain-specific openAPI

[See the 'Generated API' section in the MPS README.md](openapi/README.md)

## openAPI implementation

This project provides two implementations of the [openAPI](openapi) domain abstraction.
You need to start either of them to use the [SPA dashboard](spa-dashboard-angular).

### Model knowledge from MPS

For a `MPS <-> API <-> dashboard` pipeline [follow the `rest-api-json-bulk` README.md](rest-api-json-bulk/README.md).

### Model knowledge from `model-server`

For a `model-server <-> API <-> dashboard` pipeline [follow the `rest-api-json-bulk` README.md](rest-api-json-bulk/README.md).

## SPA Dashboard

[See the `spa-dashboard-angular` README.md](spa-dashboard-angular/README.md)

### Running the Dashboard

Before running the actual dashboard you need to open the MPS project in the [`mps`](mps) folder. This will set up MPS with
the `mps-rest-model-access` plugin and expose your models via an HTTP API. This API is then accessed from the dashboard
application to load the models.

Once your MPS project is opened you can run the dashboard:

```
./gradlew dashboard:run
```

Once you see a log message like:
```
11:12:41.672 [ktor-cio-dispatcher-worker-1] INFO ktor.application - Responding at http://0.0.0.0:8090
```

Your dashboard is ready you can point your browser to http://localhost:8090 to view the dashboard.


### Deploying to Docker / Kubernetes

> ⚠️ TBD
