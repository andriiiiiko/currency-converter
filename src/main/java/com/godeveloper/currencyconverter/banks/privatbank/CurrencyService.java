package com.godeveloper.currencyconverter.banks.privatbank;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CurrencyService {
    private static final String BASE_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=11";
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    public static List<CurrencyModelPrivatBank> getCurrencyRate() {
        HttpGet request = new HttpGet(BASE_URL);

        try {
            HttpResponse response = HTTP_CLIENT.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                List<CurrencyModelPrivatBank> currencyList = GSON.fromJson(responseBody, new TypeToken<List<CurrencyModelPrivatBank>>() {}.getType());
                return currencyList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getCurrencyInformation(String currency) {
        List<CurrencyModelPrivatBank> currencyList = getCurrencyRate();
        StringBuilder result = new StringBuilder();
        if (currencyList != null) {
            for (CurrencyModelPrivatBank currencyModelPrivatbank : currencyList) {
                if (currencyModelPrivatbank.getCcy().equals(currency)) {
                    result.append("Курси в Приватбанк: ")
                            .append(currencyModelPrivatbank.getCcy())
                            .append("/")
                            .append(currencyModelPrivatbank.getBase_ccy())
                            .append("\n").append("Купівля: ")
                            .append(currencyModelPrivatbank.getBuy()).append("\n")
                            .append("Продаж: ")
                            .append(currencyModelPrivatbank.getSale());
                }
            }
        }

        return result.toString();
    }
}