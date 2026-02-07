# Todo Backend

A production-ready REST API for a task management application with Pomodoro timer integration. Built with Spring Boot and deployed on a Linux VM.

**Live Demo:** [phasetheday.me](https://phasetheday.me)

## Tech Stack

| Category | Technology |
|----------|------------|
| Language | Java 21 |
| Framework | Spring Boot 3.5.4 |
| Security | Spring Security, JWT |
| Database | PostgreSQL |
| ORM | Spring Data JPA (Hibernate) |
| Build | Gradle |
| Deployment | Linux VM, Systemd, Nginx |

## Architecture

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│   React     │────▶│ Spring Boot │────▶│ PostgreSQL  │
│  Frontend   │     │   REST API  │     │  Database   │
└─────────────┘     └─────────────┘     └─────────────┘
                           │
                    ┌──────┴──────┐
                    │ JWT Auth    │
                    │ Filter      │
                    └─────────────┘
```

## Key Features

### Secure Authentication
- Stateless JWT-based authentication
- Password encryption with BCrypt
- Token validation filter chain

### RESTful API Design
- Clean resource-based URL structure
- Proper HTTP methods and status codes
- Request/Response DTOs for data transfer

### Pomodoro Integration
- Track focus sessions per task
- Aggregate duration statistics
- Productivity metrics support

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/auth/signup` | Register new user |
| POST | `/api/v1/auth/login` | Authenticate and get JWT |
| GET | `/api/v1/todos` | Get all todos |
| POST | `/api/v1/todos` | Create todo |
| GET | `/api/v1/todos/{id}` | Get specific todo |
| PATCH | `/api/v1/todos/{id}` | Update todo |
| DELETE | `/api/v1/todos/{id}` | Delete todo |
| POST | `/api/v1/todos/{id}/pomodoros` | Log pomodoro session |

## Technical Highlights

### Security Implementation
- Custom `JwtAuthenticationFilter` for token validation
- Configured CORS for frontend integration
- Stateless session management

### Database Design
- JPA entity relationships (User → Todo → Pomodoro)
- Optimized queries with Spring Data JPA
- Environment-specific configurations (local/prod)

### Deployment Pipeline
- Systemd service management
- Environment variables for sensitive data
- Nginx reverse proxy configuration

## Local Development

```bash
# Prerequisites: Java 21, PostgreSQL

# Set environment variables
export DB_PASSWORD=your_password
export JWT_SECRET=your_secret

# Run the application
./gradlew bootRun
```

## Project Structure

```
src/main/java/com/moon/todo/
├── config/          # Security, CORS configuration
├── controller/      # REST API endpoints
├── dto/             # Request/Response objects
├── entity/          # JPA entities
├── repository/      # Data access layer
├── security/        # JWT filter, auth components
└── service/         # Business logic
```

## What I Learned

- Implementing JWT authentication from scratch with Spring Security
- Managing environment-specific configurations for local and production
- Deploying Spring Boot applications on Linux with systemd
- Database migration from MariaDB to PostgreSQL
- CORS configuration for frontend-backend communication
