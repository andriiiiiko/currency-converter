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

    private final ProcessHandler processHandler;

    public TelegramBot(BotConfig config) {
        this.config = config;
        this.botcommands = new BotCommands(this);

        processHandler = new ProcessHandler(this);

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
        long chatId;
        String userName;
        String receivedMessage;

        if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();
            receivedMessage = update.getMessage().getText();

            processHandler.Message(receivedMessage, userName, chatId);
        }

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();

            processHandler.CallbackQuery(receivedMessage, userName, chatId);
        }
    }

    public void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
}