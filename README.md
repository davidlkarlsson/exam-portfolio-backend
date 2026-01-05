# Portfolio – Backend

This repository contains the backend for my exam portfolio project.  
The backend is built with Java and Spring Boot and provides a secure REST API for authentication, project management, and protected admin functionality.

The application uses JWT-based authentication stored in HttpOnly cookies and is designed with a clear separation of concerns, validation, and security handled server-side.

---

## Features

- REST API built with Spring Boot
- JWT authentication using HttpOnly cookies
- Role-based authorization (Admin / Public)
- Protected admin endpoints
- CRUD operations for projects
- Input validation and error handling
- Secure cookie configuration (HttpOnly, SameSite, Secure)
- CORS configuration for frontend integration
- Environment-based configuration
- PostgreSQL database integration

---

## Tech Stack

- Java 21
- Spring Boot (Spring MVC)
- Spring Security
- JWT (JSON Web Tokens)
- PostgreSQL (Supabase)
- JPA / Hibernate
- Gradle
- Docker
- Resend (email delivery API)
- Deployed with Render

---

## Project Structure

```text
src/
├─ main/
│ ├─ java/com/david/examportfolio/exam_portfolio_backend/
│ │ ├─ admin/ # Admin domain (controllers, services, repositories)
│ │ ├─ advice/ # Global exception handling
│ │ ├─ config/ # Security, CORS and application configuration
│ │ ├─ contact/ # Contact form handling
│ │ ├─ jwt/ # JWT utilities and authentication filters
│ │ ├─ project/ # Project domain (CRUD logic and mappings)
│ │ └─ ExamPortfolioBackendApplication.java
│ │
│ └─ resources/
│ └─ application.properties
│
└─ test/
```


---

## Authentication

Authentication is handled using JWT stored in HttpOnly cookies.

When a user logs in:
- Credentials are validated server-side
- A JWT is generated and sent as an HttpOnly cookie
- The cookie is automatically included in subsequent requests

Protected endpoints:
- Require a valid JWT
- Are secured using Spring Security
- Enforce role-based access (Admin)

All authorization decisions are enforced server-side and do not rely on frontend checks.

---

## API Overview

### Public Endpoints

- `POST /api/v1/login` – Authenticate admin user and issue JWT cookie
- `POST /api/v1/contact` – Handle contact form submissions
- `GET /api/v1/projects` – Fetch public project data

### Admin Endpoints (Protected)

- `GET /api/v1/admin/projects`
- `POST /api/v1/admin/projects`
- `PATCH /api/v1/admin/projects/{id}`
- `DELETE /api/v1/admin/projects/{id}`


---

## Environment Variables

The backend is configured using environment variables.  
All sensitive values (credentials, secrets, API keys) are injected at runtime
and are not stored in the repository.

### Required Variables

```env
DB_URL=jdbc:postgresql://...
DB_USER=your_db_username
DB_PASSWORD=your_db_password

ADMIN_EMAIL=admin@example.com
ADMIN_USERNAME=your_preferred_username
ADMIN_PASSWORD=your_admin_password

JWT_SECRET=your_jwt_secret_at_least_32_chars

RESEND_API_KEY=your_resend_api_key
RECIPIENT=contact_receiver@example.com

PORT=8080
```

Application configuration is handled via application.properties, where
these variables are mapped using Spring's environment variable support.

### Security Note

All secrets and credentials are excluded from version control and must be
provided via environment variables in local and production environments.

JWT secrets must be generated per environment and kept private.
They should be sufficiently long and random to ensure token integrity.

---

## Admin Creation

An initial admin user is created on application startup using environment
variables. This avoids hardcoding credentials and allows secure configuration
per environment. The user is required to access protected admin
endpoints and the admin interface.

---

## Email Delivery

Contact form submissions are delivered via email using the Resend API.
A valid Resend API key is required and must be provided via environment variables.

---

## Database

The application uses PostgreSQL hosted on Supabase as its primary database.
Database access is handled via Spring Data JPA and Hibernate.

Connection details are provided through environment variables to support
different configurations for local development and production.

---

## Run the project

### Deployment

The application is containerized using Docker and deployed on Render.

A multi-stage Docker build is used to compile the application with Gradle and
run the resulting JAR on a lightweight Java runtime image.


### Local Development

The application can be run locally using Gradle or Docker once the required
environment variables are provided.

---

## Frontend Integration

This backend is designed to be consumed by a separate frontend application and
does not expose any administrative functionality without proper authentication.

---

## Author

**David Karlsson**
Java Developer Student – STI Yrkeshögskola
Exam Portfolio Project