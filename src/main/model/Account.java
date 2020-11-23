package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

// Represents a place where money is stored, also contains a history of all deposits/withdrawals
public class Account implements Writable {

    private final String name;
    // the balance of the account, in cents
    private int balance;
    private final Currency currency;
    private final History history = new History();


    // EFFECT: Initializes account with name, initial balance
    // Modifies: this
    // Balance can be negative
    public Account(String name, int balance, Currency currency) {
        this.name = name;
        this.balance = balance;
        this.currency = currency;
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

    public Currency getCurrency() {
        return currency;
    }

    // EFFECT: Returns Balance in $X.XX string form
    public String getStringBalance() {
        return MoveMoneyFunctions.moneyToString(balance, currency);
    }

    //// SOURCE: ////
    // Save / Load System file structure based on example system JsonSerializationDemo provided by UBC CPSC 210 course
    // Adapted from the example fn given in the source
    // EFFECT: Converts Account into Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("balance",balance);
        json.put("currency",currency.toJson());
        json.put("history",history.toJson());
        return json;
    }

}