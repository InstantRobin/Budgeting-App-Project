package ui.windows;

import ui.Manager;
import ui.windows.subwindows.ManageAccounts;
import ui.windows.subwindows.ManageSavedData;
import ui.windows.subwindows.MoveMoney;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents the Home Page of the program
public class Home extends Window {

    protected final ArrayList<SubWindow> subWindows = new ArrayList<>();

    // EFFECTS: Makes a new Window with the given Container, Manager
    //          Initializes all the buttons and subwindows
    public Home(Container container, Manager manager) {
        super(container, manager);
        initializeButtons();
    }

    // MODIFIES: Buttons
    // EFFECTS: Creates a new button with the given text in the button array,
    //          and puts the target window into the inputWindows array
    protected void addButton(String text, SubWindow window) {
        buttons.add(new JButton(text));
        subWindows.add(window);
    }

    // MODIFIES: buttons
    // EFFECTS: Adds and initializes all the buttons
    private void initializeButtons() {
        addButton("Move Money", new MoveMoney(this));
        addButton("Manage Accounts", new ManageAccounts(this));
        addButton("Manage Saved Data", new ManageSavedData(this));
    }

    // MODIFIES: Container
    // EFFECTS: Clears GUI, renders buttons, adds event listeners
    //          Visibility is necessary otherwise sometimes would be missing at least one button
    @Override
    public void updateGUI() {
        container.setVisible(false);
        reset();
        loadButtons(buttons);
        createActionListeners();
        container.setVisible(true);
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

