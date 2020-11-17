package ui.windows.subwindows.inputwindows.transfer;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountInput;

import javax.swing.*;
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
    public void updateGUI() {
        super.updateGUI();
        JTextArea message = new JTextArea("(Source Account)");
        setClearUneditableTextArea(message);
        container.add(message);
    }

    @Override
    protected void inputButtonFunction(Account acc) {
        TransferSecondAccountInput accountInput = new TransferSecondAccountInput(container,home,transfer,val,acc);
        accountInput.updateGUI();
    }
}
