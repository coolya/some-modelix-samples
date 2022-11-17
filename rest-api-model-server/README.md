# rest-api-model-server

This project provides a custom REST API implemented in Quarkus that wraps a model-server instance.
The REST API abstracts the generic model-server API for providing the dashboard example with usable data.
The provided REST API conforms to the API spec in the [openapi.yaml](../actual-rest-api/openapi.yaml).

## Building

You can build this subproject individually from the parent folder via all gradle:

```sh
./gradlew rest-api-model-server:build
```

Before starting the REST API, a model-server instance needs to be running.
The example from the MPS folder has to be deployed on this instance in a repository call `courses`.
Once these preconditions are met, the REST API can be launched with:

```sh
./gradlew rest-api-model-server:run
```

## Usage

For simple testing you can `curl` the data provided by the API for example:

<details>
<summary>
Unfold for examples
</summary>

```console
$ curl -s -X GET "http://localhost:8090/rooms" -H  "accept: application/json" | jq
{
  "rooms": [
      {
          "roomRef": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188058347",
          "name": "Einstein",
          "maxPlaces": 42,
          "hasRemoteEquipment": true
      },
      {
          "roomRef": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188058349",
          "name": "Schrödinger",
          "maxPlaces": 420,
          "hasRemoteEquipment": true
      }
  ]
}
```
</details>

Alternatively you can now start the [dashboard](../spa-dashboard-angular) to consume this API.
