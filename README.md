# Flight Booking (Angular + Spring Boot)
Full-stack flight booking application with an Angular frontend and a Spring Boot backend secured using JWT.

![Java Badge](https://img.shields.io/badge/Backend-Java%20%2F%20SpringBoot-blue?style=for-the-badge&logo=springboot)
![Hibernate Badge](https://img.shields.io/badge/ORM-Hibernate-yellow?style=for-the-badge&logo=hibernate)
![Angular Badge](https://img.shields.io/badge/Frontend-Angular-red?style=for-the-badge&logo=angular)
[![GitHub repo size](https://img.shields.io/github/repo-size/rohith2201/SmartMart?logo=github&style=for-the-badge)](https://github.com/rohith2201/flightbooking)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)
![GitHub contributors](https://img.shields.io/github/contributors/rohith2201/flightbooking?style=for-the-badge)
![GitHub commit activity](https://img.shields.io/github/commit-activity/t/rohith2201/flightbooking?style=for-the-badge)




## Monorepo Structure

- `angularapp/` - Angular 10 frontend (runs on port `8081`)
- `springapp/` - Spring Boot backend API (runs on port `8080`)

## Tech Stack

### Frontend

- Angular 10.1.x
- Angular Router + Route Guards
- HttpClient + JWT interceptor

### Backend

- Java 17, Spring Boot 3.0.1
- Spring Web, Spring Data JPA, Spring Security
- PostgreSQL
- JWT (`io.jsonwebtoken`)
- Swagger / OpenAPI (`springdoc`)

## Prerequisites

- Node.js + npm
- JDK 17+
- PostgreSQL

## Backend Setup (`springapp`)

Set environment variables (PowerShell):

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/flightdb"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="your_password"
```

Run backend:

```bat
cd springapp
mvnw.cmd spring-boot:run
```

Backend URL: `http://localhost:8080`

API docs:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Frontend Setup (`angularapp`)

Install and run:

```bat
cd angularapp
npm install
npm start
```

Frontend URL: `http://localhost:8081`

Frontend API base URL is configured in `angularapp/src/app/app.constants.ts`:

```ts
export const APP_URL = 'http://localhost:8080/api';
```

## Full-Stack Run Order

1. Start PostgreSQL.
2. Start backend (`springapp`) on `8080`.
3. Start frontend (`angularapp`) on `8081`.
4. Open `http://localhost:8081`.

## Authentication Flow

- Public backend endpoints:
  - `POST /api/register`
  - `POST /api/login`
- Login response includes JWT token (`token`).
- Frontend stores `token`, `userId`, `username`, and `role` in `localStorage`.
- `HttpIntercepterAuthService` automatically attaches:

```http
Authorization: Bearer <token>
```

- Angular route guard (`AuthGuard`) enforces login and role-based routes.

## Frontend Routes

- `/home`
- `/login`
- `/register`
- `/flight-list` (authenticated)
- `/book-form` and `/book-form/:id` (authenticated)
- `/my-history` (authenticated)
- `/add-flight` and `/add-flight/:id` (authenticated, `ADMIN` only)
- `/booking` (authenticated, `ADMIN` only)

## Backend API Summary

Base URL: `http://localhost:8080/api`

### Auth & Users

- `POST /register`
- `POST /login`
- `GET /user/{userId}`
- `GET /user`

### Flights

- `POST /flights`
- `PUT /flights/{flightId}`
- `GET /flights`
- `GET /flights/{flightId}`
- `DELETE /flights/{flightId}`

### Bookings

- `POST /bookings` (request body uses `BookingDTO`)
- `GET /bookings`
- `GET /bookings/all`
- `GET /bookings/{id}`
- `GET /bookings/user/{userId}`
- `PUT /bookings/{id}/{status}`

### Test Endpoints

- `GET /test/welcome`
- `GET /test/flights`

## Core Domain Models

### User

- `userId`, `email`, `password`, `username`, `mobileNumber`, `userRole`, `token`

### Flight

- `flightId`, `flightNumber`, `airline`, `departureLocation`, `arrivalLocation`, `departureTime`, `arrivalTime`, `price`, `totalSeats`

### Booking

- `bookingId`, `bookingDate`, `numberOfPassengers`, `status`, `userId`, `flight`

## Business Rules & Errors

- Registration rejects duplicate username.
- Password is stored using BCrypt hashing.
- Booking creation checks seat availability and reduces available seats.
- Global exception handling returns HTTP `500` for:
  - `SeatsExceededException`
  - `UsernameAlreadyExistsException`

## Notes

- CORS is open (`*`) in backend config.
- Frontend service contains `deleteBooking(...)`, but backend controller currently has no `DELETE /bookings/{id}` endpoint.