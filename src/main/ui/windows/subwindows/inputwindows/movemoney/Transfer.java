package ui.windows.subwindows.inputwindows.movemoney;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountInput;

import javax.swing.*;
import java.awt.*;

// Represents a transfer window
public class Transfer extends MoveMoney {

    private Account acc2;

    public Transfer(Container container, Home home) {
        super(container,home);
    }

    @Override
    public void updateGUI() {
        super.getInt(this::getAccount);
    }

    protected void getAccount() {
        super.getAccount(this::getSecondAccount);
    }

    private void getSecondAccount() {
        AccountInput accountInput = new AccountInput(container,home);
        accountInput.updateGUI();

        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton)container.getComponent(i);
            Account acc2 = manager.getAccounts().get(i);
            button.addActionListener(e -> {
                this.acc2 = acc2;
                getDate();
            });
        }
    }

    private void getDate() {
        super.getDate(this::makeTransfer);
    }

    public void makeTransfer() {
        MoveMoneyFunctions.transfer(acc, acc2, val, date);
        showMessageWindow("Transferred " + MoveMoneyFunctions.moneyToString(val, acc.getCurrency()) + " from "
                + acc.getName() + " to " + acc2.getName() + " on " + date.toString());
    }
}
