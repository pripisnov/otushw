package ru.otus.money.impl;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.otus.money.Money;
import ru.otus.money.MoneyType;

@Setter
@Getter
@RequiredArgsConstructor
public class MoneyImpl implements Money {
    @NonNull private MoneyType moneyType;
    @NonNull private int count;
}
