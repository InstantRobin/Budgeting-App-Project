package ui.windows;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageAccounts extends Window {

    private JButton button1 = new JButton("View Account Balance");
    private JButton button2 = new JButton("View Account History");
    private JButton button3 = new JButton("Make New Account");
    private ArrayList<JButton> buttons = new ArrayList<>();

    public ManageAccounts(Container container) {
        super(container);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
    }

    public void updateGUI() {
        reset();
        addButtons(buttons);
        button1.addActionListener(e -> viewAcctBal());
        button2.addActionListener(e -> viewAcctHist());
        button3.addActionListener(e -> makeNewAcct());
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
