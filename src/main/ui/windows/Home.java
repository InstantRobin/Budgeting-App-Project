package ui.windows;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Home extends Window {

    private JButton button1 = new JButton("Move Money");
    private JButton button2 = new JButton("Manage Accounts");
    private JButton button3 = new JButton("Manage Saved Data");
    private ArrayList<JButton> buttons = new ArrayList<>();

    public Home(Container container) {
        super(container);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
    }

    public void updateGUI() {
        reset();
        addButtons(buttons);
    }


    public ArrayList<JButton> getButtons() {
        return buttons;
    }
}

