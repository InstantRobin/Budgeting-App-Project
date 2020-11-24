package ui.windows;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents a window of the program
public abstract class Window {

    protected final Container container;
    protected final ArrayList<JButton> buttons = new ArrayList<>();

    public Window(Container container) {
        this.container = container;
    }

    // EFFECTS: Should update the GUI to display the new window
    public abstract void updateGUI();

    // MODIFIES: container
    // EFFECTS: Clears the GUI
    protected void reset() {
        container.removeAll();
        container.revalidate();
        container.repaint();
    }

    // MODIFIES: container
    // EFFECTS: Adds a list of JButtons to the GUI
    protected void addButtons(ArrayList<JButton> buttonList) {
        for (JButton button : buttonList) {
            container.add(button);
        }
    }
}
