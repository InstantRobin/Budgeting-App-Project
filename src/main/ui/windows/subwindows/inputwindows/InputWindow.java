package ui.windows.subwindows.inputwindows;

import ui.windows.Home;
import ui.windows.SubWindow;

import javax.swing.*;
import java.awt.*;

// Represents a window where the user can choose between the money movement functions
public abstract class InputWindow extends SubWindow {

    protected final Home home;
    protected JTextArea textArea;

    public InputWindow(Container container, Home home) {
        super(container,home);
        this.home = home;
    }

    // EFFECTS: Creates a window like so:
    /*
            str |     | Submit
     */
    //          The user can type in the white box, and Submit is a button to do so
    //          Sets the textArea variable so it can be accessed by subclasses
    //          Calls the given function
    protected void getGenericInput(String str, Runnable rn) {
        reset();

        JTextArea message = new JTextArea(str);
        setClearUneditableTextArea(message);
        JTextArea inputArea = new JTextArea(1,5);
        JButton submit = new JButton("Submit");

        container.add(message);
        container.add(inputArea);
        container.add(submit);

        textArea = (JTextArea)container.getComponent(1);
        submit.addActionListener(e -> rn.run());
    }
}
