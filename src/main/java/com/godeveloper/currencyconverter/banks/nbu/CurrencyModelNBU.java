package com.godeveloper.currencyconverter.banks.nbu;

import lombok.Data;

@Data
public class CurrencyModelNBU {
    private final int r030;
    private final String txt;
    private final float rate;
    private final String cc;
    private final String exchangedate;
}
