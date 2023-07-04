package com.team1.currencyconverter.service.utilits.commands;


import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandsHandler extends TelegramLongPollingCommandBot {

    public CommandsHandler() {

        super("ua_currency_converter_bot");

        register(new StartCommand());
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        System.out.println("non");
    }
}
