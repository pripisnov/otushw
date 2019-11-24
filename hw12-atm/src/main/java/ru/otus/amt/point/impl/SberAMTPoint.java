package ru.otus.amt.point.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.amt.AMT;
import ru.otus.amt.point.AMTPoint;
import ru.otus.money.Money;
import ru.otus.money.impl.MoneyImpl;
import ru.otus.money.impl.RubleMoneyType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class SberAMTPoint implements AMTPoint {
    private final AMT amt;

    @Override
    public List<Money> get(int count) {
        if (balance() >= count) {
            return getMinimize(count);
        }
        else
            throw new IllegalArgumentException("no money");
    }

    @Override
    public void set(List<Money> monies) {
        setMonies(monies);
    }

    @Override
    public void set(Money money) {
        amt.setMoney(money);
    }

    @Override
    public int balance() {
        return amt.getBalance();
    }

    private List<Money> getMinimize(int count) {
        List<Money> monies = new ArrayList<>();
        int moneyCount = count;
        int[] nominals = amt.getNominal();
        Arrays.sort(nominals);

        for (int i = nominals.length - 1; i >= 0; --i) {
            int nominal = nominals[i];
            int n = moneyCount / nominal;
            int cellBalance = amt.getBankCellBalance(nominal);
            if (cellBalance < n)
                n = cellBalance;
            Money money = getMoney(nominal, n);
            monies.add(money);
            moneyCount -= n * nominal;
        }
        return monies;
    }

    private void setMonies(List<Money> monies) {
        for (Money money : monies) {
            amt.setMoney(money);
        }
    }

    private Money getMoney(int nominal, int count) {
        return amt.getMoney(new MoneyImpl(new RubleMoneyType(nominal), count));
    }
}
