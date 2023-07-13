package com.godeveloper.currencyconverter.service.utilits.commands;

import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.InlineKeyboardMarkupBuilder;
import com.godeveloper.currencyconverter.service.utilits.ui.UserServices;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.godeveloper.currencyconverter.service.utilits.ui.UserServices.getUserSettingsById;

public class BotCommands {

    private final SendMessage sendMessage;
    private final DeleteMessage deleteMessage;
    private final TelegramBot telegramBot;

    public BotCommands(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.sendMessage = new SendMessage();
        this.deleteMessage = new DeleteMessage();
    }

    public void start(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!");
        UserServices.createUserSettings(chatId);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"\uD83D\uDCB1 Отримати інфо", "⚙ Налаштування"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeSendMessage(sendMessage);
    }

    public void home(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText("Ви повернулись на головне меню");

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"\uD83D\uDCB1 Отримати інфо", "⚙ Налаштування"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeSendMessage(sendMessage);
    }

    public void infoMessage(long chatId) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(UserServices.toNumberFormat(chatId));

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"⚙ Налаштування"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeSendMessage(sendMessage);
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

        telegramBot.executeSendMessage(sendMessage);
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

        telegramBot.executeSendMessage(sendMessage);
    }

    public void setTwoNumbers(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("2");
        deleteMessage(chatId, messageId);
        numberSettings(chatId);
    }

    public void setThreeNumbers(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("3");
        deleteMessage(chatId, messageId);
        numberSettings(chatId);
    }

    public void setFourNumbers(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("4");
        deleteMessage(chatId, messageId);
        numberSettings(chatId);
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

        telegramBot.executeSendMessage(sendMessage);
    }

    public void setUSD(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("USD");
        deleteMessage(chatId, messageId);
        currencySettings(chatId);
    }

    public void setEUR(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("EUR");
        deleteMessage(chatId, messageId);
        currencySettings(chatId);
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

        telegramBot.executeSendMessage(sendMessage);
    }

    public void setMono(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Моно");
        deleteMessage(chatId, messageId);
        bankSettings(chatId);
    }

    public void setPrivat(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Приват");
        deleteMessage(chatId, messageId);
        bankSettings(chatId);
    }

    public void setNBU(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("НБУ");
        deleteMessage(chatId, messageId);
        bankSettings(chatId);
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

        telegramBot.executeSendMessage(sendMessage);
    }

    public void setTime9(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("09:00");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void setTime10(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("10:00");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void setTime11(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("11:00");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void setTime12(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("12:00");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void setTime13(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("13:00");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void setTime14(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("14:00");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void setTime15(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("15:00");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void setTime16(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("16:00");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void setTime17(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("17:00");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void setTime18(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("18:00");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void setTimeOff(long chatId, int messageId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("Вимкнути сповіщення");
        deleteMessage(chatId, messageId);
        timeSettings(chatId);
    }

    public void deleteMessage(long chatId, int messageId){
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);

        telegramBot.executeDeleteMessage(deleteMessage);
    }
}
