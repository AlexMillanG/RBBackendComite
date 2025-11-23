# RBBackendComite - Community Event Management System

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[Version en Español](README.md)

## Overview

**RBBackendComite** is a robust REST API built with Spring Boot that enables altruistic groups and community committees to organize, manage, and coordinate social impact events such as reforestation, cleaning campaigns, and other community activities.

The system facilitates event organization, participant management, community group administration, and activity tracking, all through a modern and secure architecture with JWT authentication.

## Key Features

### Event Management
- ✅ Complete event CRUD operations
- ✅ Classification by types (Reforestation, Cleaning, Campaigns, etc.)
- ✅ Status control: Upcoming, In Progress, Finished
- ✅ Automatic event assignment to specific groups
- ✅ Date validation (events can only be today or future)
- ✅ Advanced filtering by status, group, or event type

### Participant Management
- ✅ Registration and attendance confirmation
- ✅ Multiple user assignment to events
- ✅ Participation confirmation tracking
- ✅ Attendance cancellation
- ✅ Query all events per user
- ✅ Complete participant listing per event

### User Management
- ✅ Secure authentication with JWT (JSON Web Tokens)
- ✅ Differentiated roles: ADMIN, GROUP_ADMIN, MEMBER
- ✅ Profile management (full name, email, phone)
- ✅ Users linked to community groups
- ✅ Password encryption with BCrypt
- ✅ Uniqueness validation (username and email)

### Community Group Management
- ✅ Creation and administration of groups/committees
- ✅ Geographic information (municipality, neighborhood)
- ✅ Member tracking per group
- ✅ Event association with specific groups
- ✅ Multiple independent groups in a single system

### Security and Documentation
- ✅ JWT authentication with configurable expiration (10 hours)
- ✅ Stateless sessions (no server-side state)
- ✅ SSL/TLS support with PKCS12 keystore
- ✅ CORS configuration for frontend integration
- ✅ Interactive documentation with Swagger/OpenAPI
- ✅ Documented public endpoints

## Technology Stack

### Backend Framework
- **Spring Boot 3.5.3** - Main framework
- **Java 17** - Programming language
- **Maven** - Dependency management and build tool

### Database
- **MySQL 8.0** - Database management system
- **Spring Data JPA** - ORM and database operations
- **Hibernate** - JPA implementation

### Security
- **Spring Security** - Security framework
- **JWT (JJWT 0.9.1)** - Token generation and validation
- **BCrypt** - Password encryption
- **SSL/TLS** - Secure HTTPS communication

### Validation and Utilities
- **Jakarta Validation API 3.0.2** - Data validation
- **Hibernate Validator** - Bean Validation implementation
- **Lombok 1.18.34** - Boilerplate code reduction

### Documentation
- **SpringDoc OpenAPI 2.8.9** - Documentation generation
- **Swagger UI** - Interactive API interface

## Project Architecture

```
src/main/java/mx/edu/utez/rbbackendcomite/
├── config/                  # General configurations
│   ├── ApiResponseDto       # Standardized responses
│   ├── InitialDataConfig    # Initial data (seeding)
│   └── MainSecurity         # Security configuration
├── controller/              # REST controllers
│   ├── auth/               # Authentication
│   ├── event/              # Events
│   ├── eventType/          # Event types
│   ├── group/              # Groups
│   ├── role/               # Roles
│   └── user/               # Users
├── models/                  # Entities and DTOs
│   ├── event/              # Event model
│   ├── eventType/          # Event type model
│   ├── group/              # Group model
│   ├── role/               # Role model
│   ├── user/               # User model
│   └── userHasEvents/      # User-event relationship
├── security/                # Security and JWT
│   ├── filters/            # Authentication filters
│   ├── jwt/                # JWT utilities
│   └── SwaggerSecurity     # Swagger configuration
├── services/                # Business logic
│   ├── auth/               # Authentication service
│   ├── event/              # Event service
│   ├── eventType/          # Event type service
│   ├── group/              # Group service
│   ├── role/               # Role service
│   └── user/               # User service
└── utils/                   # General utilities
    ├── DBConnection
    └── PasswordEncoderService
```

## Data Model

### Main Entities

#### UserEntity (users)
- Personal information: username, password, fullName, phone, email
- Relationships: role, group, participated events

#### EventEntity (events)
- Event data: title, date, status
- Relationships: event type, organizing group, participants

#### GroupEntity (groups)
- Group information: name, municipality, neighborhood
- Relationships: members, organized events

#### EventParticipantEntity (participants)
- Junction table for user-event relationship
- Attendance confirmation field

#### RoleEntity (roles)
- Three predefined roles: ADMIN, GROUP_ADMIN, MEMBER

#### EventTypeEntity (event types)
- Examples: Cleaning, Reforestation, Social Campaign

### Event Statuses
- **PROXIMAMENTE** (Upcoming) - Scheduled event
- **EN_EJECUCION** (In Progress) - Ongoing event
- **FINALIZADO** (Finished) - Completed event

## API Endpoints

### Authentication
```
POST /api/auth - Login (returns JWT token)
```

