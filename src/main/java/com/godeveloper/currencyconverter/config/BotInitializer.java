package com.godeveloper.currencyconverter.config;

import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.Log;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {

    private final TelegramBot TELEGRAM_BOT;

    public BotInitializer(TelegramBot TELEGRAM_BOT) {
        this.TELEGRAM_BOT = TELEGRAM_BOT;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(TELEGRAM_BOT);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
}
