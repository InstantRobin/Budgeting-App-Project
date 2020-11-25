package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.movemoney.Deposit;
import ui.windows.subwindows.inputwindows.movemoney.Transfer;
import ui.windows.subwindows.inputwindows.movemoney.Withdraw;

import java.awt.*;

// Represents the window where the user can deposit, withdraw, or transfer money
public class MoveMoney extends SubWindowWithInputs {

    // EFFECTS: Adds all the buttons declared above the buttons array
    public MoveMoney(Container container, Home home) {
        super(container, home);
        initializeButtons();
    }

    // MODIFIES: buttons
    // EFFECTS: Adds and initializes all Move Money buttons
    @Override
    protected void initializeButtons() {
        addButton("Deposit", new Deposit(container,home));
        addButton("Withdraw", new Withdraw(container,home));
        addButton("Transfer", new Transfer(container,home));
        buttons.add(back);
    }

}
