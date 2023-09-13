package com.example.apigateway.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlers {

    //Este metodo responde a excepciones de tipo "Exception", es decir excepciones genericas
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> handlerExceptions(Exception exception) {
        var map = Map.of(
                "message", exception.getMessage(),
                "localizedMessage", exception.getLocalizedMessage()
        );

        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handlerExceptions(RuntimeException exception) {
        var map = Map.of(
                "message", exception.getMessage(),
                "localizedMessage", exception.getLocalizedMessage()
        );

        return ResponseEntity.badRequest().body(map);
    }


}
