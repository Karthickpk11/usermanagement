Project Content:

1. Spring JPA
2. Spring Security with JWT Token
3. Rate Limiter with Spring AOP

**Hibernate**

Hibernate is a popular ORM (Object-Relational Mapping) framework.
It implements the JPA specification.
Hibernate provides the actual code for mapping objects to database tables, querying data, and managing transactions.

**Spring Data JPA**

Spring Data JPA is a higher-level abstraction built on top of JPA.
It simplifies data access by providing a more streamlined API.
It reduces the amount of boilerplate code required to interact with databases.
It automatically generates common database queries and handles transactions.
It uses Hibernate as the underlying JPA provider by default.

**Relationship :**
JPA is the specification, Hibernate is an implementation of JPA, and Spring Data JPA is a framework that makes it easier to use JPA in Spring applications. 
Spring Data JPA cannot work without a JPA provider like Hibernate. 

Key Benefits Reduces boilerplate code for database interactions, Simplifies data access logic, Provides a consistent way to work with different databases, and Improves developer productivity.

You create a repository interface that extends Spring Data JPA's JpaRepository.

Spring Data JPA will provide the implementation of the repository, allowing you to use methods like save(), findById(), findAll(), etc.

**Hibernate Schema Management:**
Setting up your database schema in Hibernate is key to seamless development and production. _spring.jpa.hibernate.ddl-auto_ options so you can pick the perfect fit for each environment and avoid schema headaches.

Configuration Options Explained

_none_ - Locked Down
No changes to the schema.
Use: For production environments where stability is crucial.

_validate_ - Schema Checker
Verifies the schema matches your entity mappings but doesn’t alter it.
Use: For testing or production, to ensure everything aligns without risking changes.

_update_ - Additive Tweaks Only
Adds new columns/tables as needed but won’t delete anything.
Use: In development to keep the schema updated without data loss.

_create_ - Clean Slate
Drops existing tables, recreates them based on current mappings.
Use: For test environments where you need a fresh schema each run.

_create-drop_ - Temporary Sandbox
Creates the schema at startup, drops it on shutdown.
Use: In testing environments where you need data wiped every time.

Choosing the Right Option by Environment:

**Production**: Stick to _none_ or _validate_ for stability.
**Development**: _update_ is the go-to for flexibility.
**Testing**: _create_ or _create-drop_ gives you fresh data every test.

**Hibernate - @GeneratedValue Annotation in JPA**

Here used _GenerationType.IDENTITY_ for Relies on the database’s auto-increment feature, Supported by MySQL, PostgreSQL, SQL Server, etc.  The @GeneratedValue annotation with GenerationType Identity indicates that the primary key will be generated automatically by the database.

**Test the Application**

Now run your application and test it out. Hit the following URL:
localhost:8085/userserviceapi

It will add the user to the database.
![image](https://github.com/user-attachments/assets/0f3e007d-d000-46bd-acc3-5b12ef69a8eb)

And, hit the following URL to generate the token.
![image](https://github.com/user-attachments/assets/fef77492-58ab-4db7-9794-f5fa4d660098)

Using this take we can access our endpoint according to the ROLE. Hit the following URL and put the Bearer token.
![image](https://github.com/user-attachments/assets/055ce7f6-d85a-4134-b064-59442c9d5530)

Issue faced:
![image](https://github.com/user-attachments/assets/57b87bb3-6af1-4cbc-8de1-b668b44edb9d)

Solution:
Registered a PasswordEncoder bean in UsermanagementApplication to avoid the circular-references issue.

**Rate limiter with Spring AOP**

Rate limiting is a technique used to control the rate of incoming requests to a service or API.Rate limiting can help improve the reliability and availability of a service by preventing overload situations, ensuring that resources are used efficiently, and protecting against malicious or abusive traffic. It’s commonly used in web applications, APIs, and microservices to manage traffic and ensure that users and systems are not negatively affected by excessive requests.

In Spring Boot, we can implement a rate limiter using different approaches. One way to implement a rate limiter is to use the Spring AOP (Aspect-Oriented Programming) module to intercept method calls and apply rate-limiting logic.

In the project implement a rate limiter with using the Spring AOP. if hit request more than setting limit you will get below exception message in console. 
![image](https://github.com/user-attachments/assets/15778b19-e3aa-4562-b79a-b8fe3fdff6be)







