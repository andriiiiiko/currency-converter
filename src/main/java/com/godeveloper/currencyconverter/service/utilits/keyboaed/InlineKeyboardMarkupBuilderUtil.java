package com.godeveloper.currencyconverter.service.utilits.keyboaed;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardMarkupBuilderUtil {

    public static InlineKeyboardMarkup buildMarkup(String[] buttonTexts) {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonText : buttonTexts) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonText);
            button.setCallbackData(buttonText.toUpperCase());
            keyboard.add(List.of(button));
        }

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);
        return markup;
    }
}