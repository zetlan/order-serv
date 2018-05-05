# Order Server example
Example Dropwizard app that saves and serves invoice data

## Requirements

Developed using:
* Gradle 4.7 (a wrapper script is provided)
* Java 1.8 (tested under 1.8.0_102)

## Getting started

Clone/download, and then (from the project directory):

```
./gradlew clean build
java -jar ./build/libs/order-serv.jar server config.yaml
```

Look for the API at http://localhost:8080/v1/invoices, and a helpful (Swagger) UI at http://localhost:8080/v1/swagger.

## Configuration

An example (very simple) configuration file is provided (`config.yaml`).

## Database

This sample project currently uses Hibernate to auto-magically create an H2 database. With minimal changes (primarily:
adding a driver as a compile dependency and changing the config file) it could connect to MySQL, PostgreSQL, etc.