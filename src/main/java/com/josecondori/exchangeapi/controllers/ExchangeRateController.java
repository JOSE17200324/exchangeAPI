package com.josecondori.exchangeapi.controllers;

import com.josecondori.exchangeapi.exceptions.ExchangeRateCodeNotFoundException;
import com.josecondori.exchangeapi.models.request.ExchangeRateDataRq;
import com.josecondori.exchangeapi.models.response.ExchangeRateDataRs;
import com.josecondori.exchangeapi.services.ExchangeAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final ExchangeAPIService exchangeAPIService;

    @GetMapping
    public ExchangeRateDataRs getAmount(@RequestBody ExchangeRateDataRq dataRq) throws ExchangeRateCodeNotFoundException {
        return exchangeAPIService.applyExchangeRate(dataRq);
    }

}
