##  *Restful with Spring Boot, Postgres and  Hibernate*

##  *Introduction*
 
This is a simple RESTful API with Spring Boot, Postgres and Hibernate.

##  *Requirements*

* Java 17+
* Maven
* Postgres
* Postman any other API client
* Git
* Docker
* Any IDE (IntelliJ, Eclipse, Netbeans, etc)
##  *Steps to Setup*

**1. Clone the application**

```bash
git clone https://github.com/sezermehmed/restful
```

**2. Create Postgres database**

```bash
create database springbootdb
```

**3. Change Postgres username and password as per your installation**

+ open `src/main/resources/application.properties` file.
+ change `spring.datasource.username` and `spring.datasource.password` as per your postgres installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/springboot-postgres-hibernate-crud-rest-api-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

##  *Explore Rest APIs*

The app defines following CRUD APIs.

    GET /api/v1/employees
    
    POST /api/v1/employees
    
    GET /api/v1/employees/{employeeId}
    
    PUT /api/v1/employees/{employeeId}
    
    DELETE /api/v1/employees/{employeeId}

You can test them using postman or any other rest client.




