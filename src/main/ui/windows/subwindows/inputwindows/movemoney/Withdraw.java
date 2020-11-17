package ui.windows.subwindows.inputwindows.movemoney;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.AccountInput;
import ui.windows.subwindows.inputwindows.DateInput;
import ui.windows.subwindows.inputwindows.MoneyInput;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

// Represents the Withdraw Window
public class Withdraw extends MoveMoney {

    public Withdraw(Container container, Home home) {
        super(container, home);
    }

    // TODO: Reduce Duplication btw Deposit and Withdraw (lambda or override)

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will deposit inputted amount into a given acct
    @Override
    public void updateGUI() {
        super.getInt(this::getAccount);
    }

    protected void getAccount(int val) {
        super.getAccount(val,this::getDate);
    }

    private void getDate(Account acc) {
        super.getDate(acc,this::makeWithdrawal);
    }

    // EFFECTS: Takes all user input so far, withdraws val from account, logs account and date
    //          Displays a summary of the action to the user
    public void makeWithdrawal() {
        MoveMoneyFunctions.withdraw(acc,val, date);
        showMessageWindow("Withdrew " + MoveMoneyFunctions.moneyToString(val, acc.getCurrency()) + " from "
                + acc.getName() + " on " + date.toString());
    }
}
