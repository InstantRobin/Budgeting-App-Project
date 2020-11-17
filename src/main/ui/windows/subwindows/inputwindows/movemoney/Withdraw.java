package ui.windows.subwindows.inputwindows.movemoney;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.AccountInput;
import ui.windows.subwindows.inputwindows.DateInput;
import ui.windows.subwindows.inputwindows.MoneyInput;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

// Represents the Withdraw Window
public class Withdraw extends SubWindow {

    public Withdraw(Container container, Home home) {
        super(container, home);
    }

    // TODO: Reduce Duplication btw Deposit and Withdraw (lambda or override)

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will deposit inputted amount into a given acct
    @Override
    public void updateGUI() {
        MoneyInput moneyInput = new MoneyInput(container,home);
        moneyInput.updateGUI();

        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> getAccount(moneyInput.verifyVal(textArea.getText())));
    }

    private void getAccount(int val) {
        AccountInput accountInput = new AccountInput(container,home);
        accountInput.updateGUI();

        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton)container.getComponent(i);
            Account account = manager.getAccounts().get(i);
            button.addActionListener(e -> getDate(val,account));
        }
    }

    private void getDate(int val, Account acc) {
        DateInput dateInput = new DateInput(container,home);
        dateInput.updateGUI();

        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> makeWithdrawal(val,acc,dateInput.verifyDate(textArea.getText())));
    }

    // EFFECTS: Takes all user input so far, withdraws val from account, logs account and date
    //          Displays a summary of the action to the user
    public void makeWithdrawal(int val, Account acc, LocalDate date) {
        MoveMoneyFunctions.withdraw(acc,val, date);
        showMessageWindow("Withdrew " + MoveMoneyFunctions.moneyToString(val, acc.getCurrency()) + " from "
                + acc.getName() + " on " + date.toString());
    }
}
