package ui.windows;

import model.Account;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Represents a window where the user can choose between the money movement functions
public abstract class MoveMoneyWindow extends SubWindow {

    protected final Home home;

    public MoveMoneyWindow(Container container, Home home) {
        super(container,home);
        this.home = home;
    }

    // EFFECTS: Creates a window where the user can input an amount of money
    protected ArrayList<Accessible> getInputWindow() {
        return getGenericInput("Input Amount: ");
    }

    // EFFECTS: Creates a window where all accounts are displayed as buttons
    protected void getAccountWindow() { // should return Account
        reset();
        List<Account> accounts = manager.getAccounts();
        for (Account account : accounts) {
            JButton accountButton = new JButton(account.getName());
            container.add(accountButton);
        }
    }

    // EFFECTS: Creates a window where the user can input a date in YYYY-MM-DD format
    protected ArrayList<Accessible> getDateWindow() {
        return getGenericInput("Enter Date As YYYY-MM-DD:");
    }

    // EFFECTS: Creates a window like so:
    /*
            str |     | Submit
     */
    //          The user can type in the white box, and Submit is a button to do so
    //          Returns the box and the submit button so that the function that eventListeners can be added by
    //              the function that calls this
    private ArrayList<Accessible> getGenericInput(String str) {
        reset();

        JTextArea message = new JTextArea(str);
        setClearUneditableTextArea(message);
        JTextArea inputArea = new JTextArea(1,5);
        JButton submit = new JButton("Submit");

        container.add(message);
        container.add(inputArea);
        container.add(submit);

        ArrayList<Accessible> components = new ArrayList<>();

        components.add(inputArea);
        components.add(submit);

        return components;
    }

    //public abstract void inputButtonFunction(String str);
}
