package com.yody.common.utility;

public class MessageBuilder {

    private MessageBuilder() {
    }

    public static String messageWithArguments(String message, String... args) {
        String finalMessage = message;
        for (int i = 0; i < args.length; i++) {
            String param = String.format("{%d}", i);
            finalMessage = finalMessage.replace(param, args[i]);
        }
        return finalMessage;
    }
}
