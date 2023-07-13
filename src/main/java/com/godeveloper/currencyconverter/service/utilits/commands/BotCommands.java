package com.godeveloper.currencyconverter.service.utilits.commands;

import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.Buttons;
import com.godeveloper.currencyconverter.service.utilits.Constants;
import com.godeveloper.currencyconverter.service.utilits.EditMessage;
import com.godeveloper.currencyconverter.service.utilits.MessageBuilder;
import com.godeveloper.currencyconverter.service.utilits.ui.UserServices;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static com.godeveloper.currencyconverter.service.utilits.ui.UserServices.getUserSettingsById;

public class BotCommands {

    private final SendMessage sendMessage;
    private final EditMessage editMessage;
    private final MessageBuilder messageBuilder;

    public BotCommands(TelegramBot telegramBot) {
        this.sendMessage = new SendMessage();
        this.editMessage = new EditMessage(telegramBot);
        this.messageBuilder = new MessageBuilder(telegramBot);
    }

    public void start(long chatId) {
        UserServices.createUserSettings(chatId);

        messageBuilder.sendMessage(chatId, "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!", Buttons.start());
    }

    public void home(long chatId) {
        messageBuilder.sendMessage(chatId, "Ви повернулись на головне меню", Buttons.start());
    }

    public void infoMessage(long chatId) {
        messageBuilder.sendMessage(chatId, UserServices.toNumberFormat(chatId), Buttons.info());
    }

    public void settingsMessage(long chatId) {
        messageBuilder.sendMessage(chatId, "Налаштування", Buttons.setting());
    }

    public void numberSettings(long chatId) {
        messageBuilder.sendMessage(chatId, Constants.NUMBER, Buttons.number(chatId));
    }

    public void currencySettings(long chatId) {
        messageBuilder.sendMessage(chatId, Constants.CURRENCY, Buttons.currency(chatId));
    }

    public void bankSettings(long chatId) {
        messageBuilder.sendMessage(chatId, Constants.BANK, Buttons.bank(chatId));
    }

    public void timeSettings(long chatId) {
        messageBuilder.sendMessage(chatId, Constants.TIME, Buttons.time(chatId));
    }

    public void setTwoNumbers(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("2");

        editMessage.executeEditMessageText(Constants.NUMBER, chatId, messageId, Buttons.number(chatId));
    }

    public void setThreeNumbers(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("3");

        editMessage.executeEditMessageText(Constants.NUMBER, chatId, messageId, Buttons.number(chatId));
    }

    public void setFourNumbers(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("4");

        editMessage.executeEditMessageText(Constants.NUMBER, chatId, messageId, Buttons.number(chatId));
    }

    public void setUSD(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("USD");

        editMessage.executeEditMessageText(Constants.CURRENCY, chatId, messageId, Buttons.currency(chatId));
    }

    public void setEUR(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("EUR");

        editMessage.executeEditMessageText(Constants.CURRENCY, chatId, messageId, Buttons.currency(chatId));
    }

    public void setMono(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Моно");

        editMessage.executeEditMessageText(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }

    public void setPrivat(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Приват");

        editMessage.executeEditMessageText(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }

    public void setNBU(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("НБУ");

        editMessage.executeEditMessageText(Constants.BANK, chatId, messageId, Buttons.bank(chatId));
    }

    public void setTime9(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("09:00");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));
    }

    public void setTime10(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("10:00");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));
    }

    public void setTime11(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("11:00");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));

    }

    public void setTime12(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("12:00");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));
    }

    public void setTime13(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("13:00");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));
    }

    public void setTime14(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("14:00");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));
    }

    public void setTime15(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("15:00");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));
    }

    public void setTime16(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("16:00");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));
    }

    public void setTime17(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("17:00");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));
    }

    public void setTime18(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("18:00");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));
    }

    public void setTimeOff(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("Вимкнути сповіщення");

        editMessage.executeEditMessageText(Constants.TIME, chatId, messageId, Buttons.time(chatId));
    }
}
