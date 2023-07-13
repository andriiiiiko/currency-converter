package com.godeveloper.currencyconverter.banks.monobank;

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

public class CurrencyServiceMonoBank {

    private static final String BASE_URL = "https://api.monobank.ua/bank/currency";
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();
    private static List<CurrencyModelMonoBank> lastCurrencyList;
    private static long lastUpdateTime;

    public static List<CurrencyModelMonoBank> getCurrencyRate() {
        long currentTime = System.currentTimeMillis();

        if (lastCurrencyList != null && (currentTime - lastUpdateTime) < (300000)) {
            return lastCurrencyList;
        }

        HttpGet request = new HttpGet(BASE_URL);

        try {
            HttpResponse response = HTTP_CLIENT.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                List<CurrencyModelMonoBank> currencyList = GSON.fromJson(responseBody, new TypeToken<List<CurrencyModelMonoBank>>() {}.getType());

                if (currencyList != null) {
                    lastCurrencyList = currencyList;
                    lastUpdateTime = currentTime;

                    return currencyList;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastCurrencyList;
    }

    public static String getCurrencyInformation(String currency) {
        List<CurrencyModelMonoBank> currencyList = getCurrencyRate();
        StringBuilder result = new StringBuilder();

        if (currencyList != null) {
            for (CurrencyModelMonoBank currencyModelMonoBank : currencyList) {
                if(currencyModelMonoBank.getCurrencyCodeA() == convertCurrencyToNumber(currency) &&
                        currencyModelMonoBank.getCurrencyCodeB() == 980) {
                    result.append("Курс в Монобанк: ")
                            .append(currency)
                            .append("/UAH\nКупівля: ")
                            .append(currencyModelMonoBank.getRateBuy())
                            .append("\nПродаж: ")
                            .append(currencyModelMonoBank.getRateSell());
                }
            }
        }

        return result.toString();
    }

    private static int convertCurrencyToNumber(String currency) {
        int currencyNumber = 0;

        if (currency.contains("USD")) {
            currencyNumber = 840;
        } else if (currency.contains("EUR")) {
            currencyNumber = 978;
        }

        return currencyNumber;
    }
}
