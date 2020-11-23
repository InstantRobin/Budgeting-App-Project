package ui.windows;

import ui.Manager;
import ui.windows.subwindows.ManageAccounts;
import ui.windows.subwindows.ManageSavedData;
import ui.windows.subwindows.MoveMoney;

import javax.swing.*;
import java.awt.*;

// The Home Page of the program
public class Home extends Window {

    private MoveMoney moveMoney;
    private ManageAccounts manageAccounts;
    private ManageSavedData manageSavedData;

    private final JButton moveMoneyButton = new JButton("Move Money");
    private final JButton manageAcctsButton = new JButton("Manage Accounts");
    private final JButton manageDataButton = new JButton("Manage Saved Data");

    public Home(Container container, Manager manager) {
        super(container, manager);
        initializeButtons();
        initializeClasses();
    }

    // MODIFIES: buttons
    // EFFECTS: Adds the initialized JButtons to Arraylist buttons
    private void initializeButtons() {
        buttons.add(moveMoneyButton);
        buttons.add(manageAcctsButton);
        buttons.add(manageDataButton);
    }

    // EFFECTS: Clears GUI, renders buttons, adds event listeners
    public void updateGUI() {
        reset();
        addButtons(buttons);
        createActionListeners();
    }

    // EFFECTS: Instantiates new classes for the different top level GUI's: the home screen and the three buttons
    private void initializeClasses() {
        moveMoney = new MoveMoney(container,this);
        manageAccounts = new ManageAccounts(container,this);
        manageSavedData = new ManageSavedData(container,this);
    }

    // EFFECTS: Loads the Home gui, makes buttons open up their desired GUIs
    private void createActionListeners() {
        moveMoneyButton.addActionListener(e -> moveMoney.updateGUI());
        manageAcctsButton.addActionListener(e -> manageAccounts.updateGUI());
        manageDataButton.addActionListener(e -> manageSavedData.updateGUI());
    }
}

