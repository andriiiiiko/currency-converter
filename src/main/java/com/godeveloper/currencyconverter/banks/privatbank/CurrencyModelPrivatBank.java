package com.godeveloper.currencyconverter.banks.privatbank;

import lombok.Data;

@Data
public class CurrencyModelPrivatBank {
    private final String ccy;
    private final String base_ccy;
    private final String buy;
    private final String sale;
}