package com.godeveloper.currencyconverter.service.utilits.commands;

import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.InlineKeyboardMarkupBuilder;
import com.godeveloper.currencyconverter.service.utilits.settings.Settings;
import com.godeveloper.currencyconverter.service.utilits.settings.UserSettings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class BotCommands {

    private final SendMessage sendMessage;
    private final TelegramBot telegramBot;
    private UserSettings userSettings = new UserSettings();

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

    public void infoMessage(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(Settings.getCurrencyInformationFromSelectedBank(chatId));

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

    public void setUSD(long chatId) {
        sendMessage.setChatId(chatId);

        Settings.getUserSettingsById(chatId).setCurrency("USD");
        sendMessage.setText("Готово!");

        telegramBot.executeMessage(sendMessage);
    }

    public void setEUR(long chatId) {
        sendMessage.setChatId(chatId);

        Settings.getUserSettingsById(chatId).setCurrency("EUR");
        sendMessage.setText("Готово!");

        telegramBot.executeMessage(sendMessage);
    }

    public void setMono(long chatId) {
        sendMessage.setChatId(chatId);

        Settings.getUserSettingsById(chatId).setBank("Моно");

        sendMessage.setText("Готово!");
        telegramBot.executeMessage(sendMessage);
    }

    public void setPrivat(long chatId) {
        sendMessage.setChatId(chatId);

        Settings.getUserSettingsById(chatId).setBank("Приват");

        sendMessage.setText("Готово!");
        telegramBot.executeMessage(sendMessage);
    }

    public void setNBU(long chatId) {
        sendMessage.setChatId(chatId);

        Settings.getUserSettingsById(chatId).setBank("НБУ");

        sendMessage.setText("Готово!");
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
}
