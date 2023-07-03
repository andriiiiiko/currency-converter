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

    private void settingsMessage(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Налаштування");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        InlineKeyboardButton bankButton = new InlineKeyboardButton();
        bankButton.setText("Банк");
        bankButton.setCallbackData("BANK");

        InlineKeyboardButton currencyButton = new InlineKeyboardButton();
        currencyButton.setText("Валюти");
        currencyButton.setCallbackData("CURRENCIES");

        InlineKeyboardButton timeButton = new InlineKeyboardButton();
        timeButton.setText("Час сповіщень");
        timeButton.setCallbackData("TIME");

        InlineKeyboardButton numberButton = new InlineKeyboardButton();
        numberButton.setText("Кількість знаків після коми");
        numberButton.setCallbackData("NUMBER");

        keyboard.add(List.of(bankButton));
        keyboard.add(List.of(currencyButton));
        keyboard.add(List.of(timeButton));
        keyboard.add(List.of(numberButton));


        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        Log.Info(name);

        executeMessage(message);
    }

    private void numberSettings(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Виберіть кулькість знаків після коми");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        InlineKeyboardButton Button2 = new InlineKeyboardButton();
        Button2.setText("2");
        Button2.setCallbackData("N_2");

        InlineKeyboardButton Button3 = new InlineKeyboardButton();
        Button3.setText("3");
        Button3.setCallbackData("N_3");

        InlineKeyboardButton Button4 = new InlineKeyboardButton();
        Button4.setText("4");
        Button4.setCallbackData("N_4");

        keyboard.add(List.of(Button2));
        keyboard.add(List.of(Button3));
        keyboard.add(List.of(Button4));

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        Log.Info(name);

        executeMessage(message);
    }

    private void currencySettings(long chatId, String name) {
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

        Log.Info(name);

        executeMessage(message);
    }

    private void bankSettings(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Виберіть банк");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        InlineKeyboardButton nbuButton = new InlineKeyboardButton();
        nbuButton.setText("НБУ");
        nbuButton.setCallbackData("NBU");

        InlineKeyboardButton pbButton = new InlineKeyboardButton();
        pbButton.setText("Приват банк");
        pbButton.setCallbackData("PB");

        InlineKeyboardButton rfButton = new InlineKeyboardButton();
        rfButton.setText("Райфайзен банк");
        rfButton.setCallbackData("RF");

        keyboard.add(List.of(nbuButton));
        keyboard.add(List.of(pbButton));
        keyboard.add(List.of(rfButton));

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        Log.Info(name);

        executeMessage(message);
    }

    private void timeSettings(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Виберіть час сповіщення");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);


        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        InlineKeyboardButton uButton9 = new InlineKeyboardButton();
        uButton9.setText("09:00");
        uButton9.setCallbackData("T_09");

        InlineKeyboardButton Button10 = new InlineKeyboardButton();
        Button10.setText("10:00");
        Button10.setCallbackData("T_10");

        InlineKeyboardButton Button11 = new InlineKeyboardButton();
        Button11.setText("11:00");
        Button11.setCallbackData("T_11");

        InlineKeyboardButton Button12 = new InlineKeyboardButton();
        Button12.setText("12:00");
        Button12.setCallbackData("T_12");

        InlineKeyboardButton Button13 = new InlineKeyboardButton();
        Button13.setText("13:00");
        Button13.setCallbackData("T_13");

        InlineKeyboardButton Button14 = new InlineKeyboardButton();
        Button14.setText("14:00");
        Button14.setCallbackData("T_14");

        InlineKeyboardButton Button15 = new InlineKeyboardButton();
        Button15.setText("15:00");
        Button15.setCallbackData("T_15");

        InlineKeyboardButton Button16 = new InlineKeyboardButton();
        Button16.setText("16:00");
        Button16.setCallbackData("T_16");

        InlineKeyboardButton Button17 = new InlineKeyboardButton();
        Button17.setText("17:00");
        Button17.setCallbackData("T_17");

        InlineKeyboardButton Button18 = new InlineKeyboardButton();
        Button18.setText("18:00");
        Button18.setCallbackData("T_18");

        InlineKeyboardButton ofButton = new InlineKeyboardButton();
        ofButton.setText("Виключити сповіщення");
        ofButton.setCallbackData("OFF");

        keyboard.add(List.of(uButton9));
        keyboard.add(List.of(Button10));
        keyboard.add(List.of(Button11));
        keyboard.add(List.of(Button12));
        keyboard.add(List.of(Button14));
        keyboard.add(List.of(Button15));
        keyboard.add(List.of(Button16));
        keyboard.add(List.of(Button17));
        keyboard.add(List.of(Button18));
        keyboard.add(List.of(ofButton));

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);

        Log.Info(name);

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