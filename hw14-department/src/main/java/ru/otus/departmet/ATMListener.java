package ru.otus.departmet;

import ru.otus.amt.ATM;
import ru.otus.command.Command;
import ru.otus.message.Message;
import ru.otus.momento.ATMOriginator;
import ru.otus.momento.ATMState;
import ru.otus.money.BankCell;
import ru.otus.money.Currency;
import ru.otus.observer.Listener;

import java.util.List;

public class ATMListener implements Listener, ATM {
    private final ATM atm;
    private final ATMOriginator atmOriginator = new ATMOriginator();

    public ATMListener(ATM atm) {
        this.atm = atm;
        this.atmOriginator.saveState(new ATMState(atm));
    }

    @Override
    public List<Integer> getMoney(int money) {
        return atm.getMoney(money);
    }

    @Override
    public void setMoney(Integer... money) {
        atm.setMoney(money);
    }

    @Override
    public void setMoney(int nominal, int count) {
        atm.setMoney(nominal, count);
    }

    @Override
    public int getBalance() {
        return atm.getBalance();
    }

    @Override
    public void addBankCell(BankCell bankCell) {
        atm.addBankCell(bankCell);
    }

    @Override
    public boolean removeBankCell(BankCell bankCell) {
        return atm.removeBankCell(bankCell);
    }

    @Override
    public List<BankCell> getBankCells() {
        return atm.getBankCells();
    }

    @Override
    public List<Integer> getNominal() {
        return atm.getNominal();
    }

    @Override
    public int getId() {
        return atm.getId();
    }

    @Override
    public ATM copy() {
        return this.atm.copy();
    }

    @Override
    public ATM copy(ATM atm) {
        return this.atm.copy(atm);
    }

    @Override
    public Currency getCurrency() {
        return atm.getCurrency();
    }

    @Override
    public Message update(Command command) {
        return command.execute(this);
    }

    public void restore() {
        copy(atmOriginator.restoreState().getAtm());
    }
}
