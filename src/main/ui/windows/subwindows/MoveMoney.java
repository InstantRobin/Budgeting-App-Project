package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.SubWindow;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MoveMoney extends SubWindow {

    double val = 0;

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
        ArrayList<Accessible> components = getInputWindow();
        JTextArea enteredText = (JTextArea) components.get(0);
        JButton enterButton = (JButton) components.get(1);
        try {
            enterButton.addActionListener(e -> val = (Double.parseDouble((enteredText.getText()))));
        } catch (Exception e) {
            System.out.println("Bad Input"); //stub
        }
    }

    @Override
    protected ArrayList<Accessible> getInputWindow() {
        ArrayList<Accessible> result = super.getInputWindow();
        return result;
    }

    private void makeWithdrawal() {
        // stub
    }

    private void makeTransfer() {
        // stub
    }
}
