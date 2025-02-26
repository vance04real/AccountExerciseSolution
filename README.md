# Sanlam Bank Account Withdrawal Service

## Overview
This project implements a banking service that handles account withdrawals and publishes withdrawal events. The implementation follows modern software development principles including Clean Architecture, Domain-Driven Design, and proper error handling.

## Features
- Account balance retrieval and validation
- Secure withdrawal processing
- Asynchronous event publishing to AWS SNS
- Comprehensive error handling

## Technical Stack
- **Java 17**
- **Spring Boot** - Core application framework
- **Spring Data JPA** - Data access layer with Hibernate
- **PostgreSQL** - Relational database
- **AWS SDK for Java v2** - For SNS integration

## Project Structure
```
src/
├── main/
│   ├── java/za/sanlam/fintech/accountexercisesolution/
│   │   ├── config/           # Configuration classes
│   │   ├── controllers/      # REST controllers and DTOs
│   │   ├── domain/entity     # Domain models and JPA entities
│   │   ├── enums/            # Enums used in the application
│   │   ├── mapper/           # Entity-Domain mappers
│   │   ├── exceptions/       # Custom exception classes 
│   │   └── event/            # event publishers
│   │   └── mappers/          # DTO-Entity mappers
│   │   └── repository/       # Data access repositories
│   │   └── service/          # Business services and event publishers
│   └── resources/
│       ├── application.yml   # Application configuration
│       └── db/migration/     # Database migrations
└── test/                     # Test classes
```

## Improvements Made

### Architecture & Design
- Implemented Clean Architecture with proper separation of concerns
- Created rich domain models with encapsulated business logic
- Applied Repository pattern for data access abstraction
- Used DTOs for API request/response isolation
- Included balance information in withdrawal responses

### Transaction Management & Consistency
- Added proper transactional boundaries

### Error Handling & Resilience
- Implemented comprehensive exception handling
- Added retry mechanism for event publishing
- Created global exception handler for REST controllers

### Dependency Management
- Used constructor injection for better testability
- Created proper AWS configuration
- Externalized configuration properties

### Observability & Auditability
- Added comprehensive logging throughout
- Included transaction IDs for traceability
- Added timestamps for audit purposes

## Libraries Used

| Library | Purpose |
|---------|---------|
| Spring Boot | Application framework and dependency injection |
| Spring Data JPA | Data access abstraction with reduced boilerplate |
| Hibernate | ORM implementation used by Spring Data JPA |
| AWS SDK for Java v2 | Integration with AWS services (SNS) |
| Jackson | JSON serialization/deserialization |
| SLF4J | Logging facade |
| Spring Retry | Implementing retry logic for failed operations |

## Configuration

The application requires the following environment variables:

```
BANK_DB_PASSWORD=your_database_password
AWS_ACCOUNT_ID=your_aws_account_id
```

Key application properties (see `application.yml`):

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/bankdb
    username: bankuser
    
  jpa:
    hibernate:
      ddl-auto: validate
    open-in-view: false
  
bank:
  aws:
    region: us-west-2
    withdrawal-topic-arn: arn:aws:sns:${bank.aws.region}:${bank.aws.account-id}:withdrawal-events
```

## API Endpoints

### Withdraw Funds
```
POST /api/v1/bank/account/withdraw
```

Request body:
```json
{
  "accountId": 12345,
  "amount": 100.00
}
```

Response:
```json
{
  "status": "SUCCESSFUL",
  "message": "Withdrawal successful",
  "transactionId": "550e8400-e29b-41d4-a716-446655440000",
  "balance": 900.00,
  "timestamp": "2022-01-01T12:00:00Z"
}
```

## Future Improvements

### Enhanced Transaction Management
- [ ] Implemented optimistic locking for concurrency control
- [ ] Provided idempotency support via request keys

### Enhanced Error Handling
- [ ] Implement a dead letter queue for failed events
- [ ] Add circuit breaker patterns for external service calls

### Improved Scalability
- [ ] Implement caching with Redis
- [ ] Add read/write splitting for higher throughput

### Security Enhancements
- [ ] Implement API authentication and authorization
- [ ] Add request validation and sanitization

### Monitoring & Observability
- [ ] Add metrics collection (Micrometer/Prometheus)
- [ ] Implement distributed tracing

### Testing
- [ ] Add comprehensive unit and integration tests
- [ ] Implement contract testing for API endpoints

## Getting Started

### Prerequisites
- Java 17
- Maven
- PostgreSQL database
- AWS account with SNS access

### Running Locally
1. Clone the repository
2. Set required environment variables
3. Run `mvn spring-boot:run`

### Database Setup
The application uses Flyway for database migrations. The initial schema is created automatically when the application starts.

## License
This project is licensed under the Sanlam Fintech License - see the LICENSE file for details.
