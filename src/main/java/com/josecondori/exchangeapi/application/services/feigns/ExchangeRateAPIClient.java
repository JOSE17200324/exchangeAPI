package com.josecondori.exchangeapi.application.services.feigns;

import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.response.ExchangeRateAPIRs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchange-service", url = "https://v6.exchangerate-api.com", path = "v6/5f4113bc1dfca65a3a7c40d7/latest")
public interface ExchangeRateAPIClient {

    @GetMapping(path = "/{code}")
    ExchangeRateAPIRs getExchangeByCode(@PathVariable("code") String code);
}
