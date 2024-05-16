package com.josecondori.exchangeapi.infrastructure.resources.handler;

import com.josecondori.exchangeapi.application.exceptions.ExchangeRateCodeNotFoundException;
import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.response.CustomErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ExchangeRateCodeNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> badRequestHandler(ExchangeRateCodeNotFoundException err) {
        LOGGER.error("badRequestHandler - message {}", err.getMessage());
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(err.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
