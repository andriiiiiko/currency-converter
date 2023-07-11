package com.godeveloper.currencyconverter.service.utilits;

import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.commands.BotCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduledMessageSender {

    TelegramBot telegramBot;
    private Map<Long, Boolean> messageSentMap = new HashMap<>();

    public ScheduledMessageSender(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        BotCommands botCommandsHandler = new BotCommands(telegramBot);

    }

    public void scheduleMessage(long chatId, LocalDateTime scheduledTime) {
        messageSentMap.put(chatId, false);

        LocalDateTime currentTime = LocalDateTime.now();
        long delayMillis = calculateDelayMillis(currentTime, scheduledTime);

        if (delayMillis < 0) {
            delayMillis = 0;
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendMessage(chatId);
            }
        }, delayMillis);
    }

    private long calculateDelayMillis(LocalDateTime currentTime, LocalDateTime scheduledTime) {
        return java.time.Duration.between(currentTime, scheduledTime).toMillis();
    }

    private void sendMessage(long chatId) {
        if (!messageSentMap.containsKey(chatId) || !messageSentMap.get(chatId)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("Test noty");

            telegramBot.executeMessage(sendMessage);

            messageSentMap.put(chatId, true);
        }
    }
}