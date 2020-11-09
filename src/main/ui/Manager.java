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
import static model.MoveMoney.*;

public class Manager {

    private List<Account> accounts;
    private List<Currency> currencies;
    private Scanner sc = new Scanner(System.in);

    // from CPSC 210 EdX JsonSerializationDemo
    private static final String JSON_STORE = "./data/accounts.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Manager() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        accounts = new ArrayList<>();
        currencies = new ArrayList<>();
        currencies.add(new Currency("USD","$",1));

        //runManage(); // from Teller
    }

    // EFFECT: The primary function, takes + follows user input until told to quit
    private void runManage() {
        CategoryChoices choice;
        System.out.println("Welcome to Budgeteer!");
        do {
            choice = getCategory();
        } while (doCategory(choice));
    }

    //***********//
    //CATEGORIES//
    //*********//

    public enum CategoryChoices {
       MOVE, MANACCTS, MANSAVED, EXIT
    }

    // EFFECT: Presents users with categories, gets User Selection
    private CategoryChoices getCategory() {
        List<String> options = new ArrayList<>();
        options.add("Move Money");
        options.add("Manage Accounts");
        options.add("Manage Saved Data");
        options.add("Exit");
        printOptions(options);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                return CategoryChoices.MOVE;
            case 2:
                return CategoryChoices.MANACCTS;
            case 3:
                return CategoryChoices.MANSAVED;
            case 4:
                return CategoryChoices.EXIT;
            default:
                System.out.println("Unrecognized Choice, please try again");
                return getCategory();
        }
    }

    // REQUIRES: 1 < choice 4
    // EFFECT: Calls corresponding category function as outlined in getCategory()
    public Boolean doCategory(CategoryChoices choice) {
        switch (choice) {
            case MOVE:
                doMoveMoney(getMoveMoney());
                break;
            case MANACCTS:
                doManageAccounts(getManageAccounts());
                break;
            case MANSAVED:
                doManageData(getManageData());
                break;
            case EXIT:
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

    public enum ManageMoneyChoices {
        DEP, WITH, TRANS, EXIT
    }

    // EFFECT: Presents users with money movement options, gets User Selection
    private ManageMoneyChoices getMoveMoney() {
        List<String> options = new ArrayList<>();
        options.add("Deposit");
        options.add("Withdraw");
        options.add("Transfer");
        options.add("Go Back");
        printOptions(options);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                return ManageMoneyChoices.DEP;
            case 2:
                return ManageMoneyChoices.WITH;
            case 3:
                return ManageMoneyChoices.TRANS;
            case 4:
                return ManageMoneyChoices.EXIT;
            default:
                System.out.println("Unrecognized Choice, please try again");
                return getMoveMoney();
        }
    }

    // REQUIRES: 1 < choice 4
    // EFFECT: Calls corresponding function as outlined in getMoveMoney()
    public void doMoveMoney(ManageMoneyChoices choice) {
        switch (choice) {
            case DEP:
                deposit(getAccInput(accounts), getValInput(), getDateInput());
                break;
            case WITH:
                withdraw(getAccInput(accounts), getValInput(), getDateInput());
                break;
            case TRANS:
                transferStep(); // transfer requires 2 acc's, so needs more complicated fn
                break;
            case EXIT:
                break;
            default:
                System.out.println("Command not recognized, please try again\n");
                doMoveMoney(getMoveMoney());
        }
    }

    //****************//
    //MANAGE ACCOUNTS//
    //**************//

    public enum ManageAcctsChoices {
        VIEW, GET, MAKE, EXIT
    }

    // EFFECT: Presents users with account management options, gets User Selection
    private ManageAcctsChoices getManageAccounts() {
        List<String> options = new ArrayList<>();
        options.add("View Account Balance");
        options.add("Get Account History");
        options.add("Make New Account");
        options.add("Go Back");
        printOptions(options);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                return ManageAcctsChoices.VIEW;
            case 2:
                return ManageAcctsChoices.GET;
            case 3:
                return ManageAcctsChoices.MAKE;
            case 4:
                return ManageAcctsChoices.EXIT;
            default:
                System.out.println("Unrecognized Choice, please try again");
                return getManageAccounts();
        }
    }


    // REQUIRES: 1 < choice 4
    // EFFECT: Calls corresponding function as outlined in getManageAccounts()
    public void doManageAccounts(ManageAcctsChoices choice) {
        switch (choice) {
            case VIEW:
                viewBalance(getAccInput(accounts));
                break;
            case GET:
                printHistory(getAccInput(accounts));
                break;
            case MAKE:
                makeAccount(getNameInput(), getValInput(), getCurrencyInput(currencies));
                break;
            case EXIT:
                break;
            default:
                System.out.println("Command not recognized, please try again\n");
                doManageAccounts(getManageAccounts());
        }
    }

    //************//
    //MANAGE DATA//
    //**********//

    public enum ManageDataChoices {
        SAVE, LOAD, EXIT
    }

    // EFFECT: Presents users with data management options, gets User Selection
    private ManageDataChoices getManageData() {
        List<String> options = new ArrayList<>();
        options.add("Load");
        options.add("Save");
        options.add("Go Back");
        printOptions(options);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                return ManageDataChoices.SAVE;
            case 2:
                return ManageDataChoices.LOAD;
            case 3:
                return ManageDataChoices.EXIT;
            default:
                System.out.println("Unrecognized Choice, please try again");
                return getManageData();
        }
    }

    // REQUIRES: 1 < choice 3
    // EFFECT: Calls corresponding function as outlined in getManageData()
    public void doManageData(ManageDataChoices choice) {
        switch (choice) {
            case LOAD:
                load();
                break;
            case SAVE:
                save();
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

    // EFFECT: Prints given Account balance as a formatted String
    private static void viewBalance(Account acc) {
        System.out.println(acc.getStringBalance());
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


    // REQUIRES name is at least 1 char long
    // MODIFIES: accounts
    // EFFECT: Makes new Account w/ given name, initial value
    private void makeAccount(String name, int val, Currency currency) {
        accounts.add(new Account(name,val, currency));
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

    public List<Account> getAccounts() {
        return accounts;
    }
}
