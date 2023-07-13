package com.godeveloper.currencyconverter.service.utilits.commands;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import java.util.ArrayList;
import java.util.List;

public class BotCommandListMenu {

    public static List<BotCommand> getBotCommandList() {
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "Запустити бота"));
        botCommandList.add(new BotCommand("/info", "Отримати інфо"));
        botCommandList.add(new BotCommand("/setting", "Налаштування"));
        botCommandList.add(new BotCommand("/bank", "Налаштування банку"));
        botCommandList.add(new BotCommand("/currency", "Налаштування валюти"));
        botCommandList.add(new BotCommand("/time", "Налаштування сповіщення"));
        botCommandList.add(new BotCommand("/number", "Налаштування знаків"));

        return botCommandList;
    }
}
