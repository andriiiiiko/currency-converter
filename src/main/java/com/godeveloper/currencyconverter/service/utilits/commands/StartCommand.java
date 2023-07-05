package com.godeveloper.currencyconverter.service.utilits.commands;
import com.godeveloper.currencyconverter.service.TelegramBot;
import com.godeveloper.currencyconverter.service.utilits.InlineKeyboardMarkupBuilder;
import com.godeveloper.currencyconverter.service.utilits.Log;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand {

    private final TelegramBot telegramBot;

    public StartCommand(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void execute(long chatId) {
        SendMessage sendMessage = new SendMessage();
        String answer = EmojiParser.parseToUnicode("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют" + " :currency_exchange: !");
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"Отримати інфо", "Налаштування"});
        sendMessage.setReplyMarkup(markup);

        telegramBot.executeMessage(sendMessage);
    }
}