package com.customer.app.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidSuperHeroIdException extends RuntimeException {

    public InvalidSuperHeroIdException(HttpStatus badRequest, String message) {
        super(message);
    }
}