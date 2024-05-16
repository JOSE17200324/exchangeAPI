package com.josecondori.exchangeapi.infrastructure.rest.spring.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateDataRq {
    private Double amount;
    private String fromCurrency;
    private String toCurrency;
}
