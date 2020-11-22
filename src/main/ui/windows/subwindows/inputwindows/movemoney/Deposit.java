package ui.windows.subwindows.inputwindows.movemoney;

import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.GetterWindow;

import java.awt.*;

// Represents the Deposit Window
public class Deposit extends GetterWindow {

    public Deposit(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will deposit inputted amount into a given acct
    @Override
    public void updateGUI() {
        super.getInt(this::getAccount);
    }

    private void getAccount() {
        super.getAccount(this::getDate);
    }

    private void getDate() {
        super.getDate(this::makeDeposit);
    }

    // EFFECTS: Takes all user input so far, deposits val into account, logs account and date
    //          Displays a summary of the action to the user
    public void makeDeposit() {
        MoveMoneyFunctions.deposit(acc,val, date);
        showMessageWindow("Deposited " + MoveMoneyFunctions.moneyToString(val, acc.getCurrency()) + " into "
                            + acc.getName() + " on " + date.toString());
    }
}
