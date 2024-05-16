package com.josecondori.exchangeapi.infrastructure.db.springdata.dbo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double amount;
    @Column
    private Double amountExchangeRate;
    @Column
    private String fromCurrency;
    @Column
    private String toCurrency;
    @Column
    private Double exchangeRate;
}
