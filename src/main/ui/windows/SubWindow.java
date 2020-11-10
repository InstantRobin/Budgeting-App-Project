package ui.windows;

import javax.swing.*;
import java.awt.*;

public abstract class SubWindow extends Window {

    protected Home home;
    protected JButton back = new JButton("Back");

    public SubWindow(Container container, Home home) {
        super(container,home.manager);
        this.home = home;
    }

    protected void addBackButtonListener() {
        back.addActionListener(e -> home.updateGUI());
    }

    protected void setClearUneditableTextArea(JTextArea area) {
        area.setBackground(container.getBackground());
        area.setEditable(false);
    }
}
