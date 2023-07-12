package com.godeveloper.currencyconverter.service.utilits.ui;

import lombok.Data;

@Data
public class UserModel {

    private String bank;
    private String currency;
    private String time;
    private String number;

    public UserModel() {
        this.currency = "USD";
        this.bank = "Приват";
        this.time = "ВИМКНУТИ СПОВІЩЕННЯ";
        this.number = "2";
    }
}
