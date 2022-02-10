I implemented requested functionality. I used Spring Boot.

Instructions To compile and run this project you will need:

minimum Java 8 (JDK8) Maven To start the application use the command bellow

mvn spring-boot:run To see paths within swagger you need to open after application startup: http://localhost:8080/swagger-ui.html
Then you can use for instance below request body.
                 {
                   "address": "Winterallee 3"
                 }


Application port :8080

To run all unit and integration tests use the command bellow:
 mvn test

I used Google Format for code formatting and also used Sonarlint.

Previously I used address normalization in one our projects.
(I used osm and libpostal)