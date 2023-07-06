package com.godeveloper.currencyconverter.service.utilits;

import com.godeveloper.currencyconverter.service.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class MessageBuilder {

    private long chatId;
    private String messageText;
    private String[] nameButton;

    private final SendMessage sendMessage;
    private final TelegramBot telegramBot;

    public MessageBuilder(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.sendMessage = new SendMessage();
    }

    private void setChatId(long chatId) {
        this.chatId = chatId;
    }

    private void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    private void setNameButton(String[] nameButton) {
        this.nameButton = nameButton;
    }

    
    
    private void sendMessage(long chatId, String answer) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);
    }

    private void setReplyMarkup(String[] nameButton) {
        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(nameButton);
        sendMessage.setReplyMarkup(markup);
    }

    private void executeMessage() {
        telegramBot.executeMessage(sendMessage);
    }

    public void createMessage(long chatId, String answer) {
        sendMessage(chatId, answer);
        executeMessage();
    }
    public void createMessage(long chatId, String answer, String[] nameButton) {
        sendMessage(chatId, answer);
        setReplyMarkup(nameButton);
        executeMessage();
    }
}
