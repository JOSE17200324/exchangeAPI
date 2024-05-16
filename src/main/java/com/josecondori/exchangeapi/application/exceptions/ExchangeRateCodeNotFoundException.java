package com.josecondori.exchangeapi.application.exceptions;

public class ExchangeRateCodeNotFoundException extends Exception {
    public ExchangeRateCodeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
