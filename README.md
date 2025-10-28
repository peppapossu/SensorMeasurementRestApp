RESTFul API App

Tech Stack:

- **Spring Framework:** Boot, Web, Data, Security  
- **Database:** PostgreSQL  
- **Message Broker:** Apache Kafka  
- **Caching:** Redis, Caffeine (Two-level cache)  
- **DB Versioning:** Liquibase  
- **Authentication:** JWT (RSA keys, Access/Refresh tokens)  
- **Utilities:** MapStruct, Lombok  
- **Containerization:** Docker, Docker Compose  
- **API Documentation:** Swagger → [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)


Description:

An application built with best practices and a layered, responsibility-split architecture.  
Based on the Spring Framework, using the following components: Spring Boot, Spring Web, Spring Data, and Spring Security.  
Apache Kafka is used as the main message bus with manual offset commit control.  
Redis and Caﬀeine provide a two-level caching system.  
PostgreSQL serves as the primary long-term data storage, with database schema versioning managed by Liquibase.  
All components are integrated and deployed using Docker Compose for fast and consistent containerized deployment.  
Swagger is used for RESTful API documentation and testing.  
Authentication is handled via username and password, while authorization relies on JWT tokens signed with RSA keys and a two-token strategy (access and refresh).