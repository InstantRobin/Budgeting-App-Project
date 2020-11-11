package ui.windows.subwindows.movemoneywindows;

import model.Account;
import ui.windows.Home;
import ui.windows.InputWindow;

import javax.swing.*;
import java.awt.*;

public abstract class AccountInput extends InputWindow {

    private Home home;

    public AccountInput(Container container, Home home) {
        super(container,home);
        this.home = home;
    }

    // EFFECTS: Displays all existing accounts to the user as buttons, passes selected account into getDate
    public void updateGUI() {
        super.getAccountWindow();
        for (int i = 0; i < container.getComponents().length; i++) {
            Account acc;
            JButton button = (JButton) container.getComponent(i);
            acc = manager.getAccounts().get(i);
            button.addActionListener(e -> inputButtonFunction(acc));
        }
    }

    protected abstract void inputButtonFunction(Account acc);

}
