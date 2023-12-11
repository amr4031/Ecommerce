-- Schema for Ecommerce
CREATE SCHEMA IF NOT EXISTS ecommerce;

INSERT IGNORE INTO ecommerce.product (product_name, product_price, image_url)
VALUES
    ('Maramox Board Pro 60 X 125 X 0.4', '183', 'https://i.postimg.cc/MpM7xkq7/1.png'),
    ('Maramox Board Pro 60 X 250 X 1', '193', 'https://i.postimg.cc/MpM7xkq7/1.png'),
    ('Maramox Board Pro 60 X 250 X 1.25', '222', 'https://i.postimg.cc/MpM7xkq7/1.png');


INSERT IGNORE INTO ecommerce.customer (user_name, mobile, password) VALUES
('admin', '1234567890', 'admin');
