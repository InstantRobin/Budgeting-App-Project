package ui.windows.subwindows.inputwindows.movemoney;

import model.MoveMoneyFunctions;
import ui.windows.Home;

import java.awt.*;

// Represents a window where the user can withdraw money from an account
public class Withdraw extends MoveMoneyWindow {

    public Withdraw(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Loads an Withdraw Window, when submit is pressed, prompts user for a number, turns it into an int
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will withdraw inputted amount from a given acct
    @Override
    public void updateGUI() {
        super.getInt(this::getAccount);
    }

    // EFFECTS: Prompts user for an account, continues chain
    protected void getAccount() {
        super.getAccount(this::getDate);
    }

    // EFFECTS: Prompts user for a date, continues chain
    private void getDate() {
        super.getDate(this::makeWithdrawal);
    }

    // MODIFIES: acc
    // EFFECTS: Takes all user input so far, withdraws val from account, logs account and date
    //          Displays a summary of the action to the user
    public void makeWithdrawal() {
        MoveMoneyFunctions.withdraw(acc,val, date);
        showMessageWindow("Withdrew " + MoveMoneyFunctions.moneyToString(val, acc.getCurrency()) + " from "
                + acc.getName() + " on " + date.toString());
    }
}
