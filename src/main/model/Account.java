package model;
// Represents a place where money is stored, also contains a history of all deposits/withdrawals

import java.time.LocalDate;
import java.util.ArrayList;
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
        history.add(new LogEntry(this, value, date));
    }

    public void addValue(int value, LocalDate date) {
        this.balance += value;
        history.add(new LogEntry(this, value, date));
    }

    public void subValue(int value, LocalDate date) {
        this.balance -= value;
        history.add(new LogEntry(this, -1 * value, date));
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
        int before = ((balance - (balance % 100)) / 100);
        int after = balance % 100;

        if (after < 10) {
            return ("$" + before + "." + "0" + after);
        } else {
            return ("$" + before + "." + after);
        }
    }
}
