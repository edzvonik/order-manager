--liquibase formatted sql

--changeSet edzvonik:1
CREATE TABLE "order" (
	id BIGINT PRIMARY KEY NOT NULL,
	order_date DATE NOT NULL
);

CREATE TABLE product (
	id BIGINT PRIMARY KEY NOT NULL,
	name VARCHAR NOT NULL,
	price DECIMAL NOT NULL
);

CREATE TABLE order_item (
	id BIGINT PRIMARY KEY NOT NULL,
	order_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	quantity INT NOT NULL,
	total_price DECIMAL NOT NULL,
	CONSTRAINT fk_orderItem_order
		FOREIGN KEY (order_id) 
		REFERENCES "order"(id),
	CONSTRAINT fk_orderItem_product
		FOREIGN KEY (product_id) 
		REFERENCES "product"(id)
);

CREATE SEQUENCE order_id_seq START 21;

CREATE SEQUENCE product_id_seq START 10;

CREATE SEQUENCE order_item_id_seq START 40;