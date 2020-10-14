package ui;
// Manages main actions of program

import model.Account;
import model.LogEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: Restructure Heavily
public class Manage {

    private List<Account> accounts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Manage() {
        runManage(); // from Teller
    }

    private void runManage() {
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        while (cont) {
            System.out.println("Welcome to Budgeteer!");
            doChoice(getChoice());


            System.out.println("Input true to continue, false to end");
            cont = sc.nextBoolean();
        }
    }

    // Gets User Action
    private int getChoice() {
        System.out.print("Enter the corresponding number of desired action:\n"
                + "1)View Account Balance\n"
                + "2)Deposit\n"
                + "3)Withdraw\n"
                + "4)Transfer\n"
                + "5)Make New Account\n"
        );

        // https://www.javatpoint.com/how-to-get-input-from-user-in-java
        return sc.nextInt();
    }

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
                transfer(getAcc(),getAcc(),getVal(),getDate());
                break;
                //TODO
            case 5:
                makeAccount(getName(),getVal());
                break;
        }
    }

    private void makeAccount(String name, int val) {
        accounts.add(new Account(name,val));
    }

    // Presents user with list of accounts, takes user input, returns selected account
    private Account getAcc() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the corresponding number of desired account");
        for (int a = 0; a < accounts.size(); a++) {
            System.out.println((a + 1) + ") " + accounts.get(a).getName());
        }
        int acc = sc.nextInt();
        return accounts.get(acc - 1);
    }

    private int getVal() {
        System.out.println("Enter desired amount:");

        return (int)(sc.nextFloat() * 100);

    }

    private String getDate() {
        System.out.println("Enter date of action:");
        System.out.println("Year:");
        String year = sc.next();
        System.out.println("Month:");
        String month = sc.next();
        System.out.println("Day:");
        String day = sc.next();

        return (year + " - " + month + " - " + day);
    }

    private String getName() {
        System.out.println("Enter name:");
        return sc.next();
    }

    private void viewBalance(Account acc) {
        System.out.println(acc.getStringBalance());
    }

    private void deposit(Account acc, int val, String date) {
        acc.addValue(val,date);
    }

    private void withdraw(Account acc, int val, String date) {
        acc.subValue(val,date);
    }

    private void transfer(Account acc1, Account acc2, int val, String date) {
        withdraw(acc1,val,date);
        deposit(acc2,val,date);
    }

    private void logAction(Account acc, int val, String date) {
        //stub
    }

    private void returnHistory() {
        //stub
    }
}
