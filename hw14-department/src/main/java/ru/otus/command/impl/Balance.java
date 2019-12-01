package ru.otus.command.impl;

import ru.otus.command.Command;
import ru.otus.departmet.ATMListener;
import ru.otus.message.Message;
import ru.otus.message.impl.MessageImpl;

public class Balance implements Command {
    @Override
    public Message execute(ATMListener listener) {
        return new MessageImpl(String.valueOf(listener.getBalance()), listener.getId());
    }

    @Override
    public String toString() {
        return "balance";
    }
}
