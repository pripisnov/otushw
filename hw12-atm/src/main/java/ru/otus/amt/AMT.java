package ru.otus.amt;

import ru.otus.money.BankCell;

import java.util.List;

public interface AMT {
    List<Integer> getMoney(int money);
    void setMoney(Integer... money);
    void setMoney(int nominal, int count);
    int getBalance();
    void addBankCell(BankCell bankCell);
    boolean removeBankCell(BankCell bankCell);
    List<Integer> getNominal();
}
