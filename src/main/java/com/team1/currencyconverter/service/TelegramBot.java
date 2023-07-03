package com.team1.currencyconverter.service;

import com.team1.currencyconverter.config.BotConfig;
import com.team1.currencyconverter.service.utilits.Log;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String username = update.getMessage().getChat().getUserName();
            long chatId = update.getMessage().getChatId();

            Log.Info(username, messageText);

            if (messageText.equals("/start")) {
                startCommandStart(chatId);
            }
        }

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatIdBackQuery = update.getCallbackQuery().getMessage().getChatId();

            switch (callbackData) {
                case "ОТРИМАТИ ІНФО" -> infoMessage(chatIdBackQuery);
                case "НАЛАШТУВАННЯ" -> settingsMessage(chatIdBackQuery);
                case "КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> numberSettings(chatIdBackQuery);
                case "ВАЛЮТИ" -> currencySettings(chatIdBackQuery);
                case "БАНК" -> bankSettings(chatIdBackQuery);
                case "ЧАС СПОВІЩЕНЬ" -> timeSettings(chatIdBackQuery);
            }

            Log.button(callbackData);
        }
    }

    private void startCommandStart(long chatId) {
        String answer = EmojiParser.parseToUnicode("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют" + " :currency_exchange: !");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        String[] nameButton = {"Отримати інфо", "Налаштування"};
        for (String nameButtons : nameButton) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(nameButtons);
            button.setCallbackData(nameButtons.toUpperCase());
            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        executeMessage(message);
    }

    private void infoMessage(long chatId) {
        String answer = EmojiParser.parseToUnicode("""
                Курс в Приват банк: USD/UAH
                Купівлля: 38.55
                Продаж: 39.60""");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        String[] nameButton = {"Отримати інфо", "Налаштування"};
        for (String nameButtons : nameButton) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(nameButtons);
            button.setCallbackData(nameButtons.toUpperCase());
            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        executeMessage(message);
    }

    private void settingsMessage(long chatId) {
        String answer = EmojiParser.parseToUnicode("Налаштування");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        String[] nameButton = {"Банк", "Валюти", "Час сповіщень", "Кількість знаків після коми"};
        for (String nameButtons : nameButton) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(nameButtons);
            button.setCallbackData(nameButtons.toUpperCase());
            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        executeMessage(message);
    }

    private void numberSettings(long chatId) {
        String answer = EmojiParser.parseToUnicode("Виберіть кулькість знаків після коми");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        String[] nameButton = {"2", "3", "4"};
        for (String nameButtons : nameButton) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(nameButtons);
            button.setCallbackData(nameButtons);
            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        executeMessage(message);
    }

    private void currencySettings(long chatId) {
        String answer = EmojiParser.parseToUnicode("Виберіть валюту");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        String[] nameButton = {"EUR", "USD"};
        for (String nameButtons : nameButton) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(nameButtons);
            button.setCallbackData(nameButtons);
            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        executeMessage(message);
    }

    private void bankSettings(long chatId) {
        String answer = EmojiParser.parseToUnicode("Виберіть банк");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        String[] nameButton = {"НБУ", "Приват", "Райфайзен"};
        for (String nameButtons : nameButton) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(nameButtons);
            button.setCallbackData(nameButtons.toUpperCase());
            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        executeMessage(message);
    }

    private void timeSettings(long chatId) {
        String answer = EmojiParser.parseToUnicode("Виберіть час сповіщення");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        String[] timeOptions = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
        for (String timeOption : timeOptions) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(timeOption);
            button.setCallbackData("T_" + timeOption.replace(":", ""));
            keyboard.add(List.of(button));
        }

        InlineKeyboardButton offButton = new InlineKeyboardButton();
        offButton.setText("Виключити сповіщення");
        offButton.setCallbackData("OFF");
        keyboard.add(List.of(offButton));

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
}