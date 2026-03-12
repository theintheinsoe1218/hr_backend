# HR Management System - Backend

This is the backend for the HR Management System, built with Java Spring Boot and MySQL.

## Tech Stack
- **Framework:** Spring Boot 3.2.3
- **Database:** MySQL
- **Documentation:** Swagger UI / OpenAPI 3.0
- **Lombok:** For reducing boilerplate code

## Prerequisites
- Java 17 or higher
- MySQL Server
- Maven

## Database Setup
1. Create a database named `hr_db` in your MySQL server.
2. The application will automatically create the tables on start (`spring.jpa.hibernate.ddl-auto=update`).
3. Update `src/main/resources/application.properties` with your MySQL username and password.

## Running the Application
```bash
mvn spring-boot:run
```
The server will start on `http://localhost:8080`.

## API Documentation
Once the application is running, you can access the Swagger UI at:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints
- **Departments:** `/api/departments`
- **Employees:** `/api/employees`
- **Attendance:** `/api/attendance`
- **Leave Requests:** `/api/leaves`
