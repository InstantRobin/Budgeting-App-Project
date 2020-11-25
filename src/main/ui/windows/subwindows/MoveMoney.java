package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.InputWindow;
import ui.windows.subwindows.inputwindows.movemoney.Deposit;
import ui.windows.subwindows.inputwindows.movemoney.Transfer;
import ui.windows.subwindows.inputwindows.movemoney.Withdraw;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents the window where the user can deposit, withdraw, or transfer money
public class MoveMoney extends SubWindow {

    private final ArrayList<InputWindow> inputWindows = new ArrayList<>();

    private final JButton depositButton = new JButton("Deposit");
    private final JButton withdrawButton = new JButton("Withdraw");
    private final JButton transferButton = new JButton("Transfer");

    // EFFECTS: Adds all the buttons declared above the buttons array
    public MoveMoney(Container container, Home home) {
        super(container, home);
        initializeButtons();
        initializeClasses();
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
        createActionListeners();
        addBackButtonListener();
    }

    // MODIFIES: subWindows
    // EFFECTS: Instantiates new classes for the different top level GUI's: the home screen and the three buttons
    private void initializeClasses() {
        inputWindows.add(new Deposit(container,home));
        inputWindows.add(new Withdraw(container,home));
        inputWindows.add(new Transfer(container,home));
    }

    // EFFECTS: Makes buttons open up their desired GUIs
    private void createActionListeners() {
        for (int i = 0; i < inputWindows.size(); i++) {
            JButton button = (JButton)container.getComponent(i);
            InputWindow inputWindow = inputWindows.get(i);
            button.addActionListener(e -> inputWindow.updateGUI());
        }
    }
}
