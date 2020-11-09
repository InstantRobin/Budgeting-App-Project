package ui.windows;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageSavedData extends Window {

    private JButton button1 = new JButton("Load");
    private JButton button2 = new JButton("Save");
    private ArrayList<JButton> buttons = new ArrayList<>();

    public ManageSavedData(Container container) {
        super(container);
        buttons.add(button1);
        buttons.add(button2);
    }

    public void updateGUI() {
        reset();
        addButtons(buttons);
        button1.addActionListener(e -> load());
        button2.addActionListener(e -> save());
    }

    public void load() { //unfinished
        // stub
    }

    private void save() {
        // stub
    }
}
