package ui.windows.subwindows.inputwindows;

import model.Account;
import ui.windows.Home;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Represents a window used to get user input
public abstract class GetterWindow extends InputWindow {

    protected Account acc;

    public GetterWindow(Container container, Home home) {
        super(container, home);
    }

    protected void getAccount(Runnable fn) {
        reset();
        List<Account> accounts = manager.getAccounts();
        if (accounts.isEmpty()) {
            showMessageWindow("No accounts");
        }
        for (Account account : accounts) {
            JButton accountButton = new JButton(account.getName());
            container.add(accountButton);

            accountButton.addActionListener(e -> {
                this.acc = account;
                fn.run();
            });
        }
    }
}
