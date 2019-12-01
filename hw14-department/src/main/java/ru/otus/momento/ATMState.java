package ru.otus.momento;

import ru.otus.amt.ATM;

public class ATMState {
    private final ATM atm;

    public ATMState(ATM atm) {
        this.atm = atm;
    }

    public ATMState(ATMState atmState) {
        this.atm = atmState.getAtm().copy();
    }

    public ATM getAtm() {
        return atm;
    }
}
