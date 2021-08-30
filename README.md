# Warehouse Management
## The Task
The assignment is to implement a warehouse software. This software should hold articles, and the articles should contain an identification number, a name and available stock. 
It should be possible to load articles into the software from a file, see the attached inventory.json.
The warehouse software should also have products, products are made of different articles. Products should have a name, price and a list of articles of which they are made from with a quantity. 
The products should also be loaded from a file, see the attached products.json. 
 
The warehouse should have at least the following functionality;
* Get all products and quantity of each that is an available with the current inventory
* Remove(Sell) a product and update the inventory accordingly

## Assumptions
* There are no prices present in the input data jsons even though its mentioned in the assignment, so not factoring in the prices for now. 
* Product name is not unique, we will generate the uniqueIds for products in the code as and when they are loaded.

## PRO's of current implementation
* Use of mybatis gives the flexibility to use complex queries so that we don't need to do a lot of manipulation with the entities in the application layer.
* Containerized application.
* Automatic API Documentation using Springfox.
* Flexibility to pass the path to the file for loading, this can be overridden in different environments using application properties.
* Less boilerplate due to Lombok, improves readability.
* Test coverage can be analyzed using the Jacoco report.
* Health check (and metrics) endpoint enabled using Actuator to indicate if the application is up and ready to pick up traffic.
 
## CON's of current implementation
* Using in-memory database. Any data that is persisted will be lost on application restart.
* Database username and password are part of application properties. For higher environments this is not ideal. 
* No authentication needed to call the apis. 
* PlaceOrder api supports placing order containing only one type of product.


## Future changes
* Currently using H2 as the in-memory database. Will have to configure to use a standalone database hosted on-premise or in cloud.
* Extend the tests by asserting against values in the database.
* Dataload happens via a file that is present at a physical location. A better way would be to define the input data as consumer messages which can be ingested using a Pubsub/Cloud Functions combination, or the AWS cloud equivalent.
* Identifying cacheable responses.
* Enable autoscaling for the application (assumed we are deploying it on Kubernetes) which would be set configured based on the CPU usage and memory limit.
* CI / CD pipeline to be created based on which repository we would be using for .e.g. Google Cloud Build , Github Actions etc.
* Order and Product can be split as 2 different microservices.

## Commands

### Install dependencies
Use the maven command line interface [Maven](https://maven.apache.org/) to install the dependencies
```bash
mvn clean install -DSkipTests
```

### Test
Use the maven command line interface [Maven](https://maven.apache.org/) to perform unit test
```bash
mvn test
```

### Docker build
```bash
docker build --rm -f "Dockerfile" -t warehouse-management:latest "."
```

### Docker compose
```bash
docker compose up -d
```
> make sure you are in the project root folder and you do a mvn install before running docker compose 

## Run application in IDE

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `WarehouseManagementApplication` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Open Intellij
	* File->Open -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Open VS Code
	* File->Open -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for `@SpringBootApplication`) [In our case `WarehouseManagementApplication.java`]
* 	Right Click on the file and `Run as Java Application`

## API Documentation
* [Swagger](http://localhost:8080/swagger-ui/index.html)

## Postman Collection 
* The postman collection for the apis are generated as the file `warehousemanagement.postman_collection.json` in the repository. This can be imported in Postman tool.

## How the docker image is built
* Base image for the docker container is `adoptopenjdk/openjdk11-openj9:alpine-slim`
* The dependency libraries under `/target/dependency/lib` are copied to the `/app/lib` in the image
* Resources files (for e.g. configuration files , properties files etc.) under `/target/dependency/resources` are copied to `/app` in the image
* Compiled class files under `/target/classes` are copied to `/app` in the image

## Requirements

* [Java](https://www.oracle.com/java/)
* [Maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [jUnit](https://junit.org/)
* [Docker](https://www.docker.com/)
