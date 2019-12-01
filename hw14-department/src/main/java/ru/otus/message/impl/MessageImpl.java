package ru.otus.message.impl;

import ru.otus.message.Message;

public class MessageImpl implements Message {
    private final String message;
    private final int listenerId;

    public MessageImpl(String message, int listenerId) {
        this.message = message;
        this.listenerId = listenerId;
    }

    @Override
    public int getListenerId() {
        if (listenerId <= 0)
            throw new IllegalArgumentException("failed listener id");
        return listenerId;
    }

    @Override
    public String getMessage() {
        if (message == null)
            throw new NullPointerException("message is null");
        return message;
    }
}
