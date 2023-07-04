package com.godeveloper.currencyconverter.service;

import com.godeveloper.currencyconverter.config.BotConfig;
import com.godeveloper.currencyconverter.service.utilits.Keyboaed.InlineKeyboardMarkupBuilder;
import com.godeveloper.currencyconverter.service.utilits.Log;
import com.godeveloper.currencyconverter.service.utilits.commands.BotCommandListMenu;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final SendMessage sendMessage;
    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
        this.sendMessage = new SendMessage();

        List<BotCommand> botCommandList = BotCommandListMenu.getBotCommandList();

        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
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

            switch (messageText) {
                case "/start" -> startCommand(chatId);
                case "/info" -> infoMessage(chatId);
                case "/setting" -> settingsMessage(chatId);
                case "/bank" -> bankSettings(chatId);
                case "/currency" -> currencySettings(chatId);
                case "/time" -> timeSettings(chatId);
                case "/number" -> numberSettings(chatId);
            }
            Log.Info(username, messageText);
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

    private void startCommand(long chatId) {
        String answer = EmojiParser.parseToUnicode("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют" + " :currency_exchange: !");
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"Отримати інфо", "Налаштування"});
        sendMessage.setReplyMarkup(markup);

        executeMessage(sendMessage);
    }

    private void infoMessage(long chatId) {
        String answer = EmojiParser.parseToUnicode("""
                Курс в Приват банк: USD/UAH
                Купівлля: 38.55
                Продаж: 39.60""");

        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"Налаштування"});
        sendMessage.setReplyMarkup(markup);

        executeMessage(sendMessage);
    }

    private void settingsMessage(long chatId) {
        String answer = EmojiParser.parseToUnicode("Налаштування");

        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"Банк", "Валюти", "Час сповіщень", "Кількість знаків після коми"});
        sendMessage.setReplyMarkup(markup);

        executeMessage(sendMessage);
    }

    private void numberSettings(long chatId) {
        String answer = EmojiParser.parseToUnicode("Виберіть кількість знаків після коми");

        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"2", "3", "4"});
        sendMessage.setReplyMarkup(markup);

        executeMessage(sendMessage);
    }

    private void currencySettings(long chatId) {
        String answer = EmojiParser.parseToUnicode("Виберіть валюту");

        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"EUR", "USD"});
        sendMessage.setReplyMarkup(markup);

        executeMessage(sendMessage);
    }

    private void bankSettings(long chatId) {
        String answer = EmojiParser.parseToUnicode("Виберіть банк");

        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"НБУ", "Приват", "Райфайзен"});
        sendMessage.setReplyMarkup(markup);

        executeMessage(sendMessage);
    }

    private void timeSettings(long chatId) {
        String answer = EmojiParser.parseToUnicode("Виберіть час сповіщення");

        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);


        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "Виключити сповіщення"});
        sendMessage.setReplyMarkup(markup);

        executeMessage(sendMessage);
    }

    public void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
}