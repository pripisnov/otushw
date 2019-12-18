package ru.otus.amt;

import ru.otus.money.BankCell;
import ru.otus.money.Currency;

import java.util.List;

public interface ATM {
    List<Integer> getMoney(int money);
    void setMoney(Integer... money);
    void setMoney(int nominal, int count);
    int getBalance();
    void addBankCell(BankCell bankCell);
    boolean removeBankCell(BankCell bankCell);
    List<Integer> getNominal();
    int getId();
    List<BankCell> getBankCells();
    Currency getCurrency();
    ATM copy();
    ATM copy(ATM atm);
}
