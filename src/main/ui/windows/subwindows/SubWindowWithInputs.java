package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.InputWindow;

import javax.swing.*;
import java.util.ArrayList;

// Represents a window that has buttons leading to windows that take inputs
public abstract class SubWindowWithInputs extends SubWindow {

    protected final ArrayList<InputWindow> inputWindows = new ArrayList<>();

    // EFFECTS: Creates a new subwindow with the given Home
    public SubWindowWithInputs(Home home) {
        super(home);
    }

    // MODIFIES: Buttons
    // EFFECTS: Creates a new button with the given text in the button array,
    //          and puts the target window into the inputWindows array
    protected void addButton(String text, InputWindow window) {
        buttons.add(new JButton(text));
        inputWindows.add(window);
    }

    // MODIFIES: buttons
    // EFFECTS:Adds and initializes all the buttons
    protected abstract void initializeButtons();


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
