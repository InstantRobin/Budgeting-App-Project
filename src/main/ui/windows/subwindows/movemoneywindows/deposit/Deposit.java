package ui.windows.subwindows.movemoneywindows.deposit;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.MoveMoneyWindow;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

// Represents the Deposit Window
public class Deposit extends MoveMoneyWindow {

    public Deposit(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will deposit inputted amount into a given acct
    public void updateGUI() {
        ArrayList<Accessible> components = getInputWindow();
        JTextArea enteredText = (JTextArea) components.get(0);
        JButton enterButton = (JButton) components.get(1);

        enterButton.addActionListener(e -> verifyValThenContinue(enteredText.getText()));
    }

    // EFFECTS: Ensures user input is a double,
    //              if is, turns input into an integer of cents, passes it into getAccount
    //              if not, throws error screen, returns user to home
    private void verifyValThenContinue(String str) {
        try {
            double val = Double.parseDouble(str);
            getAccount((int)(val * 100));
        } catch (NumberFormatException e) {
            showMessageWindow("Unrecognized number, please try again");
        }
    }

    // EFFECTS: Displays all existing accounts to the user as buttons, passes selected account into getDate
    private void getAccount(int val) {
        super.getAccountWindow();
        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton) container.getComponent(i);
            Account acc = manager.getAccounts().get(i);
            button.addActionListener(e -> getDate(val, acc));
        }
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a date
    private void getDate(int val, Account acc) {
        ArrayList<Accessible> components = getDateWindow();
        JTextArea enteredText = (JTextArea) components.get(0);
        JButton enterButton = (JButton) components.get(1);

        enterButton.addActionListener(e -> verifyDateThenContinue(val, acc, enteredText.getText()));
    }

    // EFFECTS: Ensures user input is a properly formatted date,
    //              if is, passes it and all past inputs into makeDeposit
    //              if not, throws error screen, returns user to home
    private void verifyDateThenContinue(int val, Account acc, String str) {
        try {
            LocalDate date = LocalDate.parse(str);
            makeDeposit(val,acc,date);
        } catch (Exception e) {
            showMessageWindow("Unrecognized Date, please try again");
        }
    }

    // EFFECTS: Takes all user input so far, deposits val into account, logs account and date
    //          Displays a summary of the action to the user
    public void makeDeposit(int val, Account acc, LocalDate date) {
        MoveMoneyFunctions.deposit(acc,val, date);
        showMessageWindow("Deposited " + MoveMoneyFunctions.moneyToString(val, acc.getCurrency()) + " into "
                            + acc.getName() + " on " + date.toString());
    }
}
