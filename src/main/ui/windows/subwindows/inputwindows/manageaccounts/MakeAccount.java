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

    private String curName;
    private String curSymbol;
    private double curExchange;

    public MakeAccount(Container container, Home home) {
        super(container, home);
    }

    @Override
    public void updateGUI() {
        getGenericInput("Name: ");
        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> {
            this.name = textArea.getText();
            getStartingVal();
        });
    }


    private void getStartingVal() {
        getInt(this::getCurrency);
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

    private void createCurrency() {
        getGenericInput("New Currency Name:");
        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> {
            curName = textArea.getText();
            getSymbol();
        });
    }

    private void getSymbol() {
        getGenericInput("Symbol:");
        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> {
            curSymbol = textArea.getText();
            getExchange();
        });
    }

    private void getExchange() {
        getGenericInput("Exchange Rate into USD:");
        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> {
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
