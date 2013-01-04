SpringMVC Rest- Best Practices
=============================
_Work in Progress_

A full-blown reference app to implement REST Level 3 (HAETEOAS) web application 
using Spring along with the following best practices:

# Best Practices

## Domain Modeling
- Immutable Domain Model with Builder Pattern
- Jackson Json Mapper
- Unit Tests
 
## REST 
- REST Errors and Exception resolver 
- Csutom HATEOAS implementation - Resource discovery through Links
- Etags – HTTP caching & Resource optimistic locking
- Transaction Management & Connection Pooling
- Jobs
- REST integration testing

## Async
- Asynchronous processing: Request-Acknowledge-Poll Pattern (Executor Threads)
- Actors
- Jobs

## Caching
- Simplified caching using Spring’s new @Cacheable 

## Spring
- Streamlined configuration for web, persistence, rest, spring, and properties

## Testing
- Unit Testing (JUnit, Mockito)
- Integeration Testing (Spring MVC Test)
- Functional Testing (Spock)    

# Core Libraries 
- Spring 3.1.3, JPA 2, Hibernate 3.6
- JSP, JQuery, Twitter Bootstrap, 
- H2 database
- Akka
- Maven
- JUnit, Mockito, Spock
- Google Guava, Joda DateTime, Logback/Slf4j, Jackson Json
