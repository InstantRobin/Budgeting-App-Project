package ui.windows;

import ui.Manager;
import ui.windows.subwindows.ManageAccounts;
import ui.windows.subwindows.ManageSavedData;
import ui.windows.subwindows.MoveMoney;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// The Home Page of the program
public class Home extends Window {

    private final ArrayList<SubWindow> subWindows = new ArrayList<>();

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
    //          Visibility is necessary otherwise sometimes would be missing at least one button
    @Override
    public void updateGUI() {
        container.setVisible(false);
        reset();
        addButtons(buttons);
        createActionListeners();
        container.setVisible(true);
    }

    // MODIFIES: subWindows
    // EFFECTS: Instantiates new classes for the different top level GUI's: the home screen and the three buttons
    private void initializeClasses() {
        subWindows.add(new MoveMoney(container,this));
        subWindows.add(new ManageAccounts(container,this));
        subWindows.add(new ManageSavedData(container,this));
    }

    // EFFECTS: Loads the Home gui, makes buttons open up their desired GUIs
    private void createActionListeners() {
        for (int i = 0; i < subWindows.size(); i++) {
            JButton button = (JButton)container.getComponent(i);
            SubWindow subwindow = subWindows.get(i);
            button.addActionListener(e -> subwindow.updateGUI());
        }
    }
}

