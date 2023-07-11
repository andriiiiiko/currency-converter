package com.godeveloper.currencyconverter.service.utilits.commands;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BotCommandListMenu {

    public static List<BotCommand> getBotCommandList() {
        return Arrays.asList(
                new BotCommand("/start", "Запустити бота"),
                new BotCommand("/info", "отримати інфо"),
                new BotCommand("/setting", "Налаштуавння"),
                new BotCommand("/bank", "Налаштуавння банку"),
                new BotCommand("/currency", "Налаштуавння валюти"),
                new BotCommand("/time", "Налаштуавння сповіщення"),
                new BotCommand("/number", "Налаштуавння знаків")
        );
    }
}
