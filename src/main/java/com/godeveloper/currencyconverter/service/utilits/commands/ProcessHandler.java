package com.godeveloper.currencyconverter.service.utilits.commands;


import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.Log;

public class ProcessHandler {
    private final BotCommands botCommands;

    public ProcessHandler (TelegramBot telegramBot) {
        this.botCommands = new BotCommands(telegramBot);
    }

    public void message(String messageText, String username, long chatId) {
        switch (messageText) {
            case "/start" -> botCommands.start(chatId);
            case "/info" -> {
                botCommands.infoMessage(chatId, "USD");
                botCommands.infoMessage(chatId, "EUR");
            }
            case "/setting" -> botCommands.settingsMessage(chatId);
            case "/bank" -> botCommands.bankSettings(chatId);
            case "/currency" -> botCommands.currencySettings(chatId);
            case "/time" -> botCommands.timeSettings(chatId);
            case "/number" -> botCommands.numberSettings(chatId);
        }

        Log.Info(username, messageText);
    }

    public void callbackQuery(String callbackData, String username, long chatIdBackQuery) {
        switch (callbackData) {
            case "ОТРИМАТИ ІНФО" -> {
                botCommands.infoMessage(chatIdBackQuery, "USD");
                botCommands.infoMessage(chatIdBackQuery, "EUR");
            }
            case "НАЛАШТУВАННЯ" -> botCommands.settingsMessage(chatIdBackQuery);
            case "КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> botCommands.numberSettings(chatIdBackQuery);
            case "ВАЛЮТА" -> botCommands.currencySettings(chatIdBackQuery);
            case "БАНК" -> botCommands.bankSettings(chatIdBackQuery);
            case "ЧАС СПОВІЩЕНЬ" -> botCommands.timeSettings(chatIdBackQuery);
        }

        Log.button(username, callbackData);
    }
}
