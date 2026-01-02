# Bicycle Safety Registration System

A secure, modular backend system for bicycle registration, theft reporting, and verification, designed to support law enforcement agencies, insurance providers, bike shops, and individual owners.

Built as a production-grade backend using Java 17 and Spring Boot, with a strong emphasis on security, maintainability, and future scalability.

---

## Problem Overview

Bicycle theft reporting and recovery is fragmented across informal processes, making verification, recovery, and insurance claims inefficient.

This system provides:
- A centralized bicycle registry
- Secure role-based access for multiple stakeholders
- Auditable records suitable for investigations and claims
- A maintainable architecture that can evolve into microservices

---

## Core Features

- Bicycle registration and ownership tracking
- Theft reporting and status management
- Role-based access control for multiple user types
- Secure REST APIs with JWT authentication
- Audit logging for sensitive operations
- Analytics-ready data model

---

## Architecture

The system is implemented as a **feature-first modular monolith**, structured around clear domain boundaries.

### Core Modules
- User & Role Management
- Bicycle Registry
- Theft Reporting
- Claims & Verification
- Analytics & Reporting
- Audit Logging

Each module owns its controllers, services, domain models, and persistence logic, enabling clean separation and future extraction into microservices.

---

## Technology Stack

- **Language:** Java 17  
- **Framework:** Spring Boot 3.x  
- **Security:** Spring Security, JWT, RBAC  
- **Database:** PostgreSQL 15  
- **Migrations:** Flyway  
- **Testing:** JUnit 5, Mockito  
- **DevOps:** Docker, CI/CD pipelines  

---

## Security Model

- JWT-based authentication
- Role-Based Access Control (RBAC) for 4 user types
- Rate limiting on sensitive endpoints
- Audit logging for traceability and compliance

Security was treated as a first-class concern rather than an afterthought.

---

## Testing & Deployment

- Automated unit and service-level tests
- Dockerized application for consistent environments
- Versioned database migrations using Flyway
- CI pipeline for build and test verification

---

## Design Decisions & Trade-offs

- Chose a modular monolith over microservices to reduce operational overhead
- Enforced domain boundaries through feature-first structure
- Designed for future scale without premature distribution

This approach improved development efficiency while preserving long-term flexibility.

---

## Future Improvements

- Asynchronous workflows for claims processing
- Contract testing between modules
- Read-optimized projections for analytics
- Formal API versioning strategy

---

## What This Project Demonstrates

- End-to-end backend ownership
- Production-ready Java & Spring Boot development
- Security-first design
- Architectural judgment under real constraints
- Readiness for collaborative engineering teams
