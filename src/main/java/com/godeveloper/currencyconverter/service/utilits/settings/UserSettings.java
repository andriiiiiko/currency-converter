package com.godeveloper.currencyconverter.service.utilits.settings;

import lombok.Data;

@Data
public class UserSettings {

    private String bank;
    private String currency;
    private String time;
    private String number;

    public UserSettings() {
        this.currency = "USD";
        this.bank = "Приват";
        this.time = "Виключити сповіщення";
        this.number = "2";
    }
}
