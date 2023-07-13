package com.godeveloper.currencyconverter.service.utilits;

import com.godeveloper.currencyconverter.service.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class MessageBuilder {

    private final SendMessage sendMessage;
    private final TelegramBot telegramBot;

    public MessageBuilder(TelegramBot telegramBot) {
        this.sendMessage = new SendMessage();
        this.telegramBot = telegramBot;
    }
    public void sendMessage(long chatId, String answer, InlineKeyboardMarkup button) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        sendMessage.setReplyMarkup(button);

        telegramBot.executeMessage(sendMessage);
    }

}
