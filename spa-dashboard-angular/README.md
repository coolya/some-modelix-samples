# spa-dashboard-angular

This project provides a single page application which accesses an implementation of this projects [openapi](../openapi/openapi.yaml).
Consequently you need a component which provides the corresponding REST API conforming to the openAPI spec.

## Building

You can build this subproject individually from the parent folder via  all examples via gradle:
```
./gradlew spa-dashboard-angular:build
```

To start a server which provides the API (e.g. the [rest-api-json-bulk ](../rest-api-json-bulk)) run
```
./gradlew rest-api-json-bulk:run
```


Next run this single page application via:
```
./gradlew spa-dashboard-angular:run
```
You can then go to `http://localhost:`

## Usage

For simple testing you can `curl` the data provided by the API for example via
