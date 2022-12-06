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



### UC 2: Dashboard view and edit

This use case envisions a room and lecture planner who needs to update and modify the schedule.
Besides the obvious solution to simply use MPS to edit the model, an alternative requirement of this use case is the concurrent modification by different users via a browser and MPS alike.
This use case thus covers a scenario where a system/service outside of MPS wants to consume and modify the content of models defined in MPS in "real-time" (similar to functionalities provided by shared pads or google docs).


[<img src="./images/uc-2-read-write-dashboard.svg" width=80% >](https://app.diagrams.net/#Hmodelix/modelix-samples/main/doc/images/uc-2-read-write-dashboard.svg)

Note:
  Unlike UC1, this use case **requires** the usage of a `model-server` and the `rest-api-model-server` because the alternative `rest-api-json-bulk` only provides read access to models.
