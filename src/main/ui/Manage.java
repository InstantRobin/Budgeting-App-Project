package ui;
// Manages main actions of program

import model.Account;
import model.Currency;
import model.History;
import model.LogEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manage {

    private List<Account> accounts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public static Currency USD = new Currency("USD","$",1);

    public Manage() {
        runManage(); // from Teller
    }

    // EFFECT: The primary function, takes + follows user input until told to quit
    private void runManage() {
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Budgeteer!");
        while (cont) {
            doChoice(getChoice());

            System.out.println("Input true to continue, false to end");
            cont = sc.nextBoolean();
        }
    }

    // EFFECT: Presents users with options, gets User Action
    private int getChoice() {
        System.out.print("Enter the corresponding number of desired action:\n"
                + "1)View Account Balance\n"
                + "2)Deposit\n"
                + "3)Withdraw\n"
                + "4)Transfer\n"
                + "5)Make New Account\n"
                + "6)Get Account History\n");

        // https://www.javatpoint.com/how-to-get-input-from-user-in-java
        return sc.nextInt();
    }

    // REQUIRES: 1 < choice 6
    // EFFECT: Calls corresponding function to user choice as documented in getChoice()
    private void doChoice(int choice) {
        switch (choice) {
            case 1:
                viewBalance(getAcc());
                break;
            case 2:
                deposit(getAcc(),getVal(),getDate());
                break;
            case 3:
                withdraw(getAcc(),getVal(),getDate());
                break;
            case 4:
                transferStep(); // transfer requires 2 acc's, so needs more complicated fn
                break;
            case 5:
                makeAccount(getName(),getVal());
                break;
            case 6:
                printHistory(getAcc());
                break;
        }
    }

    //*******************//
    //GET USER INPUT FNS//
    //*****************//

    // EFFECT: Presents user with list of accounts, takes user input, returns selected account
    private Account getAcc() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the corresponding number of desired account");
        for (int a = 0; a < accounts.size(); a++) {
            System.out.println((a + 1) + ") " + accounts.get(a).getName());
        }

        int acc = sc.nextInt();
        return accounts.get(acc - 1);
    }

    // EFFECT: Gets int value from User
    private int getVal() {
        System.out.println("Enter desired amount:");
        return (int)(sc.nextFloat() * 100);
    }

    // EFFECT: Gets LocalDate date from user, separate input for year, month, date
    private LocalDate getDate() {
        System.out.println("Enter date of action in number form:");
        System.out.println("Year:");
        int year = sc.nextInt();
        System.out.println("Month:");
        int month = sc.nextInt();
        System.out.println("Day:");
        int day = sc.nextInt();

        return LocalDate.of(year,month,day);
    }

    // EFFECT: Gets string name from user
    private String getName() {
        System.out.println("Enter name:");
        return sc.next();
    }

    //******************//
    //CHOICE-ACTION FNS//
    //****************//

    // EFFECT: Prints given Account balance as a formatted String
    private void viewBalance(Account acc) {
        System.out.println(acc.getStringBalance());
    }

    // REQUIRES: Val > 0
    // MODIFIES: acc
    // EFFECT: Deposits given val in given account
    private void deposit(Account acc, int val, LocalDate date) {
        acc.addValue(val,date);
    }

    // REQUIRES: Val > 0
    // MODIFIES: acc
    // EFFECT: Withdraws given val from given account
    private void withdraw(Account acc, int val, LocalDate date) {
        acc.subValue(val,date);
    }

    // REQUIRES: Val > 0
    // EFFECT: Intermediate step required bc fn takes two accounts, extra command needed to reduce vagueness
    private void transferStep() {
        System.out.println("First select source, then select destination");
        transfer(getAcc(),getAcc(),getVal(),getDate());
    }

    // REQUIRES: Val > 0
    // MODIFIES: acc1 acc2
    // EFFECT: Does actual transfer process
    private void transfer(Account acc1, Account acc2, int val, LocalDate date) {
        withdraw(acc1,val,date);
        deposit(acc2,val,date);
    }

    // REQUIRES name is at least 1 char long
    // MODIFIES: accounts
    // EFFECT: Makes new Account w/ given name, initial value
    private void makeAccount(String name, int val) {
        accounts.add(new Account(name,val));
    }

    // EFFECT: Returns the history of actions on a given account
    private void printHistory(Account acc) {
        History hist = acc.getHistory();
        hist.updateTotals();

        System.out.println("     " + "Date        " + "Change  " + "| Total");
        //                 "     " + "yyyy-MM-dd: " + "$XXX.XX " + "| $XXX.XX"
        for (LogEntry item : hist) {
            System.out.println("     " + item.getStringDate() + ": "
                    + Account.moneyToString(item.getVal()) + " | " + Account.moneyToString(item.getTotal()));
        }
    }
}
