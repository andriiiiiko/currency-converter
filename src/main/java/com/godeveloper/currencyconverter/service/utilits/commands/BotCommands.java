package com.godeveloper.currencyconverter.service.utilits.commands;

import com.godeveloper.currencyconverter.banks.privatbank.CurrencyService;
import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.InlineKeyboardMarkupBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class BotCommands {

    private final SendMessage sendMessage;
    private final TelegramBot telegramBot;

    public BotCommands(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.sendMessage = new SendMessage();
    }

    public void start(long chatId) {
        sendMessage(chatId, "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"Отримати інфо", "Налаштування"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void infoMessage(long chatId, String currency) {
        sendMessage(chatId, CurrencyService.getCurrencyInformation(currency));

        telegramBot.executeMessage(sendMessage);
    }

    public void settingsMessage(long chatId) {
        sendMessage(chatId, "Налаштування");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"Банк", "Валюта", "Час сповіщень", "Кількість знаків після коми"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void numberSettings(long chatId) {
        sendMessage(chatId, "Виберіть кількість знаків після коми");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"2", "3", "4"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void currencySettings(long chatId) {
        sendMessage(chatId, "Виберіть валюту");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"EUR", "USD"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void bankSettings(long chatId) {
        sendMessage(chatId, "Виберіть банк");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"НБУ", "Приват", "Моно"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void timeSettings(long chatId) {
        sendMessage(chatId, "Виберіть час сповіщення");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "Виключити сповіщення"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    private void sendMessage(long chatId, String answer) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);
    }
}