package ru.otus;

import ru.otus.amt.impl.ATMImpl;
import ru.otus.money.impl.BankCellImpl;

import static ru.otus.money.Currency.*;

public class Main {
    public static void main(String[] args) {
        ATMImpl sberbankAMT = new ATMImpl(RUB);

        System.out.println("id: " + sberbankAMT.getId() + " AMT count: " + ATMImpl.getAmtCount());

        sberbankAMT.addBankCell(new BankCellImpl(RUB, 100));
        sberbankAMT.addBankCell(new BankCellImpl(RUB, 10));
        sberbankAMT.addBankCell(new BankCellImpl(RUB, 1));
        sberbankAMT.addBankCell(new BankCellImpl(EURO, 100));
        sberbankAMT.addBankCell(new BankCellImpl(EURO, 10));
        sberbankAMT.addBankCell(new BankCellImpl(DOLLAR, 100));

        sberbankAMT.setMoney(100, 100, 1, 1);
        System.out.println(sberbankAMT.getCurrencyName() + ": " + sberbankAMT.getBalance());


        sberbankAMT.setMoney(10, 5);
        sberbankAMT.setMoney(1, 15);
        System.out.println(sberbankAMT.getCurrencyName() + ": " + sberbankAMT.getBalance());

        System.out.println("Cash: " + sberbankAMT.getMoney(21));
        System.out.println(sberbankAMT.getCurrencyName() + ": " + sberbankAMT.getBalance());

        sberbankAMT.setCurrency(EURO);
        sberbankAMT.setMoney(100, 10);
        sberbankAMT.setMoney(10, 1);
        System.out.println(sberbankAMT.getCurrencyName() + ": " + sberbankAMT.getBalance());

        System.out.println("Cash: " + sberbankAMT.getMoney(200));
        System.out.println(sberbankAMT.getCurrencyName() + ": " + sberbankAMT.getBalance());

        System.out.println("Cash: " + sberbankAMT.getMoney(10));
        System.out.println(sberbankAMT.getCurrencyName() + ": " + sberbankAMT.getBalance());

        sberbankAMT.setCurrency(RUB);
        System.out.println(sberbankAMT.getCurrencyName() + ": " + sberbankAMT.getBalance());

        sberbankAMT.setCurrency(DOLLAR);
        System.out.println(sberbankAMT.getCurrencyName() + ": " + sberbankAMT.getBalance());
    }
}
