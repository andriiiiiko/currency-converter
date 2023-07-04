package com.team1.currencyconverter.service.utilits.commands;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

public class BotCommandListMenu {

    public static List<BotCommand> getBotCommandList() {
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "Запустити бота"));
        botCommandList.add(new BotCommand("/info", "отримати інфо"));
        botCommandList.add(new BotCommand("/setting", "Налаштуавння"));
        botCommandList.add(new BotCommand("/bank", "Налаштуавння банку"));
        botCommandList.add(new BotCommand("/currency", "Налаштуавння валюти"));
        botCommandList.add(new BotCommand("/time", "Налаштуавння сповіщення"));
        botCommandList.add(new BotCommand("/number", "Налаштуавння знаків"));
        return botCommandList;
    }
}
