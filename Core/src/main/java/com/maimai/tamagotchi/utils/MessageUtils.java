package com.maimai.tamagotchi.utils;

import com.maimai.tamagotchi.ProgramCore;

import java.util.Arrays;

public class MessageUtils {

    public static void sendMessage(ProgramCore core, String path) {
        core.getLanguageManager().find(core.getPlayer().getLanguage()).ifPresent(file -> {
            if(file.getString(path).isEmpty()) {
                System.out.println("An error occurred while trying to send the message " + path);
                return;
            }
            System.out.println(file.getString(path));
        });
    }

    public static void sendCenteredMessage(String message) {
        System.out.println(centerString(message));
    }

    public static void sendCenteredMessages(String... messages) {
        Arrays.asList(messages).forEach(MessageUtils::sendCenteredMessage);
    }

    public static String centerString(String str) {

        if (str == null) {
            throw new NullPointerException("Can not add padding in null String!");
        }

        int maxPadding = 20;
        int length = str.length();
        int padding = (maxPadding - length) / 2;
        if (padding <= 0) {
            return str;
        }

        String empty = "", hash = "#";

        int extra = (length % 2 == 0) ? 1 : 0;

        String leftPadding = "%" + padding + "s";
        String rightPadding = "%" + (padding - extra) + "s";

        String strFormat = leftPadding + "%s" + rightPadding;
        String formattedString = String.format(strFormat, empty, hash, empty);

        return formattedString.replace(hash, str);
    }

}
