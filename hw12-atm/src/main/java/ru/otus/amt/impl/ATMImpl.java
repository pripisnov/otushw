package ru.otus.amt.impl;

import lombok.Getter;
import ru.otus.amt.AMT;
import ru.otus.money.BankCell;
import ru.otus.money.Money;

import java.util.Map;
import java.util.TreeMap;

public class ATMImpl implements AMT {
    @Getter private static int id;
    private int balance;
    private Map<Integer, BankCell> bankCells;

    public ATMImpl() {
        this.bankCells = new TreeMap<>();
        ++id;
    }

    @Override
    public Money getMoney(Money money) {
        int nominal = money.getMoneyType().getNominal();
        int count = money.getCount();
        bankCells.get(nominal).get(count);
        balance -= count * nominal;
        return money;
    }

    @Override
    public void setMoney(Money money) {
        int nominal = money.getMoneyType().getNominal();
        int count = money.getCount();
        bankCells.get(nominal).set(count);
        balance += count * nominal;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void addBankCell(BankCell bankCell) {
        balance += bankCell.balance();
        bankCells.put(bankCell.getMoneyType().getNominal(), bankCell);
    }

    @Override
    public int getBankCellBalance(BankCell bankCell) {
        return getBankCellBalance(bankCell.getMoneyType().getNominal());
    }

    @Override
    public int getBankCellBalance(int nominal) {
        return bankCells.get(nominal).balance();
    }

    @Override
    public BankCell removeBankCell(BankCell bankCell) {
        balance -= bankCell.balance();
        return bankCells.remove(bankCell.getMoneyType().getNominal());
    }

    @Override
    public int[] getNominal() {
        int[] nom = new int[bankCells.size()];
        int current = 0;
        for (Integer i : bankCells.keySet()) {
            nom[current++] = i;
        }
        return nom;
    }
}
