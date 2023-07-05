package com.team1.currencyconverter.banks.monobank;

public class CurrencyModelMonobank {

    private final int currencyCodeA;
    private final int currencyCodeB;
    private final int date;
    private final float rateBuy;
    private final float rateCross;
    private final float rateSell ;

    public CurrencyModelMonobank(int currencyCodeA, int currencyCodeB, int date, float rateBuy, float rateCross, float rateSell) {
        this.currencyCodeA = currencyCodeA;
        this.currencyCodeB = currencyCodeB;
        this.date = date;
        this.rateBuy = rateBuy;
        this.rateCross = rateCross;
        this.rateSell = rateSell;
    }

    public int getCurrencyCodeA() {
        return currencyCodeA;
    }

    public int getCurrencyCodeB() {
        return currencyCodeB;
    }

    public int getDate() {
        return date;
    }

    public float getRateBuy() {
        return rateBuy;
    }

    public float getRateCross() {
        return rateCross;
    }

    public float getRateSell() {
        return rateSell;
    }

    @Override
    public String toString() {
        return "{" +
                "currencyCodeA=" + currencyCodeA +
                ", currencyCodeB=" + currencyCodeB +
                ", date=" + date +
                ", rateBuy=" + rateBuy +
                ", rateCross=" + rateCross +
                ", rateSell=" + rateSell +
                '}';
    }
}