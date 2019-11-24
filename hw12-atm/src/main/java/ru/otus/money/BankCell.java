package ru.otus.money;

public interface BankCell {
    int get(int count);
    void set(double count);
    MoneyType getMoneyType();
    int balance();
}
