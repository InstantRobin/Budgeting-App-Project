package ui.windows;

import javax.swing.*;
import java.awt.*;

public abstract class SubWindow extends Window {

    protected Home home;
    protected JButton back = new JButton("Back");

    public SubWindow(Container container, Home home) {
        super(container);
        this.home = home;
    }

    protected void addBackButtonListener() {
        back.addActionListener(e -> home.updateGUI());
    }
}
