package com.godeveloper.currencyconverter.banks.nbu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.List;

public class CurrencyServiceNBU {

    private static final String BASE_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    public static List<CurrencyModelNBU> getCurrencyRate() {
        HttpGet request = new HttpGet(BASE_URL);

        try {
            HttpResponse response = HTTP_CLIENT.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                List<CurrencyModelNBU> currencyList = GSON.fromJson(responseBody, new TypeToken<List<CurrencyModelNBU>>() {}.getType());

                return currencyList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getCurrencyInformation(String currency) {
        List<CurrencyModelNBU> currencyList = getCurrencyRate();

        if (currencyList != null) {
            for (CurrencyModelNBU currencyModelNBU : currencyList) {
                if (currencyModelNBU.getCc().equals(currency)) {
                    return "Курси в NBU: " + currency + "/UAN" + "\n" +
                            "Купівля: " + currencyModelNBU.getRate() + "\n" +
                            "Продаж: " + currencyModelNBU.getRate();
                }
            }
        }

        return null;
    }
}