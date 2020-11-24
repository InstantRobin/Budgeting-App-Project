package ui.windows.subwindows.inputwindows.movemoney;

import model.MoveMoneyFunctions;
import ui.windows.Home;

import java.awt.*;

// Represents a window where the user can withdraw money from an account
public class Withdraw extends MoveMoneyWindow {

    public Withdraw(Container container, Home home) {
        super(container, home);
    }

    @Override
    protected void finalFn() {
        makeWithdrawal();
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
