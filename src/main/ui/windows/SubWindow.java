package ui.windows;

import javax.swing.*;
import java.awt.*;

// Represents a non-homescreen window
public abstract class SubWindow extends Window {

    protected Home home;
    protected JButton back = new JButton("Back");

    public SubWindow(Container container, Home home) {
        super(container,home.manager);
        this.home = home;
    }

    // EFFECTS: Makes back button return to home screen when pressed
    protected void addBackButtonListener() {
        back.addActionListener(e -> home.updateGUI());
    }

    // EFFECTS: Makes a JTextArea background color the same as that of the GUI
    protected void setClearUneditableTextArea(JTextArea area) {
        area.setBackground(container.getBackground());
        area.setEditable(false);
    }
}
