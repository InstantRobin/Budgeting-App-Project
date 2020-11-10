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

        enterButton.addActionListener(e -> verifyValThenContinue(enteredText.getText()));
    }

    private void verifyValThenContinue(String str) {
        try {
            double val = Double.parseDouble(str);
            getAccount((int)(val * 100));
        } catch (NumberFormatException e) {
            showErrorPage("Unrecognized number, please try again");
        }
    }

    // Not an override (maybe should rename)
    private void getAccount(int val) {
        super.getAccountWindow();
        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton) container.getComponent(i);
            Account acc = manager.getAccounts().get(i);
            button.addActionListener(e -> getDate(val, acc));
        }
    }

    private void getDate(int val, Account acc) {
        ArrayList<Accessible> components = getDateWindow();
        JTextArea enteredText = (JTextArea) components.get(0);
        JButton enterButton = (JButton) components.get(1);

        enterButton.addActionListener(e -> verifyDateThenContinue(val, acc, enteredText.getText()));
    }

    private void verifyDateThenContinue(int val, Account acc, String str) {
        try {
            LocalDate date = LocalDate.parse(str);
            makeDeposit(val,acc,date);
        } catch (Exception e) {
            showErrorPage("Unrecognized Date, please try again");
        }
    }

    private void makeDeposit(int val, Account acc, LocalDate date) {
        System.out.println((acc.getHistory().get(acc.getHistory().size() - 1)).getVal());
        MoveMoneyFunctions.deposit(acc,val, date);
        System.out.println((acc.getHistory().get(acc.getHistory().size() - 1)).getVal());
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
