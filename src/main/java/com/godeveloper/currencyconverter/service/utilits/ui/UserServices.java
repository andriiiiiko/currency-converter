package com.godeveloper.currencyconverter.service.utilits.ui;

import com.godeveloper.currencyconverter.banks.monobank.CurrencyServiceMonoBank;
import com.godeveloper.currencyconverter.banks.nbu.CurrencyServiceNBU;
import com.godeveloper.currencyconverter.banks.privatbank.CurrencyServicePrivatBank;

import java.util.HashMap;
import java.util.Map;

public class UserServices {

    private static final Map<Long, UserModel> USERS_SETTINGS = new HashMap<>();

    public static UserModel getUserSettingsById(long chatId) {
        return USERS_SETTINGS.get(chatId);
    }

    public static boolean isUserSettingsExists(long chatId) {
        return USERS_SETTINGS.containsKey(chatId);
    }

    public static void createUserSettings(long chatId) {
        UserModel userModel = new UserModel();
        USERS_SETTINGS.put(chatId, userModel);
    }

    public static String checkSelectedBank(long chatId) {
        if (!isUserSettingsExists(chatId)) {
            createUserSettings(chatId);
        }

         return USERS_SETTINGS.get(chatId).getBank();
    }

    public static String getCurrencyInformationFromSelectedBank(long chatId) {
        String result;

        if (checkSelectedBank(chatId).equals("Приват")) {
            result = CurrencyServicePrivatBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency());
        } else if (checkSelectedBank(chatId).equals("НБУ")) {
            result = CurrencyServiceNBU.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency());
        } else {
            result = CurrencyServiceMonoBank.getCurrencyInformation(USERS_SETTINGS.get(chatId).getCurrency());
        }

        return result;
    }
}
