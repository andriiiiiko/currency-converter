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

    private Map<Long, Boolean> messageSentMap = new HashMap<>();
    BotCommands botCommandsHandler;

    public ScheduledMessageSender(TelegramBot telegramBot) {
        botCommandsHandler = new BotCommands(telegramBot);
    }

    public void scheduleMessage(long chatId,  LocalDateTime scheduledTime) {

    }

    private long calculateDelayMillis(LocalDateTime currentTime, LocalDateTime scheduledTime) {
        return java.time.Duration.between(currentTime, scheduledTime).toMillis();
    }

    private void sendMessage(long chatId,  LocalDateTime scheduledTime) {
        if (messageSentMap.containsKey(chatId) && messageSentMap.get(chatId)) {

            LocalDateTime currentTime = LocalDateTime.now();
            long delayMillis = calculateDelayMillis(currentTime, scheduledTime);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("test");

                    System.out.println("plan");
                }
            }, delayMillis);
        }
    }
}