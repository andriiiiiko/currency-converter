package com.godeveloper.currencyconverter.banks.privatbank;

import lombok.Data;

@Data
public class CurrencyModelPrivatBank {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}