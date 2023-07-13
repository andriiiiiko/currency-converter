package com.godeveloper.currencyconverter.service.utilits;

import com.godeveloper.currencyconverter.service.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.godeveloper.currencyconverter.service.utilits.ui.UserServices.getUserSettingsById;

public class SendMessageBuilder {

    private final SendMessage   sendMessage;
    private final EditMessage editMessage;

    public SendMessageBuilder(TelegramBot telegramBot) {
        this.editMessage = new EditMessage(telegramBot);
        this.sendMessage = new SendMessage();
    }
    public void setSendMessage(long chatId, String answer, long messageId, InlineKeyboardMarkup button) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime(answer);

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, button);
    }
}
