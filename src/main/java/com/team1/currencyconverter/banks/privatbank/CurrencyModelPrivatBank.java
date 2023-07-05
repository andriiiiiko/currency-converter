package com.team1.currencyconverter.banks.privatbank;

public class CurrencyModelPrivatBank {

    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;

    public CurrencyModelPrivatBank(String ccy, String base_ccy, String buy, String sale) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.buy = buy;
        this.sale = sale;
    }

    public String getCcy() {
        return ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public String getBuy() {
        return buy;
    }

    public String getSale() {
        return sale;
    }

    @Override
    public String toString() {
        return "{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy='" + buy + '\'' +

                ", sale='" + sale + '\'' +
                '}';
    }
}