package com.team1.currencyconverter.banks.privatbank;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CurrencyService {
    private static final String BASE_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=11";
    private static final Gson gson = new Gson();
    private static final HttpClient httpClient = HttpClients.createDefault();
    public static List<CurrencyModelPrivatbank> getCurrencyRate() {
        HttpGet request = new HttpGet(BASE_URL);
        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                CurrencyModelPrivatbank[] tasks = gson.fromJson(responseBody, CurrencyModelPrivatbank[].class);
                return Arrays.asList(tasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getCurrencyInformation(String currency) {
        List<CurrencyModelPrivatbank> currencyList = getCurrencyRate();
        if (currencyList != null) {
            for (CurrencyModelPrivatbank currencyModelPrivatbank : currencyList) {
                if (currencyModelPrivatbank.getCcy().equals(currency)) {
                    return "Курси в Приватбанк: " + currencyModelPrivatbank.getCcy() + "/" + currencyModelPrivatbank.getBase_ccy() + "\n" +
                            "Купівля: " + currencyModelPrivatbank.getBuy() + "\n" +
                            "Продаж: " + currencyModelPrivatbank.getSale();
                }
            }
        }
        return null;
    }
}