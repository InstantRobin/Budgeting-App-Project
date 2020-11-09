package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.SubWindow;

import javax.swing.*;
import java.awt.*;

public class MoveMoney extends SubWindow {

    // maybe rename buttons?
    private JButton button1 = new JButton("Deposit");
    private JButton button2 = new JButton("Withdraw");
    private JButton button3 = new JButton("Transfer");

    public MoveMoney(Container container, Home home) {
        super(container, home);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(back);
    }

    public void updateGUI() {
        reset();
        addButtons(buttons);
        button1.addActionListener(e -> makeDeposit());
        button2.addActionListener(e -> makeWithdrawal());
        button3.addActionListener(e -> makeTransfer());
        addBackButtonListener();
    }

    public void makeDeposit() { //unfinished
        getInput();
    }

    private void makeWithdrawal() {
        // stub
    }

    private void makeTransfer() {
        // stub
    }
}
