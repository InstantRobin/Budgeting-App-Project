package ui.windows.subwindows.inputwindows;

import model.Account;
import ui.windows.Home;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public abstract class AccountSelectWindow extends InputWindow {

    public AccountSelectWindow(Container container, Home home) {
        super(container,home);
    }

    public void updateGUI(Consumer<Account> consumer) {
        reset();
        AccountInput accountInput = new AccountInput(container,home);
        accountInput.updateGUI();

        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton)container.getComponent(i);
            Account account = manager.getAccounts().get(i);
            button.addActionListener(e -> consumer.accept(account));
        }
    }
}
