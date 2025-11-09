--liquibase formatted sql

--changeset kyra:01-insert-starter-values context:dev
INSERT INTO tasks (title, description, status, priority, due_date, created_at)
VALUES
    ('Set up Liquibase', 'Initialize Liquibase and create SQL changelogs for project setup.', 'PENDING', 'HIGH', '2025-11-10', CURRENT_DATE),

    ('Implement REST APIs', 'Build CRUD endpoints for managing tasks in Spring Boot.', 'IN_PROGRESS', 'MEDIUM', '2025-11-15', CURRENT_DATE),

    ('Add Frontend Integration', 'Connect backend APIs with frontend via REST calls.', 'NOT_STARTED', 'LOW', '2025-11-20', CURRENT_DATE),

    ('Code Review & Refactor', 'Review code for best practices and optimize SQL queries.', 'COMPLETED', 'LOW', '2025-11-05', CURRENT_DATE);
