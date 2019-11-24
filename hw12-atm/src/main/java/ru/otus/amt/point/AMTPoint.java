package ru.otus.amt.point;

import ru.otus.money.Money;

import java.util.List;

public interface AMTPoint {
    List<Money> get(int count);
    void set(List<Money> monies);
    void set(Money money);
    int balance();
}
