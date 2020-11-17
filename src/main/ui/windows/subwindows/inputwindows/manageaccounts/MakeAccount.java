package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Account;
import model.Currency;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountInput;
import ui.windows.subwindows.inputwindows.InputWindow;
import ui.windows.subwindows.inputwindows.MoneyInput;

import javax.swing.*;
import java.awt.*;

public class MakeAccount extends InputWindow {

    private String name;
    private int val;
    private Currency currency;

    private String curName;
    private String curSymbol;
    private double exchange;

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
        MoneyInput moneyInput = new MoneyInput(container,home);
        moneyInput.updateGUI();

        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> {
            this.val = moneyInput.verifyVal(textArea.getText());
            getCurrency();
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
    }

    private void createAccount() {
        manager.getAccounts().add(new Account(name,val,currency));
        showMessageWindow("Created new account called " + name + " and has "
                + MoveMoneyFunctions.moneyToString(val,currency) + " stored in " + currency.getName());
    }

    private void createCurrency() {
        getGenericInput("New Currency Name: ");
        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> {
            this.name = textArea.getText();
            //getSymbol();
        });
    }

}
