package com.godeveloper.currencyconverter.service;

import com.godeveloper.currencyconverter.config.BotConfig;
import com.godeveloper.currencyconverter.service.utilits.Log;
import com.godeveloper.currencyconverter.service.utilits.commands.BotCommandListMenu;
import com.godeveloper.currencyconverter.service.utilits.commands.BotCommands;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig CONFIG;
    private final BotCommands BOTCOMMANDS;

    public TelegramBot(BotConfig CONFIG) {
        this.CONFIG = CONFIG;
        this.BOTCOMMANDS = new BotCommands(this);
        List<BotCommand> botCommandList = BotCommandListMenu.getBotCommandList();

        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }

    @Override
    public String getBotToken() {
        return CONFIG.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return CONFIG.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String username = update.getMessage().getChat().getUserName();
            long chatId = update.getMessage().getChatId();

            processMessage(messageText, username, chatId);
        }

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatIdBackQuery = update.getCallbackQuery().getMessage().getChatId();

            processCallbackQuery(callbackData, chatIdBackQuery);
        }
    }

    private void processMessage(String messageText, String username, long chatId) {
        switch (messageText) {
            case "/start" -> BOTCOMMANDS.start(chatId);
            case "/info" -> BOTCOMMANDS.infoMessage(chatId);
            case "/setting" -> BOTCOMMANDS.settingsMessage(chatId);
            case "/bank" -> BOTCOMMANDS.bankSettings(chatId);
            case "/currency" -> BOTCOMMANDS.currencySettings(chatId);
            case "/time" -> BOTCOMMANDS.timeSettings(chatId);
            case "/number" -> BOTCOMMANDS.numberSettings(chatId);
        }

        Log.Info(username, messageText);
    }

    private void processCallbackQuery(String callbackData, long chatIdBackQuery) {
        switch (callbackData) {
            case "ОТРИМАТИ ІНФО" -> BOTCOMMANDS.infoMessage(chatIdBackQuery);
            case "НАЛАШТУВАННЯ" -> BOTCOMMANDS.settingsMessage(chatIdBackQuery);
            case "КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> BOTCOMMANDS.numberSettings(chatIdBackQuery);
            case "ВАЛЮТА" -> BOTCOMMANDS.currencySettings(chatIdBackQuery);
            case "БАНК" -> BOTCOMMANDS.bankSettings(chatIdBackQuery);
            case "ЧАС СПОВІЩЕНЬ" -> BOTCOMMANDS.timeSettings(chatIdBackQuery);
            case "ПРИВАТ" -> BOTCOMMANDS.setPrivat(chatIdBackQuery);
            case "МОНО" -> BOTCOMMANDS.setMono(chatIdBackQuery);
            case "НБУ" -> BOTCOMMANDS.setNBU(chatIdBackQuery);
            case "USD" -> BOTCOMMANDS.setUSD(chatIdBackQuery);
            case "EUR" -> BOTCOMMANDS.setEUR(chatIdBackQuery);
            case "2" -> BOTCOMMANDS.setTwoNumbers(chatIdBackQuery);
            case "3" -> BOTCOMMANDS.setThreeNumbers(chatIdBackQuery);
            case "4" -> BOTCOMMANDS.setFourNumbers(chatIdBackQuery);
        }

        Log.button(callbackData);
    }

    public void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
}
