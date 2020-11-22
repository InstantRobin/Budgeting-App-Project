package ui.windows.subwindows.inputwindows;

import model.Account;
import ui.windows.Home;
import ui.windows.SubWindow;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Represents a window used to get user input
public abstract class InputWindow extends SubWindow {

    protected Account acc;
    protected JTextArea textArea;

    public InputWindow(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Creates a window like so:
    /*
            str |     | Submit
     */
    //          The user can type in the white box, and Submit is a button to do so
    //          Sets the textArea variable so it can be accessed by subclasses
    //          Calls the given function
    protected void getGenericInput(String str, Runnable rn) {
        reset();

        JTextArea message = new JTextArea(str);
        setClearUneditableTextArea(message);
        JTextArea inputArea = new JTextArea(1,5);
        JButton submit = new JButton("Submit");

        container.add(message);
        container.add(inputArea);
        container.add(submit);

        textArea = (JTextArea)container.getComponent(1);
        submit.addActionListener(e -> rn.run());
    }

    // Prompts user for an Account, sets acc to that account, runs the given fn
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
