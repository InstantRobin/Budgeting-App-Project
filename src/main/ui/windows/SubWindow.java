package ui.windows;

import javax.swing.*;
import java.awt.*;

public abstract class SubWindow extends Window {

    protected Home home;
    protected JButton back = new JButton("Back");
    String input;

    public SubWindow(Container container, Home home) {
        super(container,home.manager);
        this.home = home;
    }

    protected void addBackButtonListener() {
        back.addActionListener(e -> home.updateGUI());
    }

    protected String getInput() {
        reset();

        JTextArea message = new JTextArea("Input Amount: ");
        message.setEditable(false);
        message.setBackground(home.getContainer().getBackground());
        JTextArea inputArea = new JTextArea(1,5);
        JButton submit = new JButton("Submit");

        container.add(message);
        container.add(inputArea);
        container.add(submit);

        submit.addActionListener(e -> setInput(inputArea.getText()));
        return input;
    }

    protected void setInput(String userInput) {
        input = userInput;
        home.updateGUI();
    }
}
