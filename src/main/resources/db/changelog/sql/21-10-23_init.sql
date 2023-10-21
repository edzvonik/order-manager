--liquibase formatted sql

--changeSet edzvonik:1
CREATE TABLE "Order" (
	id INT PRIMARY KEY NOT NULL,
	order_date DATE NOT NULL
);

CREATE TABLE "Product" (
	id INT PRIMARY KEY NOT NULL,
	name VARCHAR NOT NULL,
	price DECIMAL NOT NULL
);

CREATE TABLE "OrderItem" (
	id INT PRIMARY KEY NOT NULL,
	order_id INT NOT NULL,
	product_id INT NOT NULL,
	quantity INT NOT NULL,
	total_price DECIMAL NOT NULL,
	CONSTRAINT fk_orderItem_order
		FOREIGN KEY (order_id) 
		REFERENCES "Order"(id),
	CONSTRAINT fk_orderItem_product
		FOREIGN KEY (product_id) 
		REFERENCES "Product"(id)
);