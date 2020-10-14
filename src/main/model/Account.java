package model;
// Represents a place where money is stored, also contains a history of all deposits/withdrawals

import java.util.ArrayList;
import java.util.List;

public class Account {

    // TODO: Add Currency

    // the balance of the account, in cents
    private int balance;
    private String name;
    private List<LogEntry> history = new ArrayList<LogEntry>();

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance; // initial balance
    }

    public void logEvent(int value, boolean add, String date) {
        history.add(new LogEntry(this, value, add, date));
    }

    public void addValue(int value, String date) {
        this.balance += value;
        history.add(new LogEntry(this, value,true, date));
    }

    public void subValue(int value, String date) {
        this.balance -= value;
        history.add(new LogEntry(this, value,false, date));
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
            return ("$" + before + "." + after + "0");
        } else {
            return ("$" + before + "." + after);
        }
    }
}
