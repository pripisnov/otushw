package ru.otus.amt;

import ru.otus.money.BankCell;
import ru.otus.money.Money;

public interface AMT {
    Money getMoney(Money money);
    void setMoney(Money money);
    int getBalance();
    void addBankCell(BankCell bankCell);
    int getBankCellBalance(BankCell bankCell);
    int getBankCellBalance(int nominal);
    BankCell removeBankCell(BankCell bankCell);
    int[] getNominal();
}
