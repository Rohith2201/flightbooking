# Flight Booking Backend (Spring Boot)

Spring Boot backend API for a flight booking system with JWT authentication, flight management, and booking operations.

## Tech Stack

- Java 17
- Spring Boot 3.0.1
- Spring Web, Spring Data JPA, Spring Security
- PostgreSQL
- JWT (`io.jsonwebtoken`)
- Springdoc OpenAPI / Swagger UI
- Maven

## Project Structure

- Backend app root: `springapp/`
- Main application class: `springapp/src/main/java/com/examly/springapp/SpringappApplication.java`

## Prerequisites

- JDK 17+
- Maven (or use `mvnw` / `mvnw.cmd`)
- PostgreSQL database

## Environment Variables

The app reads DB credentials from environment variables in `application.properties`:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Example (PowerShell):

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/flightdb"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="your_password"
```

## Run the Application

From `springapp/`:

```bash
./mvnw spring-boot:run
```

On Windows:

```bat
mvnw.cmd spring-boot:run
```

Default server port: `8080`

## API Documentation

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Security

- Public endpoints:
	- `POST /api/register`
	- `POST /api/login`
	- Swagger/OpenAPI paths
- All other endpoints require Bearer JWT.
- JWT token is returned in login response (`token` field in `User` response).
- Include header:

```http
Authorization: Bearer <token>
```

## Data Model

### User

- `userId` (Long)
- `email` (String)
- `password` (String, stored encoded)
- `username` (String)
- `mobileNumber` (String)
- `userRole` (String)
- `token` (transient, returned during login)

### Flight

- `flightId` (Long)
- `flightNumber` (String)
- `airline` (String)
- `departureLocation` (String)
- `arrivalLocation` (String)
- `departureTime` (String)
- `arrivalTime` (String)
- `price` (double)
- `totalSeats` (int)

### Booking

- `bookingId` (Long)
- `bookingDate` (Date)
- `numberOfPassengers` (int)
- `status` (String)
- `userId` (Long)
- `flight` (Many-to-One `Flight`)

## API Endpoints

Base URL: `http://localhost:8080`

### Auth & Users

- `POST /api/register` - Register a user
- `POST /api/login` - Login and receive JWT token in response
- `GET /api/user/{userId}` - Get user by ID
- `GET /api/user` - Get all users

Sample register/login payload:

```json
{
	"email": "john@example.com",
	"password": "password123",
	"username": "john",
	"mobileNumber": "9876543210",
	"userRole": "ROLE_USER"
}
```

### Flights

- `POST /api/flights` - Create flight
- `PUT /api/flights/{flightId}` - Update flight
- `GET /api/flights` - List flights
- `GET /api/flights/{flightId}` - Get flight by ID
- `DELETE /api/flights/{flightId}` - Delete flight

Sample flight payload:

```json
{
	"flightNumber": "AI203",
	"airline": "Air India",
	"departureLocation": "Chennai",
	"arrivalLocation": "Delhi",
	"departureTime": "2026-02-20T10:30:00",
	"arrivalTime": "2026-02-20T13:15:00",
	"price": 6500.0,
	"totalSeats": 120
}
```

### Bookings

- `POST /api/bookings` - Create booking (uses `BookingDTO`)
- `GET /api/bookings` - Get bookings
- `GET /api/bookings/all` - Get all bookings
- `GET /api/bookings/{id}` - Get booking by ID
- `GET /api/bookings/user/{userId}` - Get booking summary for user
- `PUT /api/bookings/{id}/{status}` - Update booking status

Booking create payload (`BookingDTO`):

```json
{
	"flightId": 1,
	"userId": 1,
	"bookingDate": "2026-02-20",
	"numberOfPassengers": 2,
	"status": "CONFIRMED"
}
```

### Test

- `GET /api/test/welcome` - Returns welcome message
- `GET /api/test/flights` - Returns an empty list (placeholder endpoint)

## Business Rules

- Username must be unique on registration.
- Password is encoded with BCrypt.
- Booking creation validates seat availability:
	- if requested seats exceed `flight.totalSeats`, API throws `SeatsExceededException`.
	- on successful booking, seats are reduced from the flight.

## Error Handling

Global exception handler currently maps these to HTTP 500 with message body:

- `SeatsExceededException`
- `UsernameAlreadyExistsException`

## CORS

Configured globally to allow:

- Origins: `*`
- Methods: `GET, POST, PUT, DELETE, OPTIONS`
- Headers: `*`

## Build & Test

From `springapp/`:

```bash
./mvnw clean test
./mvnw clean package
```