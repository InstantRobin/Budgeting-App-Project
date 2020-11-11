package ui.windows.subwindows.inputwindows;

import model.Account;
import ui.windows.Home;

import javax.swing.*;
import java.awt.*;

// Represents a window where the user selects an account
public abstract class AccountInput extends InputWindow {

    public AccountInput(Container container, Home home) {
        super(container,home);
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
