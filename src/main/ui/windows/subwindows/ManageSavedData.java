package ui.windows.subwindows;

import ui.Manager;
import ui.windows.Home;
import ui.windows.SubWindow;

import javax.swing.*;
import java.awt.*;

// Represents a window where the user can choose to save or load the current data
public class ManageSavedData extends SubWindow {

    private final JButton loadButton = new JButton("Load");
    private final JButton saveButton = new JButton("Save");

    // EFFECTS: Adds all the buttons declared above the buttons array
    public ManageSavedData(Container container, Home home) {
        super(container,home);
        initializeButtons();
    }

    // MODIFIES: buttons
    // EFFECTS:Adds the four buttons to the buttons array
    private void initializeButtons() {
        buttons.add(loadButton);
        buttons.add(saveButton);
        buttons.add(back);
    }

    // MODIFIES: loadButton, saveButton
    // EFFECTS: Clears GUI, loads buttons and their actionListeners
    @Override
    public void updateGUI() {
        reset();
        addButtons(buttons);
        loadButton.addActionListener(e -> load());
        saveButton.addActionListener(e -> save());
        addBackButtonListener();
    }

    // MODIFIES: manager
    // EFFECTS: Loads the data from Json
    public void load() {
        manager.doManageData(Manager.ManageDataChoices.LOAD);
        showMessageWindow("Load Complete!");
    }

    // MODIFIES: manager
    // EFFECTS: Saves the data to Json
    private void save() {
        manager.doManageData(Manager.ManageDataChoices.SAVE);
        showMessageWindow("Save Complete!");
    }
}
