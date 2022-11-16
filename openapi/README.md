# OpenAPI of the Courses domain

This folder contains the [openAPI specification](openapi.yaml) which provides an additional domain-specific abstraction of the courses domain[^1].
The intention of this abstraction is to provide an example on how the use of `modelix` can be abstracted for eventual consumer apps, such as the [Angular based dashboard](../spa-dashboard-angular/README.md)).
The development process thus can follow a [API-first approach](https://swagger.io/resources/articles/adopting-an-api-first-approach/).
Consequently, this openAPI provides a contract of the API behaviour for web teams and a language engineers.



[^1]: This additional abstraction admittedly does not really provide fancy abstractions for this domain. 
Its purpose is rather educational for you to understand the option to add an additional layer separating web development from language engineering.
You do not have to do this.
Of course one does not necessarily need such an abstraction layer - any dashboard or web app can directly communicate with the API provided by the `model-server` based on a [generated model API](mps/README.md#generated-api).