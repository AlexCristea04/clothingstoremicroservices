USE `products-db`;

DROP TABLE IF EXISTS products;

CREATE TABLE IF NOT EXISTS products (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(36) UNIQUE,
    name VARCHAR(50),
    description VARCHAR(300),
    size VARCHAR(50),
    price DECIMAL(10, 2),
    quantity INTEGER,
    status VARCHAR(50),
    INDEX idx_product_id (product_id)
    );