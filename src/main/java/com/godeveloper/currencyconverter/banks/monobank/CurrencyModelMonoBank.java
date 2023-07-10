package com.godeveloper.currencyconverter.banks.monobank;

import lombok.Data;

@Data
public class CurrencyModelMonoBank {
    private int currencyCodeA;
    private int currencyCodeB;
    private int date;
    private float rateBuy;
    private float rateCross;
    private float rateSell ;
}
