package ui;
// Manages main actions of program

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ui.GetInput.*;
import static model.Actions.*;

public class Manage {

    private List<Account> accounts;
    // currencies still TODO
    private List<Currency> currencies;
    private Scanner sc = new Scanner(System.in);

    // from CPSC 210 EdX JsonSerializationDemo
    private static final String JSON_STORE = "./data/accounts.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Manage() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        accounts = new ArrayList<>();
        currencies = new ArrayList<>();
        currencies.add(new Currency("USD","$",1));

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

    //***********//
    //CATEGORIES//
    //*********//

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

    //***********//
    //MOVE MONEY//
    //*********//

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
                deposit(getAccInput(accounts), getValInput(), getDateInput());
                break;
            case 2:
                withdraw(getAccInput(accounts), getValInput(), getDateInput());
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

    //****************//
    //MANAGE ACCOUNTS//
    //**************//

    // REQUIRES: 1 < choice 4
    // EFFECT: Calls corresponding function as outlined in getManageAccounts()
    private void doManageAccounts(int choice) {
        switch (choice) {
            case 1:
                viewBalance(getAccInput(accounts));
                break;
            case 2:
                printHistory(getAccInput(accounts));
                break;
            case 3:
                makeAccount(accounts, getNameInput(), getValInput(), getCurrencyInput(currencies));
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

    //************//
    //MANAGE DATA//
    //**********//

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


    //******************//
    //CHOICE-ACTION FNS//
    //****************//

    // REQUIRES: Val > 0
    // EFFECT: Intermediate step required bc fn takes two accounts, extra command needed to reduce vagueness
    private void transferStep() {
        System.out.println("First select source, then select destination");
        transfer(getAccInput(accounts), getAccInput(accounts), getValInput(), getDateInput());
    }


    // MODIFIES: Accounts, Currencies
    // EFFECTS: Loads Accounts from json file, creates Currencies from loaded Accounts
    private void load() {
        try {
            accounts = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read file: " + JSON_STORE);
        }
        getCurrencies();
        setCurrencies();
    }

    // MODIFIES: Currencies
    // EFFECTS: Builds list of currencies from Accounts
    private void getCurrencies() {
        for (Account acc : accounts) {
            if (!currencies.contains(acc.getCurrency())) {
                currencies.add(acc.getCurrency());
            }
        }
    }

    // MODIFIES: Accounts
    // EFFECTS: Makes sure account w/ same currency all refer to the same object
    private void setCurrencies() {
        for (Account acc : accounts) {
            for (Currency currency : currencies) {
                if (currency == acc.getCurrency()) {
                    acc = new Account(acc.getName(),acc.getBalance(),currency);
                }
            }
        }
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


}
