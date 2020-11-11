package ui.windows.subwindows.movemoneywindows.withdraw;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.movemoneywindows.deposit.DepositMoneyInput;

import java.awt.*;
import java.time.LocalDate;

// Represents the Withdraw Window
public class Withdraw extends SubWindow {

    public Withdraw(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will deposit inputted amount into a given acct
    public void updateGUI() {
        WithdrawMoneyInput moneyInput = new WithdrawMoneyInput(container,home,this);
        moneyInput.updateGUI();
    }

    // EFFECTS: Takes all user input so far, withdraws val from account, logs account and date
    //          Displays a summary of the action to the user
    public void makeWithdrawal(int val, Account acc, LocalDate date) {
        MoveMoneyFunctions.withdraw(acc,val, date);
        showMessageWindow("Withdrew " + MoveMoneyFunctions.moneyToString(val, acc.getCurrency()) + " from "
                + acc.getName() + " on " + date.toString());
    }
}
