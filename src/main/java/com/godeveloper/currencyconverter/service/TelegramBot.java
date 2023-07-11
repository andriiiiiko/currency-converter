package com.godeveloper.currencyconverter.service;

import com.godeveloper.currencyconverter.config.BotConfig;
import com.godeveloper.currencyconverter.service.utilits.Log;
import com.godeveloper.currencyconverter.service.utilits.ScheduledMessageSender;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig CONFIG;
    private BotCommands botCommands;
    private ScheduledMessageSender scheduledMessageSender;

    public TelegramBot(BotConfig CONFIG) {
        this.CONFIG = CONFIG;
        this.scheduledMessageSender = new ScheduledMessageSender(this);
        this.botCommands = new BotCommands(this);
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
            case "/start" -> botCommands.start(chatId);
            case "/info" -> {
                botCommands.infoMessage(chatId, "USD");
                botCommands.infoMessage(chatId, "EUR");
            }
            case "/setting" -> botCommands.settingsMessage(chatId);
            case "/bank" -> botCommands.bankSettings(chatId);
            case "/currency" -> botCommands.currencySettings(chatId);
            case "/time" -> botCommands.timeSettings(chatId);
            case "/number" -> botCommands.numberSettings(chatId);
        }

        Log.Info(username, messageText);
    }

    private void processCallbackQuery(String callbackData, long chatIdBackQuery) {
        switch (callbackData) {
            case "ОТРИМАТИ ІНФО" -> {
                botCommands.infoMessage(chatIdBackQuery, "USD");
                botCommands.infoMessage(chatIdBackQuery, "EUR");
            }
            case "НАЛАШТУВАННЯ" -> botCommands.settingsMessage(chatIdBackQuery);
            case "КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> botCommands.numberSettings(chatIdBackQuery);
            case "ВАЛЮТА" -> botCommands.currencySettings(chatIdBackQuery);
            case "БАНК" -> botCommands.bankSettings(chatIdBackQuery);
            case "ЧАС СПОВІЩЕНЬ" -> botCommands.timeSettings(chatIdBackQuery);
            case "ВИКЛЮЧИТИ СПОВІЩЕННЯ" -> notificationHandler(chatIdBackQuery, 16, 48);
            case "09:00" -> notificationHandler(chatIdBackQuery, 9, 0);
            case "10:00" -> notificationHandler(chatIdBackQuery, 10, 0);
            case "11:00" -> notificationHandler(chatIdBackQuery, 11, 0);
            case "12:00" -> notificationHandler(chatIdBackQuery, 12, 0);
        }

        Log.button(callbackData);
    }

    private void notificationHandler(long chatIdBackQuery, int hh, int mm) {
        LocalDateTime scheduledTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(hh, mm));
        scheduledMessageSender.scheduleMessage(chatIdBackQuery, scheduledTime);

        System.out.println("/schedule");
    }

    public void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
}