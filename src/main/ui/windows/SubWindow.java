package ui.windows;

import javax.swing.*;

// Represents a non-homescreen window
public abstract class SubWindow extends Window {

    protected final Home home;
    protected final JButton back = new JButton("Back");

    // Modifies: this
    // EFFECTS: Creates a new Window using the container and manager from the given home, also sets home to given Home
    public SubWindow(Home home) {
        super(home.getContainer(),home.getManager());
        this.home = home;
    }

    // MODIFIES: back
    // EFFECTS: Makes back button return to home screen when pressed
    protected void addBackButtonListener() {
        back.addActionListener(e -> home.updateGUI());
    }

    // EFFECTS: Makes a JTextArea background color the same as that of the GUI
    protected void setClearUneditableTextArea(JTextArea area) {
        area.setBackground(container.getBackground());
        area.setEditable(false);
    }

    // MODIFIES: container
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
