package com.example.spectacle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SpectacleNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SpectacleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String spectacleNotFoundHandler(SpectacleNotFoundException e){
        return e.getMessage();
    }
}
