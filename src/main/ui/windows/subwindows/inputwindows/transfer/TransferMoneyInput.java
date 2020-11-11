package ui.windows.subwindows.inputwindows.transfer;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.MoneyInput;

import java.awt.*;

public class TransferMoneyInput extends MoneyInput {

    Transfer transfer;

    public TransferMoneyInput(Container container, Home home, Transfer transfer) {
        super(container, home);
        this.transfer = transfer;
    }

    @Override
    protected void inputButtonFunction(int val) {
        TransferFirstAccountInput accountInput = new TransferFirstAccountInput(container,home,transfer,val);
        accountInput.updateGUI();
    }
}
