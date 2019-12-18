package ru.otus.momento;

import java.util.ArrayDeque;
import java.util.Deque;

public class ATMOriginator {
    private final Deque<ATMemento> stack = new ArrayDeque<>();

    public void saveState(ATMState atmState) {
        stack.push(new ATMemento(atmState));
    }

    public ATMState restoreState() {
        return stack.pop().getAtmState();
    }
}
