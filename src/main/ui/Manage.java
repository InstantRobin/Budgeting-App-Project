package ui;
// Manages main actions of program

import model.Account;
import model.Currency;
import model.History;
import model.LogEntry;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manage {

    private List<Account> accounts;
    // currencies still TODO
//    private List<Currency> currencies;
    private Scanner sc = new Scanner(System.in);

    // from CPSC 210 EdX JsonSerializationDemo
    private static final String JSON_STORE = "./data/accounts.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Manage() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        accounts = new ArrayList<>();
//        currencies = new ArrayList<>();
//        currencies.add(new Currency("USD","$",1));

        runManage(); // from Teller
    }

    // EFFECT: The primary function, takes + follows user input until told to quit
    private void runManage() {
        int choice;
        System.out.println("Welcome to Budgeteer!");
        do {
            choice = getCategory();
        } while (doCategory(choice));
    }

    // EFFECT: Presents users with categories, gets User Selection
    private int getCategory() {
        List<String> options = new ArrayList<>();
        options.add("Move Money");
        options.add("Manage Accounts");
        options.add("Manage Saved Data");
        options.add("Exit");
        printOptions(options);

        // https://www.javatpoint.com/how-to-get-input-from-user-in-java
        return sc.nextInt();
    }

    // REQUIRES: 1 < choice 4
    // EFFECT: Calls corresponding category function as outlined in getCategory()
    private Boolean doCategory(int choice) {
        switch (choice) {
            case 1:
                doMoveMoney(getMoveMoney());
                break;
            case 2:
                doManageAccounts(getManageAccounts());
                break;
            case 3:
                doManageData(getManageData());
                break;
            case 4:
                return false;
            default :
                System.out.println("Command not recognized, please try again\n");
                break;
        }
        return true;
    }

    // EFFECT: Presents users with money movement options, gets User Selection
    private int getMoveMoney() {
        List<String> options = new ArrayList<>();
        options.add("Deposit");
        options.add("Withdraw");
        options.add("Transfer");
        options.add("Go Back");
        printOptions(options);
        return sc.nextInt();
    }

    // REQUIRES: 1 < choice 4
    // EFFECT: Calls corresponding function as outlined in getMoveMoney()
    private void doMoveMoney(int choice) {
        switch (choice) {
            case 1:
                deposit(getAccInput(), getValInput(), getDateInput());
                break;
            case 2:
                withdraw(getAccInput(), getValInput(), getDateInput());
                break;
            case 3:
                transferStep(); // transfer requires 2 acc's, so needs more complicated fn
                break;
            case 4:
                break;
            default:
                System.out.println("Command not recognized, please try again\n");
                doMoveMoney(getMoveMoney());
        }
    }

    // EFFECT: Presents users with account management options, gets User Selection
    private int getManageAccounts() {
        List<String> options = new ArrayList<>();
        options.add("View Account Balance");
        options.add("Get Account History");
        options.add("Make New Account");
        options.add("Go Back");
        printOptions(options);
        return sc.nextInt();
    }

    // REQUIRES: 1 < choice 4
    // EFFECT: Calls corresponding function as outlined in getManageAccounts()
    private void doManageAccounts(int choice) {
        switch (choice) {
            case 1:
                viewBalance(getAccInput());
                break;
            case 2:
                makeAccount(getNameInput(), getValInput(), getCurrencyInput());
                break;
            case 3:
                printHistory(getAccInput());
                break;
            case 4:
                break;
            default:
                System.out.println("Command not recognized, please try again\n");
                doManageAccounts(getManageAccounts());
        }
    }

    // EFFECT: Presents users with data management options, gets User Selection
    private int getManageData() {
        List<String> options = new ArrayList<>();
        options.add("Load");
        options.add("Save");
        options.add("Go Back");
        printOptions(options);
        return sc.nextInt();
    }

    // REQUIRES: 1 < choice 3
    // EFFECT: Calls corresponding function as outlined in getManageData()
    private void doManageData(int choice) {
        switch (choice) {
            case 1:
                load();
                break;
            case 2:
                save();
                break;
            case 3:
                break;
            default:
                System.out.println("Command not recognized, please try again\n");
                doManageAccounts(getManageAccounts());
        }
    }

    // REQUIRES: List.size() >= 1
    // EFFECT: Prints out list of given actions
    private void printOptions(List<String> options) {
        System.out.println("Enter the corresponding number of desired action:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ")" + options.get(i));
        }
    }

    //*******************//
    //GET USER INPUT FNS//
    //*****************//

    // EFFECT: Presents user with list of accounts, takes user input, returns selected account
    private Account getAccInput() {
        //TODO: Don't allow duplicate names
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the corresponding number of desired account");
        for (int a = 0; a < accounts.size(); a++) {
            System.out.println((a + 1) + ") " + accounts.get(a).getName());
        }

        int acc = sc.nextInt();
        return accounts.get(acc - 1);
    }

    // EFFECT: Gets int value from User
    private int getValInput() {
        System.out.println("Enter desired amount:");
        return (int)(sc.nextFloat() * 100);
    }

    // EFFECT: Gets LocalDate date from user, separate input for year, month, date
    private LocalDate getDateInput() {
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
    private String getNameInput() {
        System.out.println("Enter name:");
        return sc.next();
    }

    // EFFECT: Gets Currency from user
    private Currency getCurrencyInput() {
        // TODO: Implement Currency Array, default USD
        // TODO: Save Currency Array?
        System.out.println("Select Currency");
        List<Currency> currencies = new ArrayList<>();
        int item = 0;

        for (Account acc : accounts) {
            if (!currencies.contains(acc.getCurrency())) {
                currencies.add(acc.getCurrency());
                System.out.println((item + 1) + ") " + currencies.get(item).getName());
                item += 1;
            }
        }

        System.out.println(item + 1 + ") New Currency");

        int choice = sc.nextInt();
        if (choice == item + 1) {
            return newCurrency();
        } else {
            return currencies.get(choice);
        }

    }

    private Currency newCurrency() {
        System.out.println("Enter Currency Name:");
        String name = sc.next();
        System.out.println("Existing Currency not found, please enter information for this Currency");
        System.out.println("Enter Symbol:");
        String symbol = sc.next();
        System.out.println("Enter Exchange Rate to USD");
        int exRate = sc.nextInt();
        return (new Currency(name,symbol,exRate));
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
        transfer(getAccInput(), getAccInput(), getValInput(), getDateInput());
    }

    // REQUIRES: Val > 0
    // MODIFIES: acc1 acc2
    // EFFECT: Does actual transfer process
    private void transfer(Account acc1, Account acc2, int val, LocalDate date) {
        Currency cur1 = acc1.getCurrency();
        Currency cur2 = acc2.getCurrency();
        if (cur1 != cur2) {
            val = (int)(val * cur1.getExchangeRateUSD() / cur2.getExchangeRateUSD());
        }
        withdraw(acc1,val,date);
        deposit(acc2,val,date);
    }

    // REQUIRES name is at least 1 char long
    // MODIFIES: accounts
    // EFFECT: Makes new Account w/ given name, initial value
    private void makeAccount(String name, int val, Currency currency) {
        accounts.add(new Account(name,val, currency));
    }

    // from CPSC 210 EdX JsonSerializationDemo
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(accounts);
            jsonWriter.close();
            System.out.println("All Saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void load() {
        try {
            accounts = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read file: " + JSON_STORE);
        }
    }

    // EFFECT: Returns the history of actions on a given account
    private void printHistory(Account acc) {
        History hist = acc.getHistory();
        hist.updateTotals();

        System.out.println("     " + "Date        " + "Change  " + "| Total");
        //                 "     " + "yyyy-MM-dd: " + "$XXX.XX " + "| $XXX.XX"
        for (int i = 0; i < hist.size();i++) {
            LogEntry item = hist.get(i);
            System.out.println("     " + item.getStringDate() + ": "
                    + Account.moneyToString(item.getVal(),acc.getCurrency()) + " | "
                    + Account.moneyToString(item.getTotal(),acc.getCurrency()));
        }
    }
}
