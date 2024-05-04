USE `employees-db`;

DROP TABLE IF EXISTS employees;

CREATE TABLE IF NOT EXISTS employees (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    employee_id VARCHAR(36) UNIQUE,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    job_title VARCHAR(100),
    department VARCHAR(100),
    salary DECIMAL(10, 2),
    INDEX idx_employee_id (employee_id)
    );