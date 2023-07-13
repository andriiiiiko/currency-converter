package com.godeveloper.currencyconverter.service.utilits.commands;

import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.Buttons;
import com.godeveloper.currencyconverter.service.utilits.EditMessage;
import com.godeveloper.currencyconverter.service.utilits.InlineKeyboardMarkupBuilder;
import com.godeveloper.currencyconverter.service.utilits.ui.UserServices;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.godeveloper.currencyconverter.service.utilits.ui.UserServices.getUserSettingsById;

public class BotCommands {

    private final SendMessage sendMessage;
    private final TelegramBot telegramBot;
    private final EditMessage editMessage;

    public BotCommands(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.sendMessage = new SendMessage();
        this.editMessage = new EditMessage(telegramBot);
    }

    public void start(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!");
        UserServices.createUserSettings(chatId);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"\uD83D\uDCB1 Отримати інфо", "⚙ Налаштування"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void home(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Ви повернулись на головне меню");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"\uD83D\uDCB1 Отримати інфо", "⚙ Налаштування"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void infoMessage(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(UserServices.toNumberFormat(chatId));

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"⚙ Налаштування"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void settingsMessage(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Налаштування");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        "\uD83C\uDFE6 Банк", "\uD83D\uDCB5 Валюта", "\uD83D\uDD52 Час сповіщень",
                        "\uD83D\uDD22 Кількість знаків після коми", "\uD83C\uDFE0 На головну"
                });
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void numberSettings(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Виберіть кількість знаків після коми");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getNumber().equals("2") ? "✅ 2" : "2",
                        getUserSettingsById(chatId).getNumber().equals("3") ? "✅ 3" : "3",
                        getUserSettingsById(chatId).getNumber().equals("4") ? "✅ 4" : "4",
                        "\uD83D\uDD19 Назад"
                });
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void currencySettings(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Виберіть валюту");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getCurrency().equals("USD") ? "✅ USD" : "USD",
                        getUserSettingsById(chatId).getCurrency().equals("EUR") ? "✅ EUR" : "EUR",
                        "\uD83D\uDD19 Назад"
                });
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void bankSettings(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Виберіть банк");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getBank().equals("НБУ") ? "✅ НБУ" : "НБУ",
                        getUserSettingsById(chatId).getBank().equals("Приват") ? "✅ Приват" : "Приват",
                        getUserSettingsById(chatId).getBank().equals("Моно") ? "✅ Моно" : "Моно",
                        "\uD83D\uDD19 Назад"
                });
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void timeSettings(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Виберіть час сповіщення");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{
                        getUserSettingsById(chatId).getTime().equals("09:00") ? "✅ 09:00" : "09:00",
                        getUserSettingsById(chatId).getTime().equals("10:00") ? "✅ 10:00" : "10:00",
                        getUserSettingsById(chatId).getTime().equals("11:00") ? "✅ 11:00" : "11:00",
                        getUserSettingsById(chatId).getTime().equals("12:00") ? "✅ 12:00" : "12:00",
                        getUserSettingsById(chatId).getTime().equals("13:00") ? "✅ 13:00" : "13:00",
                        getUserSettingsById(chatId).getTime().equals("14:00") ? "✅ 14:00" : "14:00",
                        getUserSettingsById(chatId).getTime().equals("15:00") ? "✅ 15:00" : "15:00",
                        getUserSettingsById(chatId).getTime().equals("16:00") ? "✅ 16:00" : "16:00",
                        getUserSettingsById(chatId).getTime().equals("17:00") ? "✅ 17:00" : "17:00",
                        getUserSettingsById(chatId).getTime().equals("18:00") ? "✅ 18:00" : "18:00",
                        getUserSettingsById(chatId).getTime().equals("Вимкнути сповіщення") ?
                                "✅ Вимкнути сповіщення" : "Вимкнути сповіщення",
                        "\uD83D\uDD19 Назад"
                });
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }

    public void setTwoNumbers(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("2");

        editMessage.executeEditMessageText("Виберіть кількість знаків після коми", chatId, messageId, Buttons.number(chatId));
    }

    public void setThreeNumbers(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("3");

        editMessage.executeEditMessageText("Виберіть кількість знаків після коми", chatId, messageId, Buttons.number(chatId));
    }

    public void setFourNumbers(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("4");

        editMessage.executeEditMessageText("Виберіть кількість знаків після коми", chatId, messageId, Buttons.number(chatId));
    }

    public void setUSD(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("USD");

        editMessage.executeEditMessageText("Виберіть валюту", chatId, messageId, Buttons.currency(chatId));
    }

    public void setEUR(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("EUR");

        editMessage.executeEditMessageText("Виберіть валюту", chatId, messageId, Buttons.currency(chatId));
    }

    public void setMono(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Моно");

        editMessage.executeEditMessageText("Виберіть банк", chatId, messageId, Buttons.bank(chatId));
    }

    public void setPrivat(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Приват");

        editMessage.executeEditMessageText("Виберіть банк", chatId, messageId, Buttons.bank(chatId));
    }

    public void setNBU(long chatId, long messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("НБУ");

        editMessage.executeEditMessageText("Виберіть банк", chatId, messageId, Buttons.bank(chatId));
    }

    public void setTime9(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("09:00");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));
    }

    public void setTime10(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("10:00");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));
    }

    public void setTime11(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("11:00");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));

    }

    public void setTime12(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("12:00");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));
    }

    public void setTime13(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("13:00");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));
    }

    public void setTime14(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("14:00");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));
    }

    public void setTime15(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("15:00");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));
    }

    public void setTime16(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("16:00");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));
    }

    public void setTime17(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("17:00");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));
    }

    public void setTime18(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("18:00");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));
    }

    public void setTimeOff(long chatId, long messageId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("Вимкнути сповіщення");

        editMessage.executeEditMessageText("Виберіть час сповіщення", chatId, messageId, Buttons.time(chatId));
    }
}
