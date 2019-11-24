package ru.otus.money.impl;

import lombok.*;
import ru.otus.money.BankCell;
import ru.otus.money.MoneyType;

@RequiredArgsConstructor
public class BankCellImpl implements BankCell {
    private int balance;
    @NonNull private final MoneyType moneyType;

    @Override
    public int get(int count) {
        balance -= count;
        return count;
    }

    @Override
    public void set(double count) {
        balance += count;
    }

    @Override
    public MoneyType getMoneyType() {
        return moneyType;
    }

    @Override
    public int balance() {
        return balance;
    }
}
