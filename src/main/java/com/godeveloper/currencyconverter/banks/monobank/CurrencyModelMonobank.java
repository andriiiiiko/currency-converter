package com.godeveloper.currencyconverter.banks.monobank;

import lombok.Data;

@Data
public class CurrencyModelMonobank {

    private final int currencyCodeA;
    private final int currencyCodeB;
    private final int date;
    private final float rateBuy;
    private final float rateCross;
    private final float rateSell ;
}