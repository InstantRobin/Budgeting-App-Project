package ui.windows.subwindows.inputwindows.movemoney;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountInput;
import ui.windows.subwindows.inputwindows.GetterWindow;

import javax.swing.*;
import java.awt.*;

// Represents a transfer window
public class Transfer extends GetterWindow {

    private Account acc1;
    private Account acc2;

    public Transfer(Container container, Home home) {
        super(container,home);
    }

    @Override
    public void updateGUI() {
        super.getInt(this::getAccount);
    }

    protected void getAccount() {
        super.getAccount(() -> {
            acc1 = acc;
            getSecondAccount();
        });
    }

    private void getSecondAccount() {
        super.getAccount(() -> {
            acc2 = acc;
            getDate();
        });
    }

    private void getDate() {
        super.getDate(this::makeTransfer);
    }

    public void makeTransfer() {
        MoveMoneyFunctions.transfer(acc1, acc2, val, date);
        showMessageWindow("Transferred " + MoveMoneyFunctions.moneyToString(val, acc1.getCurrency()) + " from "
                + acc1.getName() + " to " + acc2.getName() + " on " + date.toString());
    }
}
