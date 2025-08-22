

```markdown
# 🏋️‍♂️ Workout Tracker Backend

A **Spring Boot backend application** for tracking workouts, exercises, and user progress. Users can sign up, log in (JWT authentication), create workout plans with multiple exercises, schedule workouts, and view progress reports.



---

## 📚 Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Database](#database)

---

## 🚀 Features
- 🔐 **User Authentication**  
  Sign up / login with **JWT authentication**; only authenticated users can manage their workouts.

- 🏋️ **Workout Management**  
  Create, update, delete, and list workouts; assign multiple exercises to each workout; schedule workouts at specific dates and times.

- 🧘 **Exercise Management**  
  Pre-populated exercises (strength, cardio, flexibility); users can choose exercises when creating workouts.

- 📈 **Reports & Progress Tracking**  
  View past workouts; track total reps, max weight, and workout count per exercise.

- 📖 **API Documentation**  
  Swagger UI provides interactive API exploration.

---

## 🛠️ Tech Stack
- **Backend Framework:** Spring Boot (Java)
- **Database:** PostgreSQL
- **Security:** Spring Security + JWT
- **ORM:** Spring Data JPA / Hibernate
- **API Documentation:** Swagger / OpenAPI (springdoc-openapi)
- **Build Tool:** Maven

---

## ⚙️ Getting Started

### 📋 Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL 14+
- (Optional) Docker for containerized deployment

### 🧑‍💻 Local Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/workout-tracker.git
   cd workout-tracker
   ```

2. **Create a PostgreSQL database**
   ```sql
   CREATE DATABASE workoutdb;
   ```

3. **Configure `application.properties`**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/workoutdb
   spring.datasource.username=YOUR_DB_USER
   spring.datasource.password=YOUR_DB_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access Swagger UI**
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📌 API Documentation

All endpoints are documented using Swagger / OpenAPI.

### 🔐 Authentication
- `POST /api/auth/signup`
- `POST /api/auth/login`

### 🏋️ Exercises
- `GET /api/exercises`

### 🗓️ Workouts
- `GET /api/workouts`
- `POST /api/workouts`
- `PUT /api/workouts/{id}`
- `DELETE /api/workouts/{id}`

### 📊 Reports
- `GET /api/workouts/report`

> 🔒 JWT Secured Endpoints require a valid token in the `Authorization` header:  
> `Authorization: Bearer <token>`

---

## 🧬 Database Schema

| Table              | Description                                      |
|--------------------|--------------------------------------------------|
| `users`            | Stores user credentials (encrypted passwords)    |
| `exercises`        | Pre-populated exercise types                     |
| `workouts`         | User-created workout sessions                    |
| `workout_exercises`| Join table linking workouts to exercises         |

---


``` 


