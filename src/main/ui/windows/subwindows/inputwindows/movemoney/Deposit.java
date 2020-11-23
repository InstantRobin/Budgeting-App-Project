package ui.windows.subwindows.inputwindows.movemoney;

import model.MoveMoneyFunctions;
import ui.windows.Home;

import java.awt.*;

// Represents a window where the user can deposit money
public class Deposit extends MoveMoneyWindow {

    public Deposit(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, prompts user for a number, turns it into an int
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will deposit inputted amount into a given acct
    @Override
    public void updateGUI() {
        super.getInt(this::getAccount);
    }

    // EFFECTS: Prompts user for an account, continues chain
    private void getAccount() {
        super.getAccount(this::getDate);
    }

    // EFFECTS: Prompts user for a date, continues chain
    private void getDate() {
        super.getDate(this::makeDeposit);
    }

    // MODIFIES: acc
    // EFFECTS: Takes all user input so far, deposits val into account, logs account and date
    //          Displays a summary of the action to the user
    public void makeDeposit() {
        MoveMoneyFunctions.deposit(acc,val, date);
        showMessageWindow("Deposited " + MoveMoneyFunctions.moneyToString(val, acc.getCurrency()) + " into "
                            + acc.getName() + " on " + date.toString());
    }
}
