package ru.otus;

import ru.otus.amt.impl.AMTControlAccess;
import ru.otus.amt.impl.ATMImpl;
import ru.otus.amt.point.AMTPoint;
import ru.otus.amt.point.impl.SberAMTPoint;
import ru.otus.money.Money;
import ru.otus.money.impl.BankCellImpl;
import ru.otus.money.impl.MoneyImpl;
import ru.otus.money.impl.RubleMoneyType;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AMTControlAccess amt = new AMTControlAccess(new ATMImpl());
        amt.addBankCell(new BankCellImpl(new RubleMoneyType(100)));
        amt.addBankCell(new BankCellImpl(new RubleMoneyType(50)));
        amt.addBankCell(new BankCellImpl(new RubleMoneyType(10)));
        amt.addBankCell(new BankCellImpl(new RubleMoneyType(5)));
        amt.addBankCell(new BankCellImpl(new RubleMoneyType(1)));

        AMTPoint amtPoint = new SberAMTPoint(amt);
        amtPoint.set(new MoneyImpl(new RubleMoneyType(100), 5));
        amtPoint.set(new MoneyImpl(new RubleMoneyType(50), 10));
        amtPoint.set(new MoneyImpl(new RubleMoneyType(5), 10));
        amtPoint.set(new MoneyImpl(new RubleMoneyType(10), 20));
        amtPoint.set(new MoneyImpl(new RubleMoneyType(1), 200));

        System.out.println("balance: " + amtPoint.balance());

        List<Money> monies = amtPoint.get(148);

        System.out.println("balance: " + amtPoint.balance());

        for (Money m : monies)
            System.out.println("nominal=" + m.getMoneyType().getNominal() + ": " + m.getCount());
    }
}
