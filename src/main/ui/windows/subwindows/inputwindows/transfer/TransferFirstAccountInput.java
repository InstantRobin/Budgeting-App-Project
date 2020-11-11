package ui.windows.subwindows.inputwindows.transfer;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountInput;

import java.awt.*;

public class TransferFirstAccountInput extends AccountInput {

    Transfer transfer;
    int val;

    public TransferFirstAccountInput(Container container, Home home, Transfer transfer, int val) {
        super(container, home);
        this.transfer = transfer;
        this.val = val;
    }

    @Override
    protected void inputButtonFunction(Account acc) {
        TransferSecondAccountInput accountInput = new TransferSecondAccountInput(container,home,transfer,val,acc);
        accountInput.updateGUI();
    }
}
