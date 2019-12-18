package ru.otus.observer;

import ru.otus.command.Command;
import ru.otus.message.Message;

import java.util.List;

public interface Producer {
    void addListener(Listener listener);
    void removeListener(Listener listener);
    List<Message> command(Command command);
}
