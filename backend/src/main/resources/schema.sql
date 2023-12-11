-- Schema for Ecommerce
CREATE SCHEMA IF NOT EXISTS ecommerce;

-- Product Table
CREATE TABLE IF NOT EXISTS ecommerce.product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL UNIQUE,
    product_price VARCHAR(255) NOT NULL,
    image_url VARCHAR(255)
);

-- Customer Table
CREATE TABLE IF NOT EXISTS ecommerce.customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL UNIQUE,
    mobile VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Bill Table
CREATE TABLE IF NOT EXISTS ecommerce.bill (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    sale_date DATE,
    total_price DOUBLE,
    discount DOUBLE,
    payment_type VARCHAR(255),
    address1 VARCHAR(255),
    address2 VARCHAR(255),
    city VARCHAR(255),
    zip_code VARCHAR(255),
    card_number VARCHAR(255),
    card_holder_name VARCHAR(255),
    expiry_date VARCHAR(255),
    cvv VARCHAR(255)
);

-- Cart Table
CREATE TABLE IF NOT EXISTS ecommerce.cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT,
    product_name VARCHAR(255),
    added_date DATE,
    image_url VARCHAR(255),
    product_price VARCHAR(255)
);
