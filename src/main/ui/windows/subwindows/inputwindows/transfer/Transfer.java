package ui.windows.subwindows.inputwindows.transfer;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.SubWindow;

import java.awt.*;
import java.time.LocalDate;

public class Transfer extends SubWindow {

    public Transfer(Container container, Home home) {
        super(container,home);
    }

    @Override
    public void updateGUI() {
        TransferMoneyInput moneyInput = new TransferMoneyInput(container,home, this);
        moneyInput.updateGUI();
    }

    public void makeTransfer(int val, Account acc1, Account acc2, LocalDate date) {
        MoveMoneyFunctions.transfer(acc1, acc2, val, date);
        showMessageWindow("Transferred " + MoveMoneyFunctions.moneyToString(val, acc1.getCurrency()) + " from "
                + acc1.getName() + " to " + acc2.getName() + " on " + date.toString());
    }
}
