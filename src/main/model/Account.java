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

    // EFFECT: Initializes account with name, initial balance
    // Modifies: this
    // Balance can be negative
    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    // EFFECT: Logs date, amount changed, current total account value, and the date of the action in a LogEntry
    public void logEvent(int value, LocalDate date) {
        history.add(new LogEntry(this, value, this.balance, date));
    }

    // REQUIRES: Value > 0
    // MODIFIES: This
    // EFFECT: Increases account balance by given value, logs value and date
    public void addValue(int value, LocalDate date) {
        this.balance += value;
        logEvent(value, date);
    }

    // REQUIRES: Value > 0
    // MODIFIES: This
    // EFFECT: Decreases account balance by given value, logs value (as negative) and date
    public void subValue(int value, LocalDate date) {
        this.balance -= value;
        logEvent(-1 * value, date);
    }

    // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date/33790426
    // EFFECT: Sorts the History List, because items can be added non-chronologically
    public void sortHistory() {
        Collections.sort(history);
    }

    // EFFECT: Sorts history, then goes through chronologically to update the total values to be accurate
    public void updateHistoryTotals() {
        sortHistory();
        for (int loc = 0; loc < history.size(); loc++) {
            LogEntry item = history.get(loc);
            if (loc == 0) {
                item.setTotal(item.getVal());
            } else {
                item.setTotal(history.get(loc - 1).getTotal() + item.getVal());
            }
        }
    }

    // Getters

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public List<LogEntry> getHistory() {
        return history;
    }

    // EFFECT: Returns Balance in $X.XX string form
    public String getStringBalance() {
        return moneyToString(balance);
    }


    // EFFECT: Turns int of currency (in cents) into $X.XX string form
    public static String moneyToString(int money) {
        int before = ((money - (money % 100)) / 100);
        int after = money % 100;

        if (after >= 0 && after < 10) {
            return ("$" + before + "." + "0" + after);
        } else {
            return ("$" + before + "." + Math.abs(after));
        }
    }
}