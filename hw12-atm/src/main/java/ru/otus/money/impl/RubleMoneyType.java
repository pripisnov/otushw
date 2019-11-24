package ru.otus.money.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.otus.money.Currency;
import ru.otus.money.MoneyType;

@RequiredArgsConstructor
public class RubleMoneyType implements MoneyType {
    private static final Currency currency = Currency.RUB;
    @NonNull private int nominal;

    @Override
    public int getNominal() {
        return nominal;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }
}
