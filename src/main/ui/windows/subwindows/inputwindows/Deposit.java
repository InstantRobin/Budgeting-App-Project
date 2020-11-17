package ui.windows.subwindows.inputwindows;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.NewAccountInput;
import ui.windows.subwindows.inputwindows.NewDateInput;
import ui.windows.subwindows.inputwindows.NewMoneyInput;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

// Represents the Deposit Window
public class Deposit extends SubWindow {

    public Deposit(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will deposit inputted amount into a given acct
    public void updateGUI() {
        NewMoneyInput moneyInput = new NewMoneyInput(container,home);
        moneyInput.updateGUI();

        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> getAccount(moneyInput.verifyVal(textArea.getText())));
    }

    private void getAccount(int val) {
        NewAccountInput accountInput = new NewAccountInput(container,home);
        accountInput.updateGUI();

        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton)container.getComponent(i);
            Account account = manager.getAccounts().get(i);
            button.addActionListener(e -> getDate(val,account));
        }
    }

    private void getDate(int val, Account acc) {
        NewDateInput dateInput = new NewDateInput(container,home);
        dateInput.updateGUI();

        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> makeDeposit(val,acc,dateInput.verifyDate(textArea.getText())));
    }

    // EFFECTS: Takes all user input so far, deposits val into account, logs account and date
    //          Displays a summary of the action to the user
    public void makeDeposit(int val, Account acc, LocalDate date) {
        MoveMoneyFunctions.deposit(acc,val, date);
        showMessageWindow("Deposited " + MoveMoneyFunctions.moneyToString(val, acc.getCurrency()) + " into "
                            + acc.getName() + " on " + date.toString());
    }
}
