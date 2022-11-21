package com.epam.clothes.exception;

public class ClothNotFoundException extends RuntimeException {

    public ClothNotFoundException(long id) {
        super("Cloth not found with id: " + id);
    }
}
