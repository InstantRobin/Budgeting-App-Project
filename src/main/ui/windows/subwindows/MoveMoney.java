package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.movemoney.Deposit;
import ui.windows.subwindows.inputwindows.movemoney.Transfer;
import ui.windows.subwindows.inputwindows.movemoney.Withdraw;

import javax.swing.*;
import java.awt.*;

// Represents the window where the user can deposit, withdraw, or transfer money
public class MoveMoney extends SubWindowWithInputs {

    private final JButton depositButton = new JButton("Deposit");
    private final JButton withdrawButton = new JButton("Withdraw");
    private final JButton transferButton = new JButton("Transfer");

    // EFFECTS: Adds all the buttons declared above the buttons array
    public MoveMoney(Container container, Home home) {
        super(container, home);
        initializeButtons();
        initializeClasses();
    }

    // MODIFIES: buttons
    // EFFECTS: Adds the four buttons to the buttons array
    @Override
    protected void initializeButtons() {
        buttons.add(depositButton);
        buttons.add(withdrawButton);
        buttons.add(transferButton);
        buttons.add(back);
    }

    // MODIFIES: subWindows
    // EFFECTS: Instantiates new classes for the different top level GUI's: the home screen and the three buttons
    @Override
    protected void initializeClasses() {
        inputWindows.add(new Deposit(container,home));
        inputWindows.add(new Withdraw(container,home));
        inputWindows.add(new Transfer(container,home));
    }
}
