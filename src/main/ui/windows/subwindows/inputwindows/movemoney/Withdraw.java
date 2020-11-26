package ui.windows.subwindows.inputwindows.movemoney;

import model.MoveMoneyFunctions;
import ui.windows.Home;

// Represents a window where the user can withdraw money from an account
public class Withdraw extends MoveMoneyWindow {

    // EFFECTS: Creates a new MoveMoneyWindow with the given Home
    public Withdraw(Home home) {
        super(home);
    }

    // EFFECTS: calls makeWithdrawal once all other input steps completed
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
