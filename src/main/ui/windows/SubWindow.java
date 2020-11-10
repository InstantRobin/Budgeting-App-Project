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

    // EFFECTS: Displays a window where a given string is displayed to the user,
    //          as well as a button to return to home page
    protected void showMessageWindow(String str) {
        reset();
        JTextArea message = new JTextArea(str);
        setClearUneditableTextArea(message);
        JButton close = new JButton("Close");
        container.add(message);
        container.add(close);
        close.addActionListener(e -> home.updateGUI());
    }
}
