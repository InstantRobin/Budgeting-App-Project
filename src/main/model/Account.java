package model;
// Represents a place where money is stored

public class Account {

    // TODO: Add Currency
    // the balance of the account, in cents
    private int balance;
    private String name;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance; // initial balance
    }

    public void addValue(int value) {
        this.balance += value;
    }

    public void subValue(int value) {
        this.balance -= value;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }
}
