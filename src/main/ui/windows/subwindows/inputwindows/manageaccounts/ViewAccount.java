package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.InputWindow;
import ui.windows.subwindows.inputwindows.AccountInput;

import javax.swing.*;
import java.awt.*;

public class ViewAccount extends InputWindow {

    public ViewAccount(Container container, Home home) {
        super(container,home);
    }

    @Override
    public void updateGUI() {
        reset();
        AccountInput accountInput = new AccountInput(container,home);
        accountInput.updateGUI();

        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton)container.getComponent(i);
            Account account = manager.getAccounts().get(i);
            button.addActionListener(e -> displayAccount(account));
        }
    }

    private void displayAccount(Account acc) {
        showMessageWindow(acc.getName() + ": " + acc.getStringBalance());
    }
}
