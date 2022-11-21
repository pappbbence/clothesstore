package com.epam.clothes.controller;

import com.epam.clothes.exception.ApiError;
import com.epam.clothes.exception.ClothNotFoundException;
import com.epam.clothes.exception.InvalidClothRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClothControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClothNotFoundException.class)
    public ApiError clothNotFoundHandler(ClothNotFoundException e) {
        return new ApiError(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidClothRequestException.class)
    public ApiError invalidClothRequestHandler(InvalidClothRequestException e) {
        return new ApiError(e.getMessage());
    }
}
