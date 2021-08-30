DROP TABLE IF EXISTS article;
CREATE TABLE article (
    article_id BIGINT PRIMARY KEY,
    name VARCHAR(250)
);

DROP TABLE IF EXISTS inventory;
CREATE TABLE inventory (
    article_id BIGINT PRIMARY KEY,
    stock INT
);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    product_id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(250)
);

DROP SEQUENCE IF EXISTS product_id_schema;
CREATE SEQUENCE product_id_schema START WITH 1 INCREMENT BY 1;

DROP TABLE IF EXISTS product_article_rel;
CREATE TABLE product_article_rel (
    product_id BIGINT,
    article_id BIGINT,
    quantity BIGINT
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    order_id VARCHAR(250),
    product_id BIGINT,
    quantity INT,
    created_timestamp TIMESTAMP DEFAULT now()
);
