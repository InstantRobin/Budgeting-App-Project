package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_E;
import static java.awt.event.KeyEvent.VK_ENTER;

public class BaseGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    Manage manage = new Manage();
    Container container;

    public BaseGUI() {
        super("Budgeteer");

        setSize(WIDTH,HEIGHT);
        setLayout(new BorderLayout());


        createFirstButtons();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createFirstButtons() {
        JButton button1 = new JButton("Move Money");
        JButton button2 = new JButton("Manage Accounts");
        JButton button3 = new JButton("Manage Saved Data");
        container = getContentPane();
        container.add(button1,BorderLayout.WEST);
        container.add(button2,BorderLayout.CENTER);
        container.add(button3,BorderLayout.EAST);

        button1.addActionListener(e -> createMoveMoneyButtons());
    }



    private void createMoveMoneyButtons() {
        reset();
        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        JButton transfer = new JButton("Transfer");
        container.add(deposit,BorderLayout.WEST);
        container.add(withdraw,BorderLayout.CENTER);
        container.add(transfer,BorderLayout.EAST);
        deposit.addActionListener(e -> makeDeposit());
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

    private int getVal() {
        JTextArea input = new JTextArea();
        JButton submit = new JButton();
        container.add(input,BorderLayout.CENTER);
        container.add(submit,BorderLayout.SOUTH);
        submit.addActionListener(e -> input.getText());
        return 0; //stub
    }

    private void reset() {
        container.removeAll();
        container.revalidate();
        container.repaint();
    }
}
