package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Account;
import model.Currency;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.GetterWindow;

import javax.swing.*;
import java.awt.*;

// Represents a window where the user can create an account
public class MakeAccount extends GetterWindow {

    private String name;
    private Currency currency;
    private int val;

    public MakeAccount(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Displays a GUI to get a name from the user
    @Override
    public void updateGUI() {
        getGenericInput("Name: ", () -> {
            this.name = textArea.getText();
            getStartingVal();
        });
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

    // EFFECTS: Allows user to select an existing currency, or to create a new one
    private void getCurrency() {
        reset();
        for (Currency currency : manager.getCurrencies()) {
            JButton accountButton = new JButton(currency.getName());
            container.add(accountButton);
            accountButton.addActionListener(e -> {
                this.currency = currency;
                createAccount();
            });
        }
        JButton newAccButton = new JButton("New");
        container.add(newAccButton);
        newAccButton.addActionListener(e -> {
            MakeCurrency makeCurrency = new MakeCurrency(container,home,this::setCurrency);
            makeCurrency.updateGUI();
        });
    }

    // EFFECTS: Sets the currency to the one given by the user
    private void setCurrency(Currency currency) {
        this.currency = currency;
        manager.addCurrency(currency);
        createAccount();
    }

    // EFFECTS: Creates an account using all the information the user has provided so far
    private void createAccount() {
        manager.getAccounts().add(new Account(name,val,currency));
        showMessageWindow("Created new account called " + name + " and has "
                + MoveMoneyFunctions.moneyToString(val,currency) + " stored in " + currency.getName());
    }

}
