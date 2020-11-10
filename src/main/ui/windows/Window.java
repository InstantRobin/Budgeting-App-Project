package ui.windows;

import ui.Manager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents a window of the program
abstract class Window {

    protected Container container;
    protected ArrayList<JButton> buttons = new ArrayList<>();
    protected Manager manager;

    public Window(Container container, Manager manager) {
        this.container = container;
        this.manager = manager;
    }

    // EFFECTS: Should update the GUI to display the new window
    public abstract void updateGUI();

    // EFFECTS: Clears the GUI
    protected void reset() {
        container.removeAll();
        container.revalidate();
        container.repaint();
    }

    // EFFECTS: Adds a list of JButtons to the GUI
    protected void addButtons(ArrayList<JButton> buttonList) {
        for (JButton button : buttonList) {
            container.add(button);
        }
    }
}
