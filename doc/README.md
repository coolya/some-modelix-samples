# System architecture and sample use cases

## System architecture

[<img src="./images/system-diagram-full.svg" width=80% >](https://app.diagrams.net/#Hmodelix/modelix-samples/main/doc/images/system-diagram-full.svg)


The full architecture includes components for multiple use cases.
One does not need all components to realize the individual use cases described below.


### Domain-specific API

Generally, the architecture introduces an extra domain-specific API layer which is defined in the [openAPI](/openapi).
While no noteworthy abstractions happen in this definition, this layer is meant educational.
It intends to show how one introduces a clearly defined domain-specific abstraction isolating the language engineering (meta-modeling) and the web engineering.


We provide two implementations of the API layer: [`rest-api-json-bulk`](/rest-api-json-bulk) and [`rest-api-model-server`](/rest-api-model-server).

#### `rest-api-json-bulk`

This API implementation provides access to the model by obtaining the model knowledge directly from a MPS instance.
It connects to the [`json-bulk-model-access`](https://github.com/modelix/mps-rest-model-access) plugin running inside of MPS and is only able to read the model content from this source.

#### `rest-api-model-server`

This API implementation provides access to the model by connecting to a running []`modelix model-server`](https://github.com/modelix/modelix.core/tree/main/model-server).
From this source the `rest-api-model-server` can provide read an write access to the underlying model.



### Dashboard

The dashboard provides access to model knowledge via a browser.
As it is conforming to the openAPI specification, the dashboard is able to obtain the model content from two sources:

1. TODO
2. TODO


TODO



## Use cases

The idea here is to illustrate the different use cases alongside with their required system architecture.


### UC 1: Dashboard view

The imaginary domain use-case is a display next to each room that shows the upcoming lectures in that room or a display in the main hall showing all the lectures of the current day.
This use case envisions a scenario where a system/service outside of MPS wants to consume the content of models defined in MPS (i.e. read only access).

The [dashboard](/spa-dashboard-angular) implements such a simple application.
It is an angular app that serves the content of a model.
A user can not edit these models from the browser.


[<img src="./images/uc-1-read-only-dashboard.svg" width=80% >](https://app.diagrams.net/#Hmodelix/modelix-samples/main/doc/images/uc-1-read-only-dashboard.svg)

Note:
  The depicted system architecture shows one way to realize this use case with this repository.
  Alternatively, one could also obtain the model data from the model server using the `rest-api-model-server`.



### UC 2: Dashboard view and edit

TODO: UC description

[<img src="./uc-2-read-write-dashboard.svg" width=80% >](https://app.diagrams.net/#Hmodelix/modelix-samples/main/doc/images/uc-2-read-write-dashboard.svg)

Note:
  Unlike UC1, this use case **requires** the usage of a `model-server` and the `rest-api-model-server` because the alternative `rest-api-json-bulk` only provides read access to models.
