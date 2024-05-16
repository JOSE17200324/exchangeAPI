package com.josecondori.exchangeapi.controllers;

import com.josecondori.exchangeapi.application.controllers.ExchangeRateController;
import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.request.ExchangeRateDataRq;
import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.response.ExchangeRateDataRs;
import com.josecondori.exchangeapi.application.services.ExchangeAPIService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class ExchangeRateControllerTest {
    @Mock
    ExchangeAPIService exchangeAPIService;
    @InjectMocks
    ExchangeRateController exchangeRateController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAmount() throws Exception {
        ExchangeRateDataRs response = new ExchangeRateDataRs(100.0, 367.25, "USD", "AED", 3.6725);
        when(exchangeAPIService.applyExchangeRate(any())).thenReturn(response);

        ExchangeRateDataRq request = new ExchangeRateDataRq(100.0, "USD", "AED");
        ExchangeRateDataRs result = exchangeRateController.getAmount(request);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getAmountExchangeRate(), 3.6725 * 100, 0.0001);
    }

    @Test
    public void testGetAllExchangesData() throws Exception {
        ExchangeRateDataRs data1 = new ExchangeRateDataRs(10.0, 36.6725, "USD", "AED", 3.6725);
        ExchangeRateDataRs data2 = new ExchangeRateDataRs(100.0, 366.725, "USD", "AED", 3.6725);

        when(exchangeAPIService.getAllExchangesRateData()).thenReturn(List.of(data1, data2));

        List<ExchangeRateDataRs> result = exchangeRateController.getAllExchangesData();

        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(List.of(data1, data2).size(), result.size());
    }
}