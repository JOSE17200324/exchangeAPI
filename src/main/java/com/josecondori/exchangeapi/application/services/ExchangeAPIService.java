package com.josecondori.exchangeapi.application.services;

import com.josecondori.exchangeapi.application.exceptions.ExchangeRateCodeNotFoundException;
import com.josecondori.exchangeapi.application.services.feigns.ExchangeRateAPIClient;
import com.josecondori.exchangeapi.infrastructure.db.springdata.dbo.entities.ExchangeRateData;
import com.josecondori.exchangeapi.domain.models.enums.ExchangeRateEnum;
import com.josecondori.exchangeapi.domain.models.enums.ExchangeRateErrorMessageEnum;
import com.josecondori.exchangeapi.infrastructure.rest.spring.mappers.ExchangeRateDataMapper;
import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.response.ExchangeRateAPIRs;
import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.request.ExchangeRateDataRq;
import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.response.ExchangeRateDataRs;
import com.josecondori.exchangeapi.application.repositories.ExchangeRateDataRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeAPIService {
    private final ExchangeRateAPIClient exchangeRateAPIClient;
    private final ExchangeRateDataRepository repository;
    private static final ExchangeRateDataMapper mapper = Mappers.getMapper(ExchangeRateDataMapper.class);

    public ExchangeRateDataRs applyExchangeRate(ExchangeRateDataRq dataRq) throws ExchangeRateCodeNotFoundException {

        // Validate exchange rate code
        Arrays.stream(ExchangeRateEnum.values()).filter(rate ->
                rate.name().equals(dataRq.getFromCurrency())).findFirst().orElseThrow(() -> new ExchangeRateCodeNotFoundException(ExchangeRateErrorMessageEnum.INVALID_EXCHANGE_RATE_CODE.getMessage()));

        ExchangeRateAPIRs exchangeRateAPIRs = exchangeRateAPIClient.getExchangeByCode(dataRq.getFromCurrency().toUpperCase());

        // Calculate exchange rate
        Double exchangeRate = exchangeRateAPIRs.getConversion_rates().get(dataRq.getToCurrency().toUpperCase());

        // Save data
        ExchangeRateData exchangeRateData = repository.save(ExchangeRateData.builder()
                        .amount(dataRq.getAmount())
                        .amountExchangeRate(dataRq.getAmount() >= 0 ? dataRq.getAmount() * exchangeRate: 0)
                        .fromCurrency(dataRq.getFromCurrency())
                        .toCurrency(dataRq.getToCurrency())
                        .exchangeRate(exchangeRate)
                .build());

        return mapper.toExchangeRateDataRs(exchangeRateData);
    }

    public List<ExchangeRateDataRs> getAllExchangesRateData() {
        return repository.findAll().stream().map(mapper::toExchangeRateDataRs).toList();
    }
}
