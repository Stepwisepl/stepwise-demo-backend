# Stepwise demo application

## Goals
Main goal of this project is a knowledge base about development good practices that are used in Stepwise.

## Technology

Application is build upon [Kotlin] programming language and [Spring Framework] and [PostgreSQL] as relational
database. [Liquibase] is used to version database schema.

## API

Communication with the application is done using [REST API]. Designed API should be compliant
with [Google API design guides]. Every deviation from that guides need to be connected to comment explaining why such
deviation was made.

API documentation is exposed as [OpenAPI Specification] under `/v3/api-docs` path and can be viewed
using `/swagger-ui.html`. Each exposed API need to provide description of its fields, behaviour and different status code
that can be returned.

## Development environment

Docker compose is provided under path `/tools/docker/docker-compose.yml`. It will set up all required services needed
during development process.

WARNING: This `docker-compose` should only be used during development process, as is not provide any availability
guarantees.

All default configuration is already provided in `src/main/resources/application.yml`.

Main method of application is available under path `src/main/kotlin/pl/stepwise/demo/DemoApplication.kt`

## Testing

Application is tested by set of:

* `Unit` (suffix `Test`)
* `Functional` (suffix `FuntionalTest`)

`Functional` tests uses [TestContainers] to create appropriate test environment that will resemble production
environment as much as possible.

[Kotlin]:https://kotlinlang.org/

[Spring Framework]:https://spring.io/projects/spring-framework

[PostgreSQL]:https://www.postgresql.org/

[REST API]: https://restfulapi.net/

[OpenAPI Specification]: https://spec.openapis.org/oas/latest.html

[Google API design guides]: https://cloud.google.com/apis/design/

[Liquibase]: https://www.liquibase.org/

[TestContainers]: https://www.testcontainers.org/