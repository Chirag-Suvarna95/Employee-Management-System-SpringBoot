# Employee Management System

Employee Management System is a Spring Boot application designed to handle employee data efficiently. It provides REST APIs for adding, updating, deleting, and retrieving employee records with role-based access control to ensure security. The system supports three roles—Admin, Manager, and Employee—with different permissions for each, such as restricting delete operations to Admins only. Besides basic CRUD operations, it offers features to search employees by name, department, or job title and generate reports like department-wise statistics and highest salary employees.

The application uses Java 17, Spring Boot 3, Spring Data JPA with MySQL for persistence, and Spring Security for authentication and authorization. Passwords are stored securely using BCrypt hashing, and HTTP Basic Authentication is implemented for simplicity. Detailed logging is set up with daily rotating log files to track operations and errors. Global exception handling ensures consistent and clear error responses in JSON format.

## Features

- Employee CRUD operations (Create, Read, Update, Delete)
- Role-based access control (Admin, Manager, Employee)
- Employee search by name, department, job title
- Reports for department statistics and highest-paid employees
- Secure authentication with BCrypt password hashing and HTTP Basic Auth
- Logging with daily rotating log files
- Global exception handling with consistent error responses
- Unit and integration tests for reliability

## Technologies Used

- Java 17+
- Spring Boot 3.x
- Spring Data JPA with MySQL
- Spring Security 6
- Hibernate
- JUnit 5 and Mockito
- Maven
- BCrypt
- Logback for logging

## Setup and Run Instructions

### Prerequisites

- Java 17 or higher
- Maven
- MySQL server with a database named `EmployeeManagementSystem`

### Database Setup

Create the database in MySQL:

```sql
CREATE DATABASE EmployeeManagementSystem;
```

### Configuration

Update database credentials in `application.properties`.

### Running the Application

Build and run as SpringBoot app

Access the API at `http://localhost:8080`.

## Authentication

Use HTTP Basic Authentication with following users:

| Username | Password     | Role     |
| -------- | ------------ | -------- |
| admin    | adminpass    | ADMIN    |
| manager  | managerpass  | MANAGER  |
| employee | employeepass | EMPLOYEE |

## API Endpoints

| Method | Endpoint                               | Roles Allowed            | Description                        |
| ------ | -------------------------------------- | ------------------------ | ---------------------------------- |
| GET    | `/employees`                           | ADMIN, MANAGER, EMPLOYEE | List all employees                 |
| GET    | `/employees/{id}`                      | ADMIN, MANAGER, EMPLOYEE | Get employee by ID                 |
| POST   | `/employees`                           | ADMIN, MANAGER           | Create new employee                |
| PUT    | `/employees/{id}`                      | ADMIN, MANAGER           | Update an employee                 |
| DELETE | `/employees/{id}`                      | ADMIN                    | Delete employee                    |
| GET    | `/employees/search?name=xyz`           | ADMIN, MANAGER, EMPLOYEE | Search employees by name           |
| GET    | `/employees/reports/department-stats`  | ADMIN, MANAGER, EMPLOYEE | Department-wise statistics         |
| GET    | `/employees/salary/above?amount=50000` | ADMIN, MANAGER, EMPLOYEE | Employees with salary above amount |

## Logging

Logs are saved to daily rotating files in the `logs/` folder. The level and retention are configurable in the `application.properties` file.

## Testing

Run tests as JUnit test

```

Tests include unit and integration tests covering services and controllers.
```
