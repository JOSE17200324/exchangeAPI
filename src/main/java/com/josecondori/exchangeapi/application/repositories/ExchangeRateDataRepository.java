package com.josecondori.exchangeapi.application.repositories;

import com.josecondori.exchangeapi.infrastructure.db.springdata.dbo.entities.ExchangeRateData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateDataRepository extends CrudRepository<ExchangeRateData, Long> {
    ExchangeRateData save(ExchangeRateData entity);
    List<ExchangeRateData> findAll();
}
