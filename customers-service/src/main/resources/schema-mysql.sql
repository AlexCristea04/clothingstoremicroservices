USE `customers-db`;

DROP TABLE IF EXISTS customers;

CREATE TABLE IF NOT EXISTS customers (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(36) UNIQUE,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    email_address VARCHAR(50),
    street_address VARCHAR(50),
    postal_code VARCHAR(50),
    city VARCHAR(50),
    province VARCHAR(50),
    INDEX idx_customer_id (customer_id)
    );
