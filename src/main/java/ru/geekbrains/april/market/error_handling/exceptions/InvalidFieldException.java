package ru.geekbrains.april.market.error_handling.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidFieldException extends RuntimeException {
    private List<String> errors;

    public InvalidFieldException(String message) {
        super(message);
    }

    public InvalidFieldException(String stackTrace, List<String> messages) {
        super(stackTrace);
        this.errors = messages;
    }
}
