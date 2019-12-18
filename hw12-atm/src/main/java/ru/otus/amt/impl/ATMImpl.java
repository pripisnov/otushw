package ru.otus.amt.impl;

import ru.otus.amt.ATM;
import ru.otus.money.BankCell;
import ru.otus.money.Currency;
import ru.otus.money.impl.BankCellImpl;

import java.util.*;

public class ATMImpl implements ATM {
    private static int AMT_COUNT;
    private static final String ERR_NOMINAL_MESSAGE = "failed nominal";
    private static final String ERR_CURRENCY_MESSAGE = "failed currency";
    private static final String ERR_ZERO_OR_NEG_REQUEST = "zero or negative cash request";
    private static final String ERR_NON_MULTIPLE_REQUEST = "request is non-multiple of nominal of cells";
    private static final String ERR_TO_BIG_REQUEST = "to big cash request";

    private int id;
    private List<BankCell> bankCells;
    private Set<Currency> currencies;
    private Currency currency;

    public ATMImpl(ATM atm) {
        this.bankCells = new ArrayList<>();
        this.currencies = new HashSet<>();
        this.copy(atm);
    }

    public ATMImpl(Currency currency) {
        this.currency = currency;
        this.bankCells = new ArrayList<>();
        this.currencies = new HashSet<>();
        this.id = ++AMT_COUNT;
    }

    public static int getAmtCount() {
        return AMT_COUNT;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getCurrencyName() {
        return currency.name();
    }

    public void setCurrency(Currency currency) {
        if (!currencies.contains(currency))
            throw new IllegalArgumentException(ERR_CURRENCY_MESSAGE);
        this.currency = currency;
    }

    @Override
    public List<Integer> getMoney(int money) {
        if (money <= 0)
            throw new IllegalArgumentException(ERR_ZERO_OR_NEG_REQUEST);
        if (!isMultiple(money))
            throw new IllegalArgumentException(ERR_NON_MULTIPLE_REQUEST);
        if (getBalance() >= money)
            return getMinimize(money);
        else
            throw new IllegalArgumentException(ERR_TO_BIG_REQUEST);
    }

    @Override
    public void setMoney(Integer... money) {
        this.set(money);
    }

    @Override
    public void setMoney(int nominal, int count) {
        if (nominal <= 0 || count <= 0)
            throw new IllegalArgumentException(ERR_ZERO_OR_NEG_REQUEST);
        this.getBankCell(nominal).set(count);
    }

    @Override
    public int getBalance() {
        return calcBalance();
    }

    @Override
    public void addBankCell(BankCell bankCell) {
        bankCells.add(bankCell);
        currencies.add(bankCell.getCurrency());
    }

    @Override
    public boolean removeBankCell(BankCell bankCell) {
        if (!currencies.contains(bankCell.getCurrency()))
            throw new IllegalArgumentException(ERR_CURRENCY_MESSAGE);
        return bankCells.remove(getBankCell(bankCell.getNominal()));
    }

    @Override
    public List<BankCell> getBankCells() {
        return bankCells;
    }

    private BankCell getBankCell(int nominal) {
        for (BankCell cell : bankCells) {
            if (cell.getCurrency() == currency && cell.getNominal() == nominal)
                return cell;
        }
        throw new IllegalArgumentException(ERR_NOMINAL_MESSAGE);
    }

    private List<Integer> getMinimize(int count) {
        List<Integer> nominals = getNominal();

        List<Integer> money = new ArrayList<>();
        int moneyCount = count;

        Collections.sort(nominals);

        for (int i = nominals.size() - 1; i >= 0; --i) {
            int nominal = nominals.get(i);
            int n = moneyCount / nominal;
            BankCell bankCell = getBankCell(nominal);
            if (bankCell.balance() < n)
                n = bankCell.balance();
            moneyCount -= bankCell.get(n) * nominal;
            for (int j = n; j > 0; --j)
                money.add(nominal);
        }
        return money;
    }

    private boolean isMultiple(int count) {
        boolean flag = false;
        for (Integer i : getNominal()) {
            flag = count / i > 0 && count % i == 0;
        }
        return flag;
    }

    @Override
    public List<Integer> getNominal() {
        List<Integer> nom = new ArrayList<>();
        for (BankCell cell : bankCells) {
            if (cell.getCurrency() == currency)
                nom.add(cell.getNominal());
        }
        return nom;
    }

    private void set(Integer... monies) {
        for (Integer nominal : monies) {
            this.setMoney(nominal, 1);
        }
    }

    private int calcBalance() {
        int balance = 0;
        for(BankCell cell : bankCells) {
            if (cell.getCurrency() == currency)
                balance += cell.balance() * cell.getNominal();
        }
        return balance;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public ATM copy() {
        return new ATMImpl(this);
    }

    @Override
    public ATM copy(ATM atm) {
        if (!bankCells.isEmpty())
            bankCells.clear();
        if (!currencies.isEmpty())
            currencies.clear();
        this.id = atm.getId();
        this.currency = atm.getCurrency();
        for (BankCell bankCell : atm.getBankCells()) {
            BankCell copyBankCell = new BankCellImpl(bankCell.getCurrency(), bankCell.getNominal());
            copyBankCell.set(bankCell.balance());
            this.addBankCell(copyBankCell);
        }
        return this;
    }
}
