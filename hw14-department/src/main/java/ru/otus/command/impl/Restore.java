package ru.otus.command.impl;

import ru.otus.command.Command;
import ru.otus.departmet.ATMListener;
import ru.otus.message.Message;
import ru.otus.message.impl.MessageImpl;

public class Restore implements Command {
    @Override
    public Message execute(ATMListener amtListener) {
        amtListener.restore();
        return new MessageImpl("restore done", amtListener.getId());
    }

    @Override
    public String toString() {
        return "restore";
    }
}
