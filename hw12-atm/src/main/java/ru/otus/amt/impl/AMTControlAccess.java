package ru.otus.amt.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.otus.amt.AMT;
import ru.otus.money.BankCell;
import ru.otus.money.Money;

@RequiredArgsConstructor
public class AMTControlAccess implements AMT {
    private final AMT amt;

    @SneakyThrows
    @Override
    public Money getMoney(Money money) {
        if (isContainsNominal(money)) {
            int balance = getBankCellBalance(money.getMoneyType().getNominal());
            if (balance >= money.getCount())
                return amt.getMoney(money);
            else
                throw new IllegalAccessException("requested money count must be a little bit less");
        }

        else
            throw new IllegalAccessException("failed nominal money");
    }

    @SneakyThrows
    @Override
    public void setMoney(Money money) {
        if (isContainsNominal(money))
            amt.setMoney(money);
        else
            throw new IllegalAccessException("failed nominal money");
    }

    @Override
    public int getBalance() {
        return amt.getBalance();
    }

    @SneakyThrows
    @Override
    public void addBankCell(BankCell bankCell) {
        if (!isContainsNominal(bankCell))
            amt.addBankCell(bankCell);
        else
            throw new IllegalAccessException("already added nominal bank cell");

    }

    @Override
    public int getBankCellBalance(BankCell bankCell) {
        return getBankCellBalance(bankCell.getMoneyType().getNominal());
    }

    @SneakyThrows
    @Override
    public int getBankCellBalance(int nominal) {
        if (isContainsNominal(nominal))
            return amt.getBankCellBalance(nominal);
        else
            throw new IllegalAccessException("failed nominal bank cell");
    }

    @SneakyThrows
    @Override
    public BankCell removeBankCell(BankCell bankCell) {
        if (isContainsNominal(bankCell))
            return amt.removeBankCell(bankCell);
        else
            throw new IllegalAccessException("failed nominal bank cell");
    }

    @Override
    public int[] getNominal() {
        return amt.getNominal();
    }

    private boolean isContainsNominal(BankCell bankCell) {
        int nominal = bankCell.getMoneyType().getNominal();
        return isContainsNominal(nominal);
    }

    private boolean isContainsNominal(Money money) {
        int nominal = money.getMoneyType().getNominal();
        return isContainsNominal(nominal);
    }

    private boolean isContainsNominal(int nominal) {
        int[] nominals = getNominal();
        for (int i : nominals) {
            if (nominal == i)
                return true;
        }
        return false;
    }
}
