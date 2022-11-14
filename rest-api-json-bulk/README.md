# rest-api-json-bulk

This project provides a REST API that wraps the functionality of the `mps-json-bulk-model-access` plugin running inside an MPS instance.
The provided REST api conforms to the API spec in the [openapi.yaml](../actual-rest-api/openapi.yaml).


## Building

You can build this subproject individually from the parent folder via  all examples via gradle:
```
./gradlew rest-api-json-bulk:build
```

To start the server which provides the API run
```
./gradlew rest-api-json-bulk:run
```
Settings such as ports and hosts are defined in the [application.conf](src/main/resources/application.conf).


## Usage

For simple testing you can `curl` the data provided by the API for example via






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