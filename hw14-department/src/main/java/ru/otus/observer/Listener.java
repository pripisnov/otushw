package ru.otus.observer;

import ru.otus.command.Command;
import ru.otus.message.Message;

public interface Listener {
    Message update(Command command);
}
