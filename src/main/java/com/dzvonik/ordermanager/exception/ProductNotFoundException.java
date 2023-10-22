package com.dzvonik.ordermanager.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productId) {
        super("Product not found with ID: " + productId);
    }

}