package ui;

import ui.windows.Home;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BaseGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    Manage manage = new Manage();
    Container container;

    public BaseGUI() {
        super("Budgeteer");

        setSize(WIDTH,HEIGHT);
        setLayout(new FlowLayout());


        createHomeButtons();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createHomeButtons() {
        container = getContentPane();
        Home home = new Home(container);
        home.updateGUI();
        ArrayList<JButton> buttons = home.getButtons();
        buttons.get(1).addActionListener(e -> createMoveMoneyButtons());
        buttons.get(2).addActionListener(e -> createManageAccountsButtons());
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

    private void createManageAccountsButtons() {
        reset();
        JButton viewAccountBalance = new JButton("View Account Balance");
        JButton viewAccountHistory = new JButton("View Account History");
        JButton makeNewAccount = new JButton("Make New Account");
        container.add(viewAccountBalance,BorderLayout.WEST);
        container.add(viewAccountHistory,BorderLayout.CENTER);
        container.add(makeNewAccount,BorderLayout.EAST);
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
