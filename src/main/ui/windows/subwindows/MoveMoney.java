package ui.windows.subwindows;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.SubWindow;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
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
        getAccount((int)(val * 100));
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

        try {
            enterButton.addActionListener(e -> makeDeposit(val, acc, LocalDate.parse(enteredText.getText())));
        } catch (Exception e) {
            System.out.println("Bad Input"); //stub
        }
    }

    private void makeDeposit(int val, Account acc, LocalDate date) {
        System.out.println((acc.getHistory().get(acc.getHistory().size() - 1)).getDate());
        MoveMoneyFunctions.deposit(acc,val, date);
        System.out.println((acc.getHistory().get(acc.getHistory().size() - 1)).getDate());
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
