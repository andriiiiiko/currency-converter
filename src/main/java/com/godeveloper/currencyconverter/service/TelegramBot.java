package com.godeveloper.currencyconverter.service;

import com.godeveloper.currencyconverter.config.BotConfig;
import com.godeveloper.currencyconverter.service.utilits.Log;
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
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                startCommandStart(chatId, update.getMessage().getChat().getUserName());
            }
        }

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            switch (callbackData) {
                case "INFO_BUTTON" -> infoMessage(chatId, update.getCallbackQuery().getData());
                case "SETTINGS BUTTON" -> settingsMessage(chatId, update.getCallbackQuery().getData());
                case "NUMBER" -> numberSettings(chatId, update.getCallbackQuery().getData());
                case "CURRENCIES" -> currencySettings(chatId, update.getCallbackQuery().getData());
                case "BANK" -> bankSettings(chatId, update.getCallbackQuery().getData());
                case "TIME" -> timeSettings(chatId, update.getCallbackQuery().getData());
            }
        }

    }

    private void startCommandStart(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют" + " :currency_exchange: !");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(name);

        List<String> buttons = Arrays.asList(
                "Отримати інфо",
                "Налаштування"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "INFO_BUTTON",
                buttons.get(1), "SETTINGS BUTTON"
        ));

        executeMessage(message);
    }

    private void infoMessage(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("""
                Курс в Приват банк: USD/UAH
                Купівлля: 38.55
                Продаж: 39.60""");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(str);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        InlineKeyboardButton infoButton = new InlineKeyboardButton();
        infoButton.setText("Отримати інфо");
        infoButton.setCallbackData("INFO_BUTTON");

        InlineKeyboardButton settingButton = new InlineKeyboardButton();
        settingButton.setText("Налаштування");
        settingButton.setCallbackData("SETTINGS BUTTON");

        keyboard.add(List.of(infoButton));
        keyboard.add(List.of(settingButton));


        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        executeMessage(message);
    }

    private void settingsMessage(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Налаштування");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(str);

        List<String> buttons = Arrays.asList(
                "Кількість знаків після коми",
                "Банк",
                "Валюти",
                "Час сповіщень"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "NUMBER",
                buttons.get(1), "BANK",
                buttons.get(2), "CURRENCIES",
                buttons.get(3), "TIME"
        ));


        executeMessage(message);
    }

    private void numberSettings(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Виберіть кулькість знаків після коми");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(str);

        List<String> buttons = Arrays.asList(
                "2",
                "3",
                "4"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "N_2",
                buttons.get(1), "N_3",
                buttons.get(2), "N_4"
        ));


        executeMessage(message);
    }

    private void currencySettings(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Виберіть валюту");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var eurButton = new InlineKeyboardButton();

        eurButton.setText("EUR");
        eurButton.setCallbackData("B_EUR");

        var usdButton = new InlineKeyboardButton();

        usdButton.setText("USD");
        usdButton.setCallbackData("B_USD");

        rowInLine.add(eurButton);
        rowInLine.add(usdButton);

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        Log.Info(str);

        executeMessage(message);
    }

    private void bankSettings(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Виберіть банк");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(str);

        List<String> buttons = Arrays.asList(
                "НБУ",
                "Приват банк",
                "Райфайзен банк"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "NBU",
                buttons.get(1), "PB",
                buttons.get(2), "RF"
        ));

        executeMessage(message);
    }

    private void timeSettings(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Виберіть час сповіщення");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(str);

        List<String> buttons = Arrays.asList(
                "Виключити сповіщення",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "T_09",
                buttons.get(1), "T_10",
                buttons.get(2), "T_11",
                buttons.get(3), "T_12",
                buttons.get(4), "T_13",
                buttons.get(5), "T_14",
                buttons.get(6), "T_15",
                buttons.get(7), "T_16",
                buttons.get(8), "T_17",
                buttons.get(9), "T_18"
        ));

        executeMessage(message);
    }
    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }

    public void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes()));
            button.setCallbackData(buttonValue);

            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }
}