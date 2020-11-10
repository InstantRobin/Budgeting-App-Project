package ui.windows.subwindows;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.SubWindow;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
        button1.addActionListener(e -> makeDepositWindow());
        button2.addActionListener(e -> makeWithdrawal());
        button3.addActionListener(e -> makeTransfer());
        addBackButtonListener();
    }

    public void makeDepositWindow() { //unfinished
        ArrayList<Accessible> components = getInputWindow();
        JTextArea enteredText = (JTextArea) components.get(0);
        JButton enterButton = (JButton) components.get(1);
        try {
            enterButton.addActionListener(e -> valueEntered(enteredText.getText()));
        } catch (Exception e) {
            System.out.println("Bad Input"); //stub
        }
    }

    private void valueEntered(String str) {
        double val = 0;
        try {
            val = Double.parseDouble(str);
        } catch (Exception e) {
            // TODO: Throw Error message
            makeDepositWindow();
        }
        getAccountWindow((int)(val * 100));
    }

    private void getAccount(Account acc) {
        getAccountWindow();
    }

    // Not an override (maybe should rename)
    protected void getAccountWindow(int val) {
        super.getAccountWindow();
        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton) container.getComponent(i);
            Account acc = manager.getAccounts().get(i);
            button.addActionListener(e -> makeDeposit(val, acc));
        }
    }

    private void makeDeposit(int val, Account acc) {
        System.out.println(acc.getStringBalance());
        MoveMoneyFunctions.deposit(acc,val, LocalDate.of(2020,11,1));
        System.out.println(acc.getStringBalance());
        home.updateGUI();
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
