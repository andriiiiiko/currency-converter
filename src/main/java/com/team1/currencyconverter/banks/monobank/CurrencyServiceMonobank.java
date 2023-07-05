package com.team1.currencyconverter.banks.monobank;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CurrencyServiceMonobank {

    private static final String BASE_URL = "https://api.monobank.ua/bank/currency";
    private static final Gson gson = new Gson();
    private static final HttpClient httpClient = HttpClients.createDefault();
    private static List<CurrencyModelMonobank> cachedCurrencyRates;

    public static List<CurrencyModelMonobank> getCurrencyRate() {
        HttpGet request = new HttpGet(BASE_URL);

        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                CurrencyModelMonobank[] tasks = gson.fromJson(responseBody, CurrencyModelMonobank[].class);
                cachedCurrencyRates = Arrays.asList(tasks);

                return cachedCurrencyRates;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getCurrencyInformation(String currency) {
        getCurrencyRate();

        if (cachedCurrencyRates != null) {
            for (CurrencyModelMonobank currencyModel : cachedCurrencyRates) {
                if (currencyModel.getCurrencyCodeA() == convertCurrencyToNumber(currency)) {
                    return "Курси в Monobank: " + currency + "/UAN" + "\n" +
                            "Купівля: " + currencyModel.getRateBuy() + "\n" +
                            "Продаж: " + currencyModel.getRateSell();
                }
            }
        }

        return null;
    }

    public static int convertCurrencyToNumber(String currency) {
        int currencyNumber = 0;

        if (currency.contains("USD")) {
            currencyNumber = 840;
        } else if (currency.contains("EUR")) {
            currencyNumber = 978;
        }

        return currencyNumber;
    }
}
