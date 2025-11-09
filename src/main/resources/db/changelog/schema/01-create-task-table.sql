
--liquibase formatted sql

--changeset kyra:01-create-tasks-table
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50),
    priority VARCHAR(50),
    due_date DATE,
    created_at DATE DEFAULT CURRENT_DATE
);
