package ru.otus.departmet;

import ru.otus.command.Command;
import ru.otus.command.impl.Balance;
import ru.otus.message.Message;
import ru.otus.observer.Listener;
import ru.otus.observer.Producer;

import java.util.ArrayList;
import java.util.List;

public class ATMDepartment implements Producer {
    private final List<Listener> listeners = new ArrayList<>();

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public List<Message> command(Command command) {
        List<Message> messages = new ArrayList<>();
        for (Listener listener : listeners) {
            messages.add(listener.update(command));
        }
        return messages;
    }

    public int sumBalance() {
        int sum = 0;
        for (Message message : command(new Balance())) {
            sum += Integer.parseInt(message.getMessage());
        }
        return sum;
    }
}
