package com.team1.currencyconverter.service;

import com.team1.currencyconverter.config.BotConfig;
import com.team1.currencyconverter.service.utilits.Log;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private Map<Long,  Integer> levels = new HashMap<>();
    final BotConfig config;


    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                startCommandStart(chatId, update.getMessage().getChat().getUserName());
            }
        }

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callbackData.equals("INFO_BUTTON")) {
                infoMessage(chatId, update.getCallbackQuery().getData());
            } else if (callbackData.equals("SETTINGS BUTTON")) {
                settingsMessage(chatId, update.getCallbackQuery().getData());
            } else if (callbackData.equals("NUMBER")) {
                numberSetiings(chatId, update.getCallbackQuery().getData());
            } else if (callbackData.equals("CURRENCIES")) {
                currencySettings(chatId, update.getCallbackQuery().getData());
            } else if (callbackData.equals("BANK")) {
                bankSetiings(chatId, update.getCallbackQuery().getData());
            } else if (callbackData.equals("TIME")) {
                timeSetiings(chatId, update.getCallbackQuery().getData());
            }
        }

    }

    private void startCommandStart(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют" + " :currency_exchange: !");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(name);

        List<String> buttons = Arrays.asList(
                "Отримати інфо",
                "Налаштування"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "INFO_BUTTON",
                buttons.get(1), "SETTINGS BUTTON"
        ));


        executeMessage(message);
    }

    private void infoMessage(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Курс в Приват банк: USD/UAH\n" +
                "Купівлля: 38.55\n" +
                "Продажа: 39.60");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(str);

        List<String> buttons = Arrays.asList(
                "Отримати інфо",
                "Налаштування"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "INFO_BUTTON",
                buttons.get(1), "SETTINGS BUTTON"
        ));


        executeMessage(message);
    }

    private void settingsMessage(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Налаштування");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(str);

        List<String> buttons = Arrays.asList(
                "Кількість знаків після коми",
                "Банк",
                "Валюти",
                "Час сповіщень"

        );
        attachButtons(message, Map.of(
                buttons.get(0), "NUMBER",
                buttons.get(1), "BANK",
                buttons.get(2), "CURRENCIES",
                buttons.get(3), "TIME"
        ));


        executeMessage(message);
    }

    private void numberSetiings(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Виберіть кулькість знаків після коми");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(str);

        List<String> buttons = Arrays.asList(
                "2",
                "3",
                "4"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "N_2",
                buttons.get(1), "N_3",
                buttons.get(2), "N_4"
        ));


        executeMessage(message);
    }

    private void currencySettings(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Виберіть валюту");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();


        Log.Info(str);

        List<String> buttons = Arrays.asList(
                "USD",
                "EUR"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "B_USD",
                buttons.get(1), "B_EUR"
        ));


        executeMessage(message);
    }

    private void bankSetiings(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Виберіть банк");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(str);

        List<String> buttons = Arrays.asList(
                "НБУ",
                "Приват банк",
                "Райфайзен банк"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "NBU",
                buttons.get(1), "PB",
                buttons.get(2), "RF"
        ));


        executeMessage(message);
    }

    private void timeSetiings(long chatId, String str) {
        String answer = EmojiParser.parseToUnicode("Виберіть час сповіщення");

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);

        Log.Info(str);

        List<String> buttons = Arrays.asList(
                "Виключити сповіщення",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00"
        );
        attachButtons(message, Map.of(
                buttons.get(0), "T_09",
                buttons.get(1), "T_10",
                buttons.get(2), "T_11",
                buttons.get(3), "T_12",
                buttons.get(4), "T_13",
                buttons.get(5), "T_14",
                buttons.get(6), "T_15",
                buttons.get(7), "T_16",
                buttons.get(8), "T_17",
                buttons.get(9), "T_18"
        ));

        executeMessage(message);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }

    public void attachButtons(SendMessage message, Map<String, String> buttons){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buutonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes()));
            button.setCallbackData(buutonValue);

            keyboard.add(Arrays.asList(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }
}