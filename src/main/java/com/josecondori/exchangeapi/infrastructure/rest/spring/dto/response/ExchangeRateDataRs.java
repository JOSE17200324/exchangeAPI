package com.josecondori.exchangeapi.infrastructure.rest.spring.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateDataRs {
    private Double amount;
    private Double amountExchangeRate;
    private String fromCurrency;
    private String toCurrency;
    private Double exchangeRate;
}
