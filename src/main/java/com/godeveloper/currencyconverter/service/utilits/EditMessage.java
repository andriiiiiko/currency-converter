package com.godeveloper.currencyconverter.service.utilits;

import com.godeveloper.currencyconverter.service.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EditMessage {

    private final TelegramBot telegramBot;

    public EditMessage(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void executeEditMessageText(String text, long chatId, long messageId, InlineKeyboardMarkup button){
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setMessageId((int) messageId);

        message.setReplyMarkup(button);

        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }

}
