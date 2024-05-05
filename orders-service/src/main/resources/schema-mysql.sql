USE `orders-db`;

DROP TABLE IF EXISTS orders;

CREATE TABLE IF NOT EXISTS orders (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id VARCHAR(36) UNIQUE,
    product_id VARCHAR(36),
    customer_id VARCHAR(36),
    employee_id VARCHAR(36),
    delivery_status VARCHAR(50),
    shipping_price DECIMAL(10, 2),
    total_price DECIMAL(10, 2)
    );