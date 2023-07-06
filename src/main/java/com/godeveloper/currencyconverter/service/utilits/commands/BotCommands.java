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
        messageBuilder(chatId,
                "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!",
                new String[]{"Отримати інфо", "Налаштування"});
    }

    public void infoMessage(long chatId, String currency) {
        messageBuilder(chatId,
                CurrencyService.getCurrencyInformation(currency));
    }

    public void settingsMessage(long chatId) {
        messageBuilder(chatId,
                "Налаштування",
                new String[]{"Банк", "Валюта", "Час сповіщень", "Кількість знаків після коми"});
    }

    public void numberSettings(long chatId) {
        messageBuilder(chatId,
                "Виберіть кількість знаків після коми",
                new String[]{"2", "3", "4"});
    }

    public void currencySettings(long chatId) {
        messageBuilder(chatId,
                "Виберіть валюту",
                new String[]{"EUR", "USD"});
    }

    public void bankSettings(long chatId) {
        messageBuilder(chatId,
                "Виберіть банк",
                new String[]{"НБУ", "Моно", "Приват"});
    }

    public void timeSettings(long chatId) {
        messageBuilder(chatId,
                "Виберіть час сповіщення",
                new String[]{"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "Виключити сповіщення"});
    }

    private void sendMessage(long chatId, String answer) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);
    }

    private void setReplyMarkup(String[] nameButton) {
        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(nameButton);
        sendMessage.setReplyMarkup(markup);
    }

    private void executeMessage() {
        telegramBot.executeMessage(sendMessage);
    }

    private void messageBuilder(long chatId, String answer) {
        sendMessage(chatId, answer);
        executeMessage();
    }
    private void messageBuilder(long chatId, String answer, String[] nameButton) {
        sendMessage(chatId, answer);
        setReplyMarkup(nameButton);
        executeMessage();
    }
}