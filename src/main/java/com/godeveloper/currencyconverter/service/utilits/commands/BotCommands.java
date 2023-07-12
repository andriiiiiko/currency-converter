package com.godeveloper.currencyconverter.service.utilits.commands;

import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.InlineKeyboardMarkupBuilder;
import com.godeveloper.currencyconverter.service.utilits.ui.UserServices;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.godeveloper.currencyconverter.service.utilits.ui.UserServices.getUserSettingsById;

public class BotCommands {

    private final SendMessage sendMessage;
    private final TelegramBot telegramBot;

    public BotCommands(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.sendMessage = new SendMessage();
    }

    public void start(long chatId) {
        sendMessage(chatId, "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!",
                new String[]{"\uD83D\uDCB1 Отримати інфо", "⚙ Налаштування"});

        UserServices.createUserSettings(chatId);
    }

    public void home(long chatId) {
        sendMessage(chatId, "Ви повернулись на головне меню",
                new String[]{"\uD83D\uDCB1 Отримати інфо", "⚙ Налаштування"});
    }

    public void infoMessage(long chatId) {
        sendMessage(chatId, UserServices.toNumberFormat(chatId),
                new String[]{"⚙ Налаштування"});
    }

    public void settingsMessage(long chatId) {
        sendMessage(chatId, "Налаштування",
                new String[]{
                        "\uD83C\uDFE6 Банк", "\uD83D\uDCB5 Валюта", "\uD83D\uDD52 Час сповіщень",
                        "\uD83D\uDD22 Кількість знаків після коми", "\uD83C\uDFE0 На головну"
                });
    }

    public void currencySettings(long chatId) {
        sendMessage(chatId, "Виберіть валюту",
                new String[]{
                        getUserSettingsById(chatId).getCurrency().equals("USD") ? "✅ USD" : "USD",
                        getUserSettingsById(chatId).getCurrency().equals("EUR") ? "✅ EUR" : "EUR",
                        "\uD83D\uDD19 Назад"
                });
    }

    public void numberSettings(long chatId) {
        sendMessage(chatId, "Виберіть кількість знаків після коми",
                new String[]{
                        getUserSettingsById(chatId).getNumber().equals("2") ? "✅ 2" : "2",
                        getUserSettingsById(chatId).getNumber().equals("3") ? "✅ 3" : "3",
                        getUserSettingsById(chatId).getNumber().equals("4") ? "✅ 4" : "4",
                        "\uD83D\uDD19 Назад"
                });
    }

    public void bankSettings(long chatId) {
        sendMessage(chatId, "Виберіть банк",
                new String[]{
                        getUserSettingsById(chatId).getBank().equals("НБУ") ? "✅ НБУ" : "НБУ",
                        getUserSettingsById(chatId).getBank().equals("Приват") ? "✅ Приват" : "Приват",
                        getUserSettingsById(chatId).getBank().equals("Моно") ? "✅ Моно" : "Моно",
                        "\uD83D\uDD19 Назад"
                });
    }

    public void timeSettings(long chatId) {
        sendMessage(chatId, "Виберіть час сповіщення",
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
    }

    public void setTwoNumbers(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("2");
    }

    public void setThreeNumbers(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("3");
    }

    public void setFourNumbers(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setNumber("4");
    }

    public void setUSD(long chatId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("USD");
    }

    public void setEUR(long chatId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setCurrency("EUR");
    }

    public void setMono(long chatId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Моно");
    }

    public void setPrivat(long chatId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("Приват");
    }

    public void setNBU(long chatId) {
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setBank("НБУ");
    }

    public void setTime9(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("09:00");
    }

    public void setTime10(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("10:00");
    }

    public void setTime11(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("11:00");
    }

    public void setTime12(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("12:00");
    }

    public void setTime13(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("13:00");
    }

    public void setTime14(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("14:00");
    }

    public void setTime15(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("15:00");
    }

    public void setTime16(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("16:00");
    }

    public void setTime17(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("17:00");
    }

    public void setTime18(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("18:00");
    }

    public void setTimeOff(long chatId){
        sendMessage.setChatId(chatId);
        getUserSettingsById(chatId).setTime("Вимкнути сповіщення");
    }

    private void sendMessage(long chatId, String answer, String[] nameButton){
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(nameButton);
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }
}
