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


## Use Cases

> ⚠️ TBD

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

Once the initial build has completes feel free to inspect and edit the project with the code editor of your choice.
 - The top repository provides `IntelliJ` configurations, 
 - the [mps](mps) sub-project can be opened using `MPS 2020.3.6`, and 
 - the [dashboard](spa-dashboard-angular) is a `WebStorm` project.


## Components in this repository

This project allows you to run different use cases.
Depending on the chosen use case, different components are used.
This section gives a detailed overview and links for each of these components.

### 1. The MPS Language

The language and model used in all examples. 

[See the 'Language' section in the MPS README.md](mps/README.md#language)


### 2. Generated API

An API generated with the [api-gen](https://github.com/modelix/api-gen) plugin from modelix.

[See the 'Generated API' section in the MPS README.md](mps/README.md#generated-api)

### 3. Domain-specific openAPI

A domain-specific [openAPI](https://www.openapis.org/) specification.

[See the 'Generated API' section in the MPS README.md](openapi/README.md)

### 4. openAPI implementation

This project provides two implementations of the [openAPI](openapi) domain abstraction.
You need to start either of them to use the [SPA dashboard](spa-dashboard-angular).

#### 4a. MPS as a source

For a `MPS <-> API <-> dashboard` pipeline [follow the `rest-api-json-bulk` README.md](rest-api-json-bulk/README.md).

#### 4b. model-server as a source

For a `model-server <-> API <-> dashboard` pipeline [follow the `rest-api-json-bulk` README.md](rest-api-json-bulk/README.md).

### 5. SPA Dashboard

[See the `spa-dashboard-angular` README.md](spa-dashboard-angular/README.md)

### 6. 'Real-time' collaboration web application

> ⚠️ TBD

### 7. Deploying to Docker / Kubernetes

> ⚠️ TBD