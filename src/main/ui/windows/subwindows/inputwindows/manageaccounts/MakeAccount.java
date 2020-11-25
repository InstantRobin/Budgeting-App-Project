package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Account;
import model.Currency;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.InputWindow;

import javax.swing.*;
import java.awt.*;

// Represents a window where the user can create an account
public class MakeAccount extends InputWindow {

    private String name;
    private Currency currency;
    private int val;

    public MakeAccount(Container container, Home home) {
        super(home);
    }

    // MODIFIES: name
    // EFFECTS: Displays a GUI to get a name from the user
    @Override
    public void updateGUI() {
        getGenericInput("Name: ", () -> {
            name = textArea.getText();
            verifyName();
        });
    }

    // EFFECTS: Checks to make sure an account with the provided name does not already exist, throws error page if so
    public void verifyName() {
        for (Account acc: manager.getAccounts()) {
            if (name.equals(acc.getName())) {
                showMessageWindow("An account with this name already exists, please try again");
                return;
            }
        }
        getStartingVal();
    }

    // EFFECTS: Gets an amount of money from the user, turns it into cents
    // Not using getInt because it can be negative
    private void getStartingVal() {
        getGenericInput("Input Amount: ", () -> {
            try {
                this.val = (int)(Double.parseDouble(textArea.getText()) * 100);
                getCurrency();
            } catch (NumberFormatException exception) {
                showMessageWindow("Unrecognized number, please try again");
            }
        });
    }

    // MODIFIES: container
    // EFFECTS: Allows user to select an existing currency, or to create a new one
    private void getCurrency() {
        reset();
        loadCurrencyButtons();
        loadNewCurrencyButton();
    }

    // MODIFIES: container, currency
    // EFFECTS: Loads all existing currencies as buttons that can be selected by the user
    private void loadCurrencyButtons() {
        for (Currency currency : manager.getCurrencies()) {
            JButton accountButton = new JButton(currency.getName());
            container.add(accountButton);
            accountButton.addActionListener(e -> {
                this.currency = currency;
                createAccount();
            });
        }
    }

    // MODIFIES: container
    // EFFECTS: Loads a button to allow the user to create a new currency
    private void loadNewCurrencyButton() {
        JButton newAccButton = new JButton("New");
        container.add(newAccButton);
        newAccButton.addActionListener(e -> {
            MakeCurrency makeCurrency = new MakeCurrency(container,home,this::setCurrency);
            makeCurrency.updateGUI();
        });
    }

    // MODIFIES: currency, manager
    // EFFECTS: Sets the currency to the one given by the user
    private void setCurrency(Currency currency) {
        this.currency = currency;
        manager.addCurrency(currency);
        createAccount();
    }

    // MODIFIES: manager
    // EFFECTS: Creates an account using all the information the user has provided so far
    private void createAccount() {
        manager.getAccounts().add(new Account(name,val,currency));
        showMessageWindow("Created new account called " + name + " and has "
                + MoveMoneyFunctions.moneyToString(val,currency) + " stored in " + currency.getName());
    }

}
