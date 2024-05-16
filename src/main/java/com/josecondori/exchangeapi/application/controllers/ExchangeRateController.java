package com.josecondori.exchangeapi.application.controllers;

import com.josecondori.exchangeapi.application.exceptions.ExchangeRateCodeNotFoundException;
import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.request.ExchangeRateDataRq;
import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.response.ExchangeRateDataRs;
import com.josecondori.exchangeapi.application.services.ExchangeAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class ExchangeRateController {
    private final ExchangeAPIService exchangeAPIService;

    @GetMapping
    public ExchangeRateDataRs getAmount(@RequestBody ExchangeRateDataRq dataRq) throws ExchangeRateCodeNotFoundException {
        return exchangeAPIService.applyExchangeRate(dataRq);
    }

    @GetMapping(path = "/all")
    public List<ExchangeRateDataRs> getAllExchangesData() {
        return exchangeAPIService.getAllExchangesRateData();
    }

}
