package com.godeveloper.currencyconverter.service.utilits;


import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.commands.BotCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduledMessageSender {

    BotCommands botCommandsHandler;

    public ScheduledMessageSender(TelegramBot telegramBot) {
        botCommandsHandler = new BotCommands(telegramBot);
    }

    public void scheduleMessage(long chatId,  LocalDateTime scheduledTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        long delayMillis = calculateDelayMillis(currentTime, scheduledTime);

        if (delayMillis < 0) {
            delayMillis = 0;
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Виберіть банк");

                InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                        new String[]{"НБУ", "Приват", "Моно"});
                sendMessage.setReplyMarkup(markup);



                System.out.println("plan");
            }
        }, delayMillis);
    }

    private long calculateDelayMillis(LocalDateTime currentTime, LocalDateTime scheduledTime) {
        return java.time.Duration.between(currentTime, scheduledTime).toMillis();
    }

    private void sendMessage() {

    }
}