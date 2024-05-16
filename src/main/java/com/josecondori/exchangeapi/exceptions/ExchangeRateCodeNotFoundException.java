package com.josecondori.exchangeapi.exceptions;

public class ExchangeRateCodeNotFoundException extends Exception {
    public ExchangeRateCodeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
