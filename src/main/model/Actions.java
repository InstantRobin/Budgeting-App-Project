package model;

import java.time.LocalDate;
import java.util.List;

public class Actions {
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

    // EFFECT: Prints given Account balance as a formatted String
    public static void viewBalance(Account acc) {
        System.out.println(acc.getStringBalance());
    }

    // REQUIRES name is at least 1 char long
    // MODIFIES: accounts
    // EFFECT: Makes new Account w/ given name, initial value
    public static void makeAccount(List<Account> accounts, String name, int val, Currency currency) {
        accounts.add(new Account(name,val, currency));
    }


    // EFFECT: Returns the history of actions on a given account
    public static void printHistory(Account acc) {
        History hist = acc.getHistory();
        hist.updateTotals();

        System.out.println("     " + "Date        " + "Change  " + "| Total");
        //                 "     " + "yyyy-MM-dd: " + "$XXX.XX " + "| $XXX.XX"
        for (int i = 0; i < hist.size();i++) {
            LogEntry item = hist.get(i);
            System.out.println("     " + item.getStringDate() + ": "
                    + Account.moneyToString(item.getVal(),acc.getCurrency()) + " | "
                    + Account.moneyToString(item.getTotal(),acc.getCurrency()));
        }
    }
}
