package com.godeveloper.currencyconverter.service.utilits.commands;
import com.godeveloper.currencyconverter.banks.privatbank.CurrencyServicePrivatBank;
import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.InlineKeyboardMarkupBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BotCommands {

    private final SendMessage sendMessage;
    private final TelegramBot telegramBot;
    private static String format = "#.##";

    public BotCommands(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.sendMessage = new SendMessage();
    }

    public void start(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"Отримати інфо", "Налаштування"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void infoMessage(long chatId, String currency) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(CurrencyServicePrivatBank.getCurrencyInformation(currency));

        telegramBot.executeMessage(sendMessage);
    }

    public void settingsMessage(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Налаштування");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"Банк", "Валюта", "Час сповіщень", "Кількість знаків після коми"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void numberSettings(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Виберіть кількість знаків після коми");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"2", "3", "4"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void currencySettings(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Виберіть валюту");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"EUR", "USD"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void bankSettings(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Виберіть банк");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"НБУ", "Приват", "Моно"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void timeSettings(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Виберіть час сповіщення");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "Виключити сповіщення"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void setFormat(String format) {
        BotCommands.format = format;
    }

    public static String getFormat() {
        return format;
    }

    public static String setNumberFormat(float number, String format){
        DecimalFormat decimalFormat = new DecimalFormat(format);
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(number);
    }
}