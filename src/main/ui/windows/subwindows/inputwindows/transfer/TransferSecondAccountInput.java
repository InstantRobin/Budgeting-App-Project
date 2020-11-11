package ui.windows.subwindows.inputwindows.transfer;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountInput;

import java.awt.*;

public class TransferSecondAccountInput extends AccountInput {

    Transfer transfer;
    int val;
    Account acc1;

    public TransferSecondAccountInput(Container container, Home home, Transfer transfer, int val, Account acc) {
        super(container, home);
        this.transfer = transfer;
        this.val = val;
        this.acc1 = acc;
    }

    @Override
    protected void inputButtonFunction(Account acc2) {
        TransferDateInput accountInput = new TransferDateInput(container,home,transfer,val,acc1,acc2);
        accountInput.updateGUI();
    }
}
