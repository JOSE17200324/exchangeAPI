package com.josecondori.exchangeapi.infrastructure.rest.spring.mappers;

import com.josecondori.exchangeapi.infrastructure.db.springdata.dbo.entities.ExchangeRateData;
import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.response.ExchangeRateDataRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ExchangeRateDataMapper {
    
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "amountExchangeRate", source = "amountExchangeRate")
    @Mapping(target = "fromCurrency", source = "fromCurrency")
    @Mapping(target = "toCurrency", source = "toCurrency")
    @Mapping(target = "exchangeRate", source = "exchangeRate")
    ExchangeRateDataRs toExchangeRateDataRs(ExchangeRateData exchangeRateData);
}
