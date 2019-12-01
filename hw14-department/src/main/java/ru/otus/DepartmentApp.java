package ru.otus;

import ru.otus.amt.impl.ATMImpl;
import ru.otus.command.impl.Balance;
import ru.otus.command.impl.Restore;
import ru.otus.departmet.ATMDepartment;
import ru.otus.departmet.ATMListener;
import ru.otus.message.Message;
import ru.otus.money.impl.BankCellImpl;

import java.util.List;

import static ru.otus.money.Currency.*;

public class DepartmentApp {
    public static void main(String[] args) {
        ATMImpl sber = new ATMImpl(RUB);
        sber.addBankCell(new BankCellImpl(RUB, 100));
        sber.setMoney(100, 10);

        ATMImpl tinkoff = new ATMImpl(RUB);
        tinkoff.addBankCell(new BankCellImpl(RUB, 100));
        tinkoff.setMoney(100, 5);

        ATMListener sberListener = new ATMListener(sber);
        ATMListener tinkoffListener = new ATMListener(tinkoff);

        ATMDepartment department = new ATMDepartment();
        department.addListener(sberListener);
        department.addListener(tinkoffListener);

        print(department.command(new Balance()));

        sber.getMoney(200);
        tinkoff.getMoney(200);

        print(department.command(new Balance()));

        print(department.command(new Restore()));

        print(department.command(new Balance()));

        System.out.println("sum: " + department.sumBalance());

        department.removeListener(tinkoffListener);

        print(department.command(new Balance()));
    }

    public static void print(List<Message> messages) {
        for (Message m : messages) {
            System.out.println(m.getListenerId() + ": " + m.getMessage());
        }
    }
}
