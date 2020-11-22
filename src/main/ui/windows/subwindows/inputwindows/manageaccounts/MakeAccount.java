package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Account;
import model.Currency;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.GetterWindow;

import javax.swing.*;
import java.awt.*;

public class MakeAccount extends GetterWindow {

    private String name;
    private Currency currency;
    private int val;

    private String curName;
    private String curSymbol;
    private double curExchange;

    public MakeAccount(Container container, Home home) {
        super(container, home);
    }

    @Override
    public void updateGUI() {
        getString("Name: ", () -> {
            this.name = textArea.getText();
            getStartingVal();
        });
    }

    // Not using getInt because it can be negative
    private void getStartingVal() {
        getString("Input Amount: ", () -> {
            try {
                this.val = (int)(Double.parseDouble(textArea.getText()) * 100);
                getCurrency();
            } catch (NumberFormatException exception) {
                showMessageWindow("Unrecognized number, please try again");
            }
        });
    }

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
        newAccButton.addActionListener(e -> createCurrency());
    }

    private void createAccount() {
        manager.getAccounts().add(new Account(name,val,currency));
        showMessageWindow("Created new account called " + name + " and has "
                + MoveMoneyFunctions.moneyToString(val,currency) + " stored in " + currency.getName());
    }


    // Currency Related

    private void createCurrency() {
        getString("New Currency Name:", () -> {
            curName = textArea.getText();
            getSymbol();
        });
    }

    private void getSymbol() {
        getString("Symbol:", () -> {
            curSymbol = textArea.getText();
            getExchange();
        });
    }

    private void getExchange() {
        getString("Exchange Rate into USD:", () -> {
            curExchange = Double.parseDouble(textArea.getText());
            if (curExchange <= 0) {
                showMessageWindow("Error: Value must be positive");
                return;
            }
            currency = new Currency(curName,curSymbol,curExchange);
            manager.addCurrency(currency);
            createAccount();
        });
    }

}
