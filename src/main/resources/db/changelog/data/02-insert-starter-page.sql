--liquibase formatted sql

--changeset kyra:02-insert-starter-page context:dev

INSERT INTO tasks (title, description, status, priority, due_date, created_at)
VALUES
    ('Configure Spring Security', 'Implement JWT-based authentication and authorization.', 'IN_PROGRESS', 'HIGH', '2025-11-12', CURRENT_DATE),

    ('Set Up CI/CD', 'Create GitHub Actions pipeline for automated builds and tests.', 'NOT_STARTED', 'HIGH', '2025-11-25', CURRENT_DATE),

    ('Add Validation Layer', 'Use Jakarta Bean Validation for input validation in REST controllers.', 'PENDING', 'MEDIUM', '2025-11-13', CURRENT_DATE),

    ('Integrate MapStruct', 'Add MapStruct mappers to convert entities to DTOs efficiently.', 'COMPLETED', 'LOW', '2025-11-02', CURRENT_DATE),

    ('Implement Global Exception Handling', 'Use @RestControllerAdvice to standardize error responses.', 'PENDING', 'MEDIUM', '2025-11-09', CURRENT_DATE),

    ('Optimize Database Indexes', 'Analyze query performance and add necessary indexes to PostgreSQL.', 'NOT_STARTED', 'LOW', '2025-11-30', CURRENT_DATE),

    ('Add Server-Sent Events (SSE)', 'Push real-time task updates to frontend using Spring SSE.', 'NOT_STARTED', 'HIGH', '2025-11-28', CURRENT_DATE),

    ('Implement Task Sorting & Filtering', 'Add support for sorting tasks by priority and status.', 'IN_PROGRESS', 'MEDIUM', '2025-11-14', CURRENT_DATE),

    ('Enhance API Documentation', 'Add Swagger/OpenAPI documentation for all REST endpoints.', 'PENDING', 'LOW', '2025-11-22', CURRENT_DATE),

    ('Implement Email Notifications', 'Send email reminders for tasks nearing due date.', 'NOT_STARTED', 'HIGH', '2025-11-27', CURRENT_DATE),

    ('Write Unit Tests', 'Add JUnit and Mockito tests for service and controller layers.', 'IN_PROGRESS', 'HIGH', '2025-11-18', CURRENT_DATE),

    ('Containerize Application', 'Create Dockerfile and docker-compose setup for local development.', 'PENDING', 'MEDIUM', '2025-11-16', CURRENT_DATE),

    ('Implement Caching', 'Use Spring Cache abstraction for frequently accessed data.', 'NOT_STARTED', 'LOW', '2025-12-01', CURRENT_DATE),

    ('Integrate Flyway Migrations', 'Experiment with Flyway migrations for versioned schema updates.', 'COMPLETED', 'LOW', '2025-11-01', CURRENT_DATE),

    ('Create Project README', 'Document project setup, structure, and contribution guidelines.', 'IN_PROGRESS', 'MEDIUM', '2025-11-11', CURRENT_DATE),

    ('Deploy to Cloud', 'Deploy the application to Render or Railway using PostgreSQL backend.', 'NOT_STARTED', 'HIGH', '2025-11-29', CURRENT_DATE);
