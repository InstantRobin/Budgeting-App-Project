package model;
// Represents a place where money is stored, also contains a history of all deposits/withdrawals

import java.time.LocalDate;
import java.util.List;

public class Account {

    // TODO: Add Currency

    // the balance of the account, in cents
    private int balance;
    private String name;
    private History history = new History();

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



    // Getters

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public History getHistory() {
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