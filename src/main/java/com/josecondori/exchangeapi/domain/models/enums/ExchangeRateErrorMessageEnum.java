package com.josecondori.exchangeapi.domain.models.enums;

public enum ExchangeRateErrorMessageEnum {
    INVALID_EXCHANGE_RATE_CODE("Enter a correct exchange rate code");
    private String message;

    ExchangeRateErrorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

