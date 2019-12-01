package ru.otus.momento;

public class ATMemento {
    private final ATMState ATMState;

    ATMemento(ATMState atmState) {
        this.ATMState = new ATMState(atmState);
    }

    ATMState getAtmState() {
        return ATMState;
    }
}
