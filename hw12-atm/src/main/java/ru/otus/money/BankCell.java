package ru.otus.money;

public interface BankCell {
    int get(int count);
    void set(int count);
    int balance();
    int getNominal();
    Currency getCurrency();
}
