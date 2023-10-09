# Construcción
El microservicio se construyó con Spring Boot, se utilizó [Spring Initializr](https://start.spring.io) y se seleccionaron las siguientes opciones:
* Project type: Gradle
* Language: Java
* Packaging: jar
* Java version: 8
* Spring Boot: 3.1.14

Y se usaron las siguientes dependencias:
* **Web**: Spring Web
* **Validation**: Bean Validation
* **H2 Database**: H2 Database
* **Spring Data JPA**: Spring Data and Hibernate
* **Lombok**

```mermaid
classDiagram
   class User{
       +uuid id
       +String name
       +String email
       +String password
       +LocalDate created
       +LocalDate lastLogin
       +boolean isActive
       +List Phone 
       -getters()
       -setters()
   } 
   class Phone{
       +uuid id
       +long number
       +int citycode
       +String countrycode
       -getters()
       -setters()
   }
   
   Phone <|--User
```
