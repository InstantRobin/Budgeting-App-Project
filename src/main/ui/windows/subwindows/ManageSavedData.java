package ui.windows.subwindows;

import ui.Manager;
import ui.windows.Home;
import ui.windows.SubWindow;

import javax.swing.*;
import java.awt.*;

public class ManageSavedData extends SubWindow {

    // maybe rename buttons?
    private JButton button1 = new JButton("Load");
    private JButton button2 = new JButton("Save");

    public ManageSavedData(Container container, Home home) {
        super(container,home);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(back);
    }

    public void updateGUI() {
        reset();
        addButtons(buttons);
        button1.addActionListener(e -> load());
        button2.addActionListener(e -> save());
        addBackButtonListener();
    }

    public void load() { //unfinished
        manager.doManageData(Manager.ManageDataChoices.LOAD);
    }

    private void save() {
        manager.doManageData(Manager.ManageDataChoices.SAVE);
    }
}
