package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.InputWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class MainSubWindow extends SubWindow {

    protected final ArrayList<InputWindow> inputWindows = new ArrayList<>();

    public MainSubWindow(Container container, Home home) {
        super(container, home);
        initializeButtons();
        initializeClasses();
    }

    // MODIFIES: buttons
    // EFFECTS:Adds the four buttons to the buttons array
    protected abstract void initializeButtons();

    @Override
    public void updateGUI() {
        reset();
        addButtons(buttons);
        createActionListeners();
        addBackButtonListener();
    }

    // MODIFIES: subWindows
    // EFFECTS: Instantiates new classes for the different top level GUI's: the home screen and the three buttons
    protected abstract void initializeClasses();

    // EFFECTS: Makes buttons open up their desired GUIs
    protected void createActionListeners() {
        for (int i = 0; i < inputWindows.size(); i++) {
            JButton button = (JButton)container.getComponent(i);
            InputWindow inputWindow = inputWindows.get(i);
            button.addActionListener(e -> inputWindow.updateGUI());
        }
    }
}
