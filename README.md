SpringMVC Rest- Best Practices
==============================
[Updates 07/13]: I got busy with Python projects. Plan to implement this by mid August.

Work in Progress ...

---

A full-blown, functional, tested Spring 3.2 reference application with JPA persistence, REST Level-3 resources, asynchronous processing, jobs, security, unit, mock, integration, functional, rest client stubs, and performance tests, and many best practices I gathered over several years working in mvc / spring / grails web apps.

# GOALS

## Domain Modeling
- Immutable Domain Model with Builder Pattern
- Jackson JSON Annotations
- JPA Annotations
- Unit Tests
 
## REST 
- REST Errors and Exception Resolver 
- HATEOAS (REST Level 3)

## Persistence
- Transaction Management & Connection Pooling
- JPA / Hibernate 

## AOP
- Http ETag management, HTTP Caching & Resource optimistic locking

## Async
- Asynchronous processing: Request-Acknowledge-Poll Pattern (Fork-Join/Future implemention on REST)
- Actors (Akka)
- Jobs

## Caching
- Simplified caching using Spring’s new @Cacheable / Eh-Cache

## Spring
- Streamlined configuration for web, persistence, rest, spring, and properties

## Testing
- Unit Testing (JUnit, Mockito)
- Integration Testing (Spring Test, MVC Test)

# Libraries Used
- Spring 3.2, JPA 2, Hibernate 4.1
- JSP, JQuery, Twitter Bootstrap 2.2
- Akka
- H2 db (soon, MongoDb?)
- JUnit, Mockito, Spring Test, Hamcrest, JsonPath, Restito, Spock
- Google Guava, Joda DateTime, Logback/Slf4j, Jackson Json
