package com.josecondori.exchangeapi.infrastructure.rest.spring.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class CustomErrorResponse {
    private final LocalDateTime date = LocalDateTime.now();

    private final HttpStatus status;

    private final String message;

    public CustomErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
