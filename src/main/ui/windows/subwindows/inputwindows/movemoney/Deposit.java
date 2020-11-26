package ui.windows.subwindows.inputwindows.movemoney;

import model.MoveMoneyFunctions;
import ui.windows.Home;

// Represents a window where the user can deposit money
public class Deposit extends MoveMoneyWindow {

    public Deposit(Home home) {
        super(home);
    }

    // EFFECTS: calls makeDeposit once all other input steps completed
    @Override
    protected void finalFn() {
        makeDeposit();
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
