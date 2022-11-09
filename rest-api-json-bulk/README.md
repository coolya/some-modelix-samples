# rest-api-json-bulk

This project provides a REST API that wraps the functionality of the `mps-json-bulk-model-access` plugin running inside an MPS instance. The provided REST api conforms to the API spec in the [openapi.yaml](../actual-rest-api/openapi.yaml).


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

```shell
curl -s -X GET "http://localhost:8090/rooms" -H  "accept: application/json" | jq
curl -s -X GET "http://localhost:8090/rooms/r%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2(unused)%2F4128798754188058349" -H  "accept: application/json" | jq
curl -s -X GET "http://localhost:8090/rooms/r%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2(unused)%2F4128798754188058347" -H  "accept: application/json" | jq
curl -s -X GET "http://localhost:8090/rooms/trash" -H  "accept: application/json" | jq

curl -s -X GET "http://localhost:8090/lectures" -H  "accept: application/json" | jq
curl -s -X GET "http://localhost:8090/lectures/r%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2(unused)%2F4128798754188058353" -H  "accept: application/json" | jq
curl -s -X GET "http://localhost:8090/lectures/r%3Ace161c54-ea76-40a6-a31d-9d7cd01febe2(unused)%2F4128798754188060854" -H  "accept: application/json" | jq
curl -s -X GET "http://localhost:8090/lectures/trash" -H  "accept: application/json" | jq
```