package ru.otus.money.impl;

import ru.otus.money.BankCell;
import ru.otus.money.Currency;

public class BankCellImpl implements BankCell {
    private int balance;
    private final Currency currency;
    private final int nominal;

    public BankCellImpl(Currency currency, int nominal) {
        this.currency = currency;
        this.nominal = nominal;
    }

    @Override
    public int getNominal() {
        return nominal;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public int get(int count) {
        balance -= count;
        return count;
    }

    @Override
    public void set(int count) {
        balance += count;
    }

    @Override
    public int balance() {
        return balance;
    }
}
