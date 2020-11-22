package model;

import java.time.LocalDate;
import java.util.ArrayList;

// Static functions involving the movement of money into, out of, and between accounts
public class MoveMoneyFunctions {
    // REQUIRES: Val > 0
    // MODIFIES: acc
    // EFFECT: Deposits given val in given account
    public static void deposit(Account acc, int val, LocalDate date) {
        acc.addValue(val,date);
    }

    // REQUIRES: Val > 0
    // MODIFIES: acc
    // EFFECT: Withdraws given val from given account
    public static void withdraw(Account acc, int val, LocalDate date) {
        acc.subValue(val,date);
    }

    // REQUIRES: Val > 0
    // MODIFIES: acc1 acc2
    // EFFECT: Does actual transfer process
    //         Done in terms of base account currency
    public static void transfer(Account acc1, Account acc2, int val, LocalDate date) {
        Currency cur1 = acc1.getCurrency();
        Currency cur2 = acc2.getCurrency();
        withdraw(acc1,val,date);

        if (cur1 != cur2) {
            val = (int)(val * cur1.getExchangeRateUSD() / cur2.getExchangeRateUSD());
        }
        deposit(acc2,val,date);
    }

    // EFFECT: Turns int of currency (in cents) into $X.XX string form
    public static String moneyToString(int money, Currency cur) {
        int before = ((money - (money % 100)) / 100);
        int after = money % 100;

        if (after >= 0 && after < 10) {
            return (cur.getSymbol() + before + "." + "0" + after);
        } else {
            return (cur.getSymbol() + before + "." + Math.abs(after));
        }
    }

    // Takes an account, builds a day-by-day list of events in the account
    public static History buildData(Account acc) {
        History data = new History();
        History hist = acc.getHistory();
        hist.updateTotals();

        for (int i = 0; i < hist.size() - 1; i++) {
            LogEntry item = hist.get(i);
            LocalDate start = item.getDate();
            data.add(item);
            for (LocalDate date = start.plusDays(1); date.isBefore(hist.get(i + 1).getDate());
                    date = date.plusDays(1)) {
                data.add(new LogEntry(acc,0,item.getTotal(),date));
            }
        }

        data.add(hist.get(hist.size() - 1));
        return data;
    }
}
