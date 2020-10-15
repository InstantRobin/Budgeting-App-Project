package model;
// Represents a place where money is stored, also contains a history of all deposits/withdrawals

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    // TODO: Add Currency

    // the balance of the account, in cents
    private int balance;
    private String name;
    private List<LogEntry> history = new ArrayList<>();

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance; // initial balance
    }

    public void logEvent(int value, LocalDate date) {
        history.add(new LogEntry(this, value, this.balance, date));
    }

    public void addValue(int value, LocalDate date) {
        this.balance += value;
        logEvent(value, date);
    }

    public void subValue(int value, LocalDate date) {
        this.balance -= value;
        logEvent(-1 * value, date);
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public List<LogEntry> getHistory() {
        return history;
    }

    // Returns Balance in $X.XX string form
    public String getStringBalance() {
        return moneyToString(balance);
    }

    public static String moneyToString(int money) {
        int before = ((money - (money % 100)) / 100);
        int after = money % 100;

        if (after < 10) {
            return ("$" + before + "." + "0" + after);
        } else {
            return ("$" + before + "." + after);
        }
    }

    // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date/33790426
    public void sortHistory() {
        Collections.sort(history);
    }

    public void updateHistoryTotals() {
        sortHistory();
        for (int loc = 0; loc < history.size(); loc++) {
            LogEntry item = history.get(loc);
            if (loc == 0) {
                item.updateTotal(item.getVal());
            } else {
                item.updateTotal(history.get(loc - 1).getTotal() + item.getVal());
            }
        }
    }
}
