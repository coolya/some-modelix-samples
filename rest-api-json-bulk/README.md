# rest-api-json-bulk

This project provides a REST API that wraps the functionality of the `mps-json-bulk-model-access` plugin running inside an MPS instance.
The provided REST api conforms to the API spec in the [openapi.yaml](../actual-rest-api/openapi.yaml).

## Building

You can build this subproject individually from the parent folder via all gradle:
```
./gradlew rest-api-json-bulk:build
```

Before running the API provider you need to open the MPS project in the [mps](../mps) folder. 
This will automatically set up MPS with the [`mps-json-bulk-access` plugin](https://github.com/modelix/mps-rest-model-access) and expose the models via an HTTP API.
This API is then accessed from this openAPI abstraction implementation to load the models.

Once your MPS project is opened you can run:

```
./gradlew rest-api-json-bulk:run
```

Once you see a log message like:
```
[DefaultDispatcher-worker-8] INFO  ktor.application - Responding at http://0.0.0.0:8090
```

Your openAPI implementation is ready.



Settings such as ports and hosts are defined in the [application.conf](src/main/resources/application.conf).


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

$ curl -s -X GET "http://localhost:8090/rooms/r%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2(unused)%2F4128798754188058349" -H  "accept: application/json" | jq
{
    "roomRef": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188058349",
    "name": "Schrödinger",
    "maxPlaces": 420,
    "hasRemoteEquipment": true
}

$ curl -s -X GET "http://localhost:8090/rooms/r%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2(unused)%2F4128798754188058347" -H  "accept: application/json" | jq
{
    "roomRef": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188058347",
    "name": "Einstein",
    "maxPlaces": 42,
    "hasRemoteEquipment": true
}


$ curl -s -X GET "http://localhost:8090/rooms/trash" -H  "accept: application/json" | jq
{
    "Can not load Room: No deserializer found for: trash"
}

$ curl -s -X GET "http://localhost:8090/lectures" -H  "accept: application/json" | jq
{
    "lectures": [
        {
            "lectureRef": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188058353",
            "name": "Physics 101",
            "description": "You learn about stuff",
            "maxParticipants": 42,
            "room": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188058347"
        },
        {
            "lectureRef": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188060854",
            "name": "New Students Welcome",
            "description": "Hello everyone",
            "maxParticipants": 69,
            "room": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188058349"
        }
    ]
}

$ curl -s -X GET "http://localhost:8090/lectures/r%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2(unused)%2F4128798754188058353" -H  "accept: application/json" | jq
{
    "lectureRef": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188058353",
    "name": "Physics 101",
    "description": "You learn about stuff",
    "maxParticipants": 42,
    "room": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188058347"
}

$ curl -s -X GET "http://localhost:8090/lectures/r%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2(unused)%2F4128798754188060854" -H  "accept: application/json" | jq
{
    "lectureRef": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188060854",
    "name": "New Students Welcome",
    "description": "Hello everyone",
    "maxParticipants": 69,
    "room": "r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(unused)/4128798754188058349"
}

$ curl -s -X GET "http://localhost:8090/lectures/trash" -H  "accept: application/json" | jq
{
    "Can not load Room: No deserializer found for: trash"
}

```
</details>


Alternatively you can now start the [dashboard](../spa-dashboard-angular) to consume this API endpoint.