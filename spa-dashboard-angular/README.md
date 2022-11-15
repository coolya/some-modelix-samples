# spa-dashboard-angular

[<img src="doc/spa-sample-1.png" width=45% >](doc/spa-sample-1.png)
[<img src="doc/spa-sample-2.png" width=45% >](doc/spa-sample-2.png)


This project provides a single page application which accesses an implementation of this projects [openapi](../openapi/openapi.yaml).
To run this application you will need a component which provides the corresponding REST API conforming to the openAPI spec.

## Building & Running

### 1. Build `spa-dashboard-angular`

You can build this subproject individually from the parent folder via  all examples via gradle:
```
./gradlew spa-dashboard-angular:build
```

### 2. Start an openAPI implementation

Before you can start the application, you need to start one of the available openAPI implementations.
Thus, you can either start the [rest-api-json-bulk](../rest-api-json-bulk)) implementation, or alternatively you can start the [rest-api-model-server](../rest-api-model-server) implementation.
Check the READMEs of these projects on how to run them.

### 3. Run the angular app

Next run this single page application via:
```
./gradlew spa-dashboard-angular:run
```
You can then go to `http://localhost:4200` to see a dashboard with the courses model content.
