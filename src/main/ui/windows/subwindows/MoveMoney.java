package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.movemoney.Deposit;
import ui.windows.subwindows.inputwindows.movemoney.Transfer;
import ui.windows.subwindows.inputwindows.movemoney.Withdraw;

import javax.swing.*;
import java.awt.*;

// Represents the window where the user can deposit, withdraw, or transfer money
public class MoveMoney extends SubWindow {

    private final JButton depositButton = new JButton("Deposit");
    private final JButton withdrawButton = new JButton("Withdraw");
    private final JButton transferButton = new JButton("Transfer");

    public MoveMoney(Container container, Home home) {
        super(container, home);
        initializeButtons();
    }

    // MODIFIES: buttons
    // EFFECTS: Adds the four buttons to the buttons array
    private void initializeButtons() {
        buttons.add(depositButton);
        buttons.add(withdrawButton);
        buttons.add(transferButton);
        buttons.add(back);
    }

    // MODIFIES: container
    // EFFECTS: Clears GUI, loads buttons and their actionListeners
    @Override
    public void updateGUI() {
        reset();
        addButtons(buttons);
        depositButton.addActionListener(e -> makeDepositWindow());
        withdrawButton.addActionListener(e -> makeWithdrawal());
        transferButton.addActionListener(e -> makeTransfer());
        addBackButtonListener();
    }

    // EFFECTS: Instantiates and Loads deposit window
    public void makeDepositWindow() {
        Deposit deposit = new Deposit(container,home);
        deposit.updateGUI();
    }

    // EFFECTS: Instantiates and Loads withdrawal window
    private void makeWithdrawal() {
        Withdraw withdraw = new Withdraw(container,home);
        withdraw.updateGUI();
    }

    // EFFECTS: Instantiates and Loads transfer window
    public void makeTransfer() {
        Transfer transfer = new Transfer(container,home);
        transfer.updateGUI();
    }
}
