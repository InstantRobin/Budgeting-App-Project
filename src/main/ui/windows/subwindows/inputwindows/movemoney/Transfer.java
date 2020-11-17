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

public class Transfer extends MoveMoney {

    private Account acc2;

    public Transfer(Container container, Home home) {
        super(container,home);
    }

    @Override
    public void updateGUI() {
        super.getInt(this::getAccount);
    }

    protected void getAccount(int val) {
        super.getAccount(val,this::getSecondAccount);
    }

    private void getSecondAccount(Account acc1) {
        this.acc = acc1;
        AccountInput accountInput = new AccountInput(container,home);
        accountInput.updateGUI();

        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton)container.getComponent(i);
            Account acc2 = manager.getAccounts().get(i);
            button.addActionListener(e -> getDate(acc2));
        }
    }

    private void getDate(Account acc2) {
        super.getDate(acc2,this::makeTransfer);
    }

    public void makeTransfer() {
        MoveMoneyFunctions.transfer(acc, acc2, val, date);
        showMessageWindow("Transferred " + MoveMoneyFunctions.moneyToString(val, acc.getCurrency()) + " from "
                + acc.getName() + " to " + acc2.getName() + " on " + date.toString());
    }
}
