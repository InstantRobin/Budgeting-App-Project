package ui.windows;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MoveMoney extends Window {

    private JButton button1 = new JButton("Deposit");
    private JButton button2 = new JButton("Withdraw");
    private JButton button3 = new JButton("Transfer");
    private ArrayList<JButton> buttons = new ArrayList<>();

    public MoveMoney(Container container) {
        super(container);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
    }

    public void updateGUI() {
        reset();
        addButtons(buttons);
        button1.addActionListener(e -> makeDeposit());
        button2.addActionListener(e -> makeWithdrawal());
        button3.addActionListener(e -> makeTransfer());
    }

    public void makeDeposit() { //unfinished
        reset();
        int val;
        JTextArea input = new JTextArea();
        JButton submit = new JButton();
        container.add(input,BorderLayout.CENTER);
        container.add(submit,BorderLayout.SOUTH);
        submit.addActionListener(e -> input.getText());
    }

    private void makeWithdrawal() {
        // stub
    }

    private void makeTransfer() {
        // stub
    }
}
