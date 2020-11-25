package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.InputWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents a window that has buttons leading to windows that take inputs
public abstract class SubWindowWithInputs extends SubWindow {

    protected final ArrayList<InputWindow> inputWindows = new ArrayList<>();

    public SubWindowWithInputs(Container container, Home home) {
        super(container, home);
    }

    // MODIFIES: buttons
    // EFFECTS:Adds the four buttons to the buttons array
    protected abstract void initializeButtons();

    // MODIFIES: subWindows
    // EFFECTS: Instantiates new classes for the different top level GUI's: the home screen and the three buttons
    protected abstract void initializeClasses();

    // MODIFIES: container
    // EFFECTS: Clears GUI, loads buttons and their actionListeners, and a back button
    @Override
    public void updateGUI() {
        reset();
        addButtons(buttons);
        createActionListeners();
        addBackButtonListener();
    }

    // EFFECTS: Makes buttons open up their desired GUIs
    protected void createActionListeners() {
        for (int i = 0; i < inputWindows.size(); i++) {
            JButton button = (JButton)container.getComponent(i);
            InputWindow inputWindow = inputWindows.get(i);
            button.addActionListener(e -> inputWindow.updateGUI());
        }
    }
}
