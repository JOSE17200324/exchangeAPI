package com.josecondori.exchangeapi.services;

import com.josecondori.exchangeapi.exceptions.ExchangeRateCodeNotFoundException;
import com.josecondori.exchangeapi.feigns.ExchangeRateAPIClient;
import com.josecondori.exchangeapi.models.enums.ExchangeRateEnum;
import com.josecondori.exchangeapi.models.enums.ExchangeRateErrorMessageEnum;
import com.josecondori.exchangeapi.models.response.ExchangeRateAPIRs;
import com.josecondori.exchangeapi.models.request.ExchangeRateDataRq;
import com.josecondori.exchangeapi.models.response.ExchangeRateDataRs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ExchangeAPIService {
    private final ExchangeRateAPIClient exchangeRateAPIClient;

    public ExchangeRateDataRs applyExchangeRate(ExchangeRateDataRq dataRq) throws ExchangeRateCodeNotFoundException {

        // Validate exchange rate code
        Arrays.stream(ExchangeRateEnum.values()).filter(rate ->
                rate.name().equals(dataRq.getFromCurrency())).findFirst().orElseThrow(() -> new ExchangeRateCodeNotFoundException(ExchangeRateErrorMessageEnum.INVALID_EXCHANGE_RATE_CODE.getMessage()));

        ExchangeRateAPIRs exchangeRateAPIRs = exchangeRateAPIClient.getExchangeByCode(dataRq.getFromCurrency().toUpperCase());

        Double exchangeRate = exchangeRateAPIRs.getConversion_rates().get(dataRq.getToCurrency().toUpperCase());

        return ExchangeRateDataRs.builder()
                .amount(dataRq.getAmount())
                .amountExchangeRate(dataRq.getAmount() * exchangeRate)
                .fromCurrency(dataRq.getFromCurrency())
                .toCurrency(dataRq.getToCurrency())
                .exchangeRate(exchangeRate)
                .build();
    }
}
