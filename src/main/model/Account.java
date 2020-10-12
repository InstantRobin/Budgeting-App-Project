package model;
// Represents a place where money is stored

public class Account {

    // TODO: Add Currency
    // the balance of the account, in cents
    private int balance;

    public Account(int balance) {
        this.balance = balance; // initial balance
    }

    public void addValue(int value) {
        this.balance += value;
    }

    public void subValue(int value) {
        this.balance -= value;
    }

    public int getBalance() {
        return balance;
    }
}
