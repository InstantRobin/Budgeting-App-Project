package ui.windows.subwindows.inputwindows;

import model.Account;
import ui.windows.Home;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class GetterWindow extends InputWindow {


    protected Account acc;
    protected JButton button;
    protected JTextArea textArea;

    public GetterWindow(Container container, Home home) {
        super(container, home);
    }

    protected void initButtons() {
        button = (JButton)container.getComponent(2);
        textArea = (JTextArea)container.getComponent(1);
    }

    protected void getString(String str, Runnable rn) {
        getGenericInput(str);
        initButtons();
        button.addActionListener(e -> rn.run());
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
