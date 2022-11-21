package com.epam.clothes.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidClothRequestException extends RuntimeException {

    private final List<String> errors;

    public InvalidClothRequestException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
