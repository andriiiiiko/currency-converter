package com.team1.currencyconverter.service.utilits.commands;

import com.team1.currencyconverter.service.utilits.Keyboaed.InlineKeyboardMarkupBuilder;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;

public class StartCommand extends BotCommand {

    public StartCommand() {
        super("start", "Start command!");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String answer = EmojiParser.parseToUnicode("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют" + " :currency_exchange: !");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chat.getId()));
        sendMessage.setText(answer);

        InlineKeyboardMarkup markup = InlineKeyboardMarkupBuilder.buildMarkup(
                new String[]{"Отримати інфо", "Налаштування"});
        sendMessage.setReplyMarkup(markup);


        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
