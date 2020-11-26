package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.movemoney.Deposit;
import ui.windows.subwindows.inputwindows.movemoney.Transfer;
import ui.windows.subwindows.inputwindows.movemoney.Withdraw;

// Represents the window where the user can deposit, withdraw, or transfer money
public class MoveMoney extends SubWindowWithInputs {

    // EFFECTS: Creates a new SubWindowWithInputs with given Home
    //          Creates and initializes a set of buttons
    public MoveMoney(Home home) {
        super(home);
        initializeButtons();
    }

    // MODIFIES: buttons
    // EFFECTS: Adds and initializes all Move Money buttons
    @Override
    protected void initializeButtons() {
        addButton("Deposit", new Deposit(home));
        addButton("Withdraw", new Withdraw(home));
        addButton("Transfer", new Transfer(home));
        buttons.add(back);
    }

}