### Users
```
GET    /api/users                        - List all users
GET    /api/users/{id}                   - Get user by ID
POST   /api/users                        - Create new user
PUT    /api/users/{id}                   - Update user
DELETE /api/users/{id}                   - Delete user
GET    /api/users/usersByRole/{roleId}   - Users by role
GET    /api/users/usersByEvent/{eventId} - Users by event
GET    /api/users/usersByGroup/{groupId} - Users by group
```

### Events
```
GET    /api/event                                              - List all events
GET    /api/event/{id}                                         - Get event by ID
POST   /api/event                                              - Create new event
PUT    /api/event/{id}                                         - Update event
DELETE /api/event/{id}                                         - Delete event
GET    /api/event/status/{status}                              - Events by status
GET    /api/event/group/{groupId}                              - Events by group
GET    /api/event/type/{typeId}                                - Events by type
GET    /api/event/user/{userId}                                - Events for a user
POST   /api/event/setUsersToEvent/{eventId}                    - Assign users to event
PUT    /api/event/attendance/{eventId}/participants/{userId}   - Confirm/cancel attendance
PUT    /api/event/{id}/status                                  - Update event status
```

### Groups
```
GET    /api/groups     - List all groups
GET    /api/groups/{id} - Get group by ID
POST   /api/groups     - Create new group
PUT    /api/groups/{id} - Update group
DELETE /api/groups/{id} - Delete group
```

### Roles
```
GET    /api/roles     - List all roles
GET    /api/roles/{id} - Get role by ID
POST   /api/roles     - Create new role
PUT    /api/roles/{id} - Update role
DELETE /api/roles/{id} - Delete role
```

### Event Types
```
GET    /api/eventType     - List all types
GET    /api/eventType/{id} - Get type by ID
POST   /api/eventType     - Create new type
PUT    /api/eventType/{id} - Update type
DELETE /api/eventType/{id} - Delete type
```

## Installation and Setup

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation Steps

1. **Clone the repository**
```bash
git clone https://github.com/your-username/RBBackendComite.git
cd RBBackendComite
```

2. **Setup the database**
```sql
CREATE DATABASE comite;
```

3. **Configure application.properties**

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/comite
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. **Build the project**
```bash
mvn clean install
```

5. **Run the application**
```bash
mvn spring-boot:run
```

The application will be available at `https://localhost:8443`

### Default Users

The system automatically creates test users:

| Username | Password | Role |
|----------|----------|-----|
| admin | admin123 | ADMIN |
| usuario1 | UserPass123! | GROUP_ADMIN |
| gerente1 | ManagerPass456! | MEMBER |
| invitado | GuestPass789! | GROUP_ADMIN |

## API Documentation

Once the application is running, access:

- **Swagger UI**: `https://localhost:8443/swagger-ui.html`
- **OpenAPI JSON**: `https://localhost:8443/v3/api-docs`

The interactive documentation allows testing all endpoints directly from the browser.

## Security Configuration

### JWT Authentication

1. **Login**: Send credentials to `/api/auth`
```json
{
  "username": "admin",
  "password": "admin123"
}
```

2. **Response**: Receive JWT token
```json
{
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIs...",
    "user": {
      "id": 1,
      "username": "admin",
      "role": "ADMIN"
    }
  }
}
```

3. **Use the token**: Include in header of protected requests
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
```

### Public Endpoints
- `/api/auth` - Authentication
- `/swagger-ui/**` - Documentation
- `/v3/api-docs/**` - OpenAPI specification

## Implemented Validations

- **Username**: 4-20 characters
- **Password**: Minimum 8 characters
- **Email**: Valid email format
- **Phone**: Exactly 10 digits
- **Event date**: Only current or future dates
- **Uniqueness**: Username and email unique in the system

## API Response Structure

All responses follow a standardized format:

```json
{
  "data": { },
  "error": false,
  "message": "Successful operation",
  "status": 200
}
```

## Main Use Cases

### 1. Organizing a Reforestation Event
1. Admin creates an event type "Reforestation"
2. Group Admin creates the event with date, title, and group
3. Users register as participants
4. Participants confirm their attendance
5. Admin updates the event status (Upcoming → In Progress → Finished)

### 2. Community Group Management
1. Admin creates a new group with location
2. Users register and are assigned to the group
3. Group Admin organizes events for their group
4. Group members participate in events

### 3. Participation Tracking
1. User queries available events
2. User registers for events of interest
3. User confirms attendance before the event
4. System records effective participation

## Contributing

Contributions are welcome. Please:

1. Fork the project
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Contact and Support

For questions, suggestions, or to report issues:

- Open an issue on GitHub
- Submit pull request with improvements
- Contact the development team

## Future Roadmap

- [ ] Email notifications
- [ ] Event reminder system
- [ ] Reports and statistics
- [ ] Calendar integration
- [ ] Mobile app (iOS/Android)
- [ ] Points/gamification system
- [ ] Event geolocation
- [ ] Event photo uploads

---

Developed with ❤️ for communities making a difference
