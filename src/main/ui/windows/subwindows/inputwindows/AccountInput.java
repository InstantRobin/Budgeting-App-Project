package ui.windows.subwindows.inputwindows;

import model.Account;
import ui.windows.Home;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AccountInput extends InputWindow {

    public AccountInput(Container container, Home home) {
        super(container,home);
    }

    // EFFECTS: Displays all existing accounts to the user as buttons, passes selected account into getDate
    public void updateGUI() {
        reset();
        List<Account> accounts = manager.getAccounts();
        for (Account account : accounts) {
            JButton accountButton = new JButton(account.getName());
            container.add(accountButton);
        }
    }
}
