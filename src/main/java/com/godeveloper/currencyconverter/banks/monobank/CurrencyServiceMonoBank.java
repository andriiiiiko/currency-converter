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

import static com.godeveloper.currencyconverter.service.utilits.commands.BotCommands.getFormat;
import static com.godeveloper.currencyconverter.service.utilits.commands.BotCommands.setNumberFormat;

public class CurrencyServiceMonoBank {

    private static final String BASE_URL = "https://api.monobank.ua/bank/currency";
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();
    private static List<CurrencyModelMonoBank> cachedCurrencyRates;

    public static void getCurrencyRate() {
        HttpGet request = new HttpGet(BASE_URL);

        try {
            HttpResponse response = HTTP_CLIENT.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                cachedCurrencyRates = GSON.fromJson(responseBody, new TypeToken<List<CurrencyModelMonoBank>>() {}.getType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrencyInformation(String currency) {
        getCurrencyRate();

        if (cachedCurrencyRates != null) {
            for (CurrencyModelMonoBank currencyModel : cachedCurrencyRates) {
                if (currencyModel.getCurrencyCodeA() == convertCurrencyToNumber(currency)) {
                    return "Курси в Monobank: " + currency + "/UAN" + "\n" +
                            "Купівля: " + setNumberFormat(currencyModel.getRateBuy(), getFormat()) + "\n" +
                            "Продаж: " + setNumberFormat(currencyModel.getRateSell(), getFormat());
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