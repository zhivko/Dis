# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

```

## Documentation for API Endpoints

All URIs are relative to *https://erender-test.ts.telekom.si:8445/api/v1/dis-dev*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------


## Documentation for Models

 - [Attribute](docs/Attribute.md)
 - [Document](docs/Document.md)
 - [DocumentsResponse](docs/DocumentsResponse.md)
 - [Error](docs/Error.md)
 - [ImportDocumentRequest](docs/ImportDocumentRequest.md)
 - [NewDocumentRequest](docs/NewDocumentRequest.md)
 - [PromoteDemoteDocumentRequest](docs/PromoteDemoteDocumentRequest.md)
 - [RoleUsers](docs/RoleUsers.md)


## Documentation for Authorization

Authentication schemes defined for the API:
### BasicAuth


- **Type**: HTTP basic authentication





## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author


