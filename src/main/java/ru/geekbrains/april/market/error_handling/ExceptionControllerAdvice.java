package ru.geekbrains.april.market.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geekbrains.april.market.error_handling.exceptions.InvalidFieldException;
import ru.geekbrains.april.market.error_handling.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        MarketError err = new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidFieldException(InvalidFieldException e) {
        MarketError err = new MarketError(HttpStatus.BAD_REQUEST.value(), e.getErrors());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
