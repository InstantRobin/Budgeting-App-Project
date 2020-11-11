package ui.windows.subwindows.inputwindows.transfer;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.DateInput;

import java.awt.*;
import java.time.LocalDate;

public class TransferDateInput extends DateInput {

    Transfer transfer;
    int val;
    Account acc1;
    Account acc2;

    public TransferDateInput(Container container, Home home, Transfer transfer, int val, Account acc1, Account acc2) {
        super(container, home);
        this.transfer = transfer;
        this.val = val;
        this.acc1 = acc1;
        this.acc2 = acc2;
    }

    @Override
    protected void inputButtonFunction(LocalDate date) {
        transfer.makeTransfer(val,acc1,acc2,date);
    }
}
