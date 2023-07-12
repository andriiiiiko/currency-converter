package com.godeveloper.currencyconverter.service;

import com.godeveloper.currencyconverter.service.utilits.Log;
import com.godeveloper.currencyconverter.service.utilits.commands.BotCommands;

public class ProcessHandler {

    private final BotCommands botcommands;

    public ProcessHandler(TelegramBot telegramBot) {
        this.botcommands = new BotCommands (telegramBot);
    }

    public void Message(String messageText, String username, long chatId) {
        switch (messageText) {
            case "/start" -> botcommands.start(chatId);
            case "/info" -> botcommands.infoMessage(chatId);
            case "/setting" -> botcommands.settingsMessage(chatId);
            case "/bank" -> botcommands.bankSettings(chatId);
            case "/currency" -> botcommands.currencySettings(chatId);
            case "/time" -> botcommands.timeSettings(chatId);
            case "/number" -> botcommands.numberSettings(chatId);
        }

        Log.Info(username, messageText);
    }

    public void CallbackQuery(String callbackData, String userName, long chatIdBackQuery) {
        switch (callbackData) {
            case "\uD83D\uDCB1 ОТРИМАТИ ІНФО" -> botcommands.infoMessage(chatIdBackQuery);
            case "⚙ НАЛАШТУВАННЯ", "\uD83D\uDD19 НАЗАД" -> botcommands.settingsMessage(chatIdBackQuery);
            case "\uD83D\uDD22 КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> botcommands.numberSettings(chatIdBackQuery);
            case "\uD83D\uDCB5 ВАЛЮТА" -> botcommands.currencySettings(chatIdBackQuery);
            case "\uD83C\uDFE6 БАНК" -> botcommands.bankSettings(chatIdBackQuery);
            case "\uD83D\uDD52 ЧАС СПОВІЩЕНЬ" -> botcommands.timeSettings(chatIdBackQuery);
            case "ПРИВАТ" -> botcommands.setPrivat(chatIdBackQuery);
            case "МОНО" -> botcommands.setMono(chatIdBackQuery);
            case "НБУ" -> botcommands.setNBU(chatIdBackQuery);
            case "USD" -> botcommands.setUSD(chatIdBackQuery);
            case "EUR" -> botcommands.setEUR(chatIdBackQuery);
            case "2" -> botcommands.setTwoNumbers(chatIdBackQuery);
            case "3" -> botcommands.setThreeNumbers(chatIdBackQuery);
            case "4" -> botcommands.setFourNumbers(chatIdBackQuery);
            case "09:00" -> botcommands.setTime9(chatIdBackQuery);
            case "10:00" -> botcommands.setTime10(chatIdBackQuery);
            case "11:00" -> botcommands.setTime11(chatIdBackQuery);
            case "12:00" -> botcommands.setTime12(chatIdBackQuery);
            case "13:00" -> botcommands.setTime13(chatIdBackQuery);
            case "14:00" -> botcommands.setTime14(chatIdBackQuery);
            case "15:00" -> botcommands.setTime15(chatIdBackQuery);
            case "16:00" -> botcommands.setTime16(chatIdBackQuery);
            case "17:00" -> botcommands.setTime17(chatIdBackQuery);
            case "18:00" -> botcommands.setTime18(chatIdBackQuery);
            case "ВИМКНУТИ СПОВІЩЕННЯ" -> botcommands.setTimeOff(chatIdBackQuery);
            case "\uD83C\uDFE0 НА ГОЛОВНУ" -> botcommands.home(chatIdBackQuery);
        }

        Log.button(userName, callbackData);
    }
}