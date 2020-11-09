package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.SubWindow;

import javax.swing.*;
import java.awt.*;

public class ManageAccounts extends SubWindow {

    // maybe rename buttons?
    private JButton button1 = new JButton("View Account Balance");
    private JButton button2 = new JButton("View Account History");
    private JButton button3 = new JButton("Make New Account");

    public ManageAccounts(Container container, Home home) {
        super(container,home);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(back);
    }

    public void updateGUI() {
        reset();
        addButtons(buttons);
        button1.addActionListener(e -> viewAcctBal());
        button2.addActionListener(e -> viewAcctHist());
        button3.addActionListener(e -> makeNewAcct());
        addBackButtonListener();
    }

    public void viewAcctBal() { //unfinished
        // stub
    }

    private void viewAcctHist() {
        // stub
    }

    private void makeNewAcct() {
        // stub
    }
}
