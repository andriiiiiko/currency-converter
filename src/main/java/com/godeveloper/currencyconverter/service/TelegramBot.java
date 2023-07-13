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

    private final BotConfig config;
    private final BotCommands botcommands;

    public TelegramBot(BotConfig config) {
        this.config = config;
        this.botcommands = new BotCommands(this);
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

            processMessage(messageText, username, chatId);
        }

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatIdBackQuery = update.getCallbackQuery().getMessage().getChatId();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();

            processCallbackQuery(callbackData, chatIdBackQuery, messageId);
        }
    }

    private void processMessage(String messageText, String username, long chatId) {
        switch (messageText) {
            case "/start" -> botcommands.start(chatId);
            case "/info" -> botcommands.infoMessage(chatId);
            case "/setting" -> botcommands.settingsMessage(chatId);
            case "/bank" -> botcommands.bankSettings(chatId);
            case "/currency" -> botcommands.currencySettings(chatId);
            case "/time" -> botcommands.timeSettings(chatId);
            case "/number" -> botcommands.numberSettings(chatId);
        }

        Log.Info(username, messageText);
    }

    private void processCallbackQuery(String callbackData, long chatIdBackQuery, long messageId) {
        switch (callbackData) {
            case "\uD83D\uDCB1 ОТРИМАТИ ІНФО" -> botcommands.infoMessage(chatIdBackQuery);
            case "⚙ НАЛАШТУВАННЯ", "\uD83D\uDD19 НАЗАД" -> botcommands.settingsMessage(chatIdBackQuery);
            case "\uD83D\uDD22 КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> botcommands.numberSettings(chatIdBackQuery);
            case "\uD83D\uDCB5 ВАЛЮТА" -> botcommands.currencySettings(chatIdBackQuery);
            case "\uD83C\uDFE6 БАНК" -> botcommands.bankSettings(chatIdBackQuery);
            case "\uD83D\uDD52 ЧАС СПОВІЩЕНЬ" -> botcommands.timeSettings(chatIdBackQuery);
            case "ПРИВАТ" -> botcommands.setPrivat(chatIdBackQuery, messageId);
            case "МОНО" -> botcommands.setMono(chatIdBackQuery, messageId);
            case "НБУ" -> botcommands.setNBU(chatIdBackQuery, messageId);
            case "USD" -> botcommands.setUSD(chatIdBackQuery, messageId);
            case "EUR" -> botcommands.setEUR(chatIdBackQuery, messageId);
            case "2" -> botcommands.setTwoNumbers(chatIdBackQuery, messageId);
            case "3" -> botcommands.setThreeNumbers(chatIdBackQuery, messageId);
            case "4" -> botcommands.setFourNumbers(chatIdBackQuery, messageId);
            case "09:00" -> botcommands.setTime9(chatIdBackQuery, messageId);
            case "10:00" -> botcommands.setTime10(chatIdBackQuery, messageId);
            case "11:00" -> botcommands.setTime11(chatIdBackQuery, messageId);
            case "12:00" -> botcommands.setTime12(chatIdBackQuery, messageId);
            case "13:00" -> botcommands.setTime13(chatIdBackQuery, messageId);
            case "14:00" -> botcommands.setTime14(chatIdBackQuery, messageId);
            case "15:00" -> botcommands.setTime15(chatIdBackQuery, messageId);
            case "16:00" -> botcommands.setTime16(chatIdBackQuery, messageId);
            case "17:00" -> botcommands.setTime17(chatIdBackQuery, messageId);
            case "18:00" -> botcommands.setTime18(chatIdBackQuery, messageId);
            case "ВИМКНУТИ СПОВІЩЕННЯ" -> botcommands.setTimeOff(chatIdBackQuery, messageId);
            case "\uD83C\uDFE0 НА ГОЛОВНУ" -> botcommands.home(chatIdBackQuery);
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
