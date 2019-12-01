package ru.otus.command;

import ru.otus.departmet.ATMListener;
import ru.otus.message.Message;

public interface Command {
    Message execute(ATMListener amtListener);
}
