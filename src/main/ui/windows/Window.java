package ui.windows;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

abstract class Window {

    protected Container container;

    public Window(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }

    public abstract void updateGUI();

    protected void reset() {
        container.removeAll();
        container.revalidate();
        container.repaint();
    }

    protected void addButtons(ArrayList<JButton> buttonList) {
        for (JButton button : buttonList) {
            container.add(button);
        }
    }
}
