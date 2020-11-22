package ui.windows.subwindows.inputwindows.movemoney;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;

import java.awt.*;

// Represents a window where the user can transfer money between two accounts
public class Transfer extends MoveMoneyWindow {

    private Account acc1;
    private Account acc2;

    public Transfer(Container container, Home home) {
        super(container,home);
    }

    // EFFECTS: Loads a Transfer Window, when submit is pressed, prompts user for a number, turns it into an int
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will transfer inputted amount from acc1 to acc2
    @Override
    public void updateGUI() {
        super.getInt(this::getAccount);
    }

    // EFFECTS: Prompts user for source account, stores it as acc1, continues fn chain
    protected void getAccount() {
        super.getAccount(() -> {
            acc1 = acc;
            getSecondAccount();
        });
    }

    // EFFECTS: Prompts user for target account, stores it as acc2, continues fn chain
    private void getSecondAccount() {
        super.getAccount(() -> {
            acc2 = acc;
            getDate();
        });
    }

    // EFFECTS: Prompts user for a date, continues fn chain
    private void getDate() {
        super.getDate(this::makeTransfer);
    }

    // EFFECTS: Takes all user input so far, transfers val from acc1 to acc2, including currency exchange
    //          Displays a summary of the action to the user
    public void makeTransfer() {
        MoveMoneyFunctions.transfer(acc1, acc2, val, date);
        showMessageWindow("Transferred " + MoveMoneyFunctions.moneyToString(val, acc1.getCurrency()) + " from "
                + acc1.getName() + " to " + acc2.getName() + " on " + date.toString());
    }
}
