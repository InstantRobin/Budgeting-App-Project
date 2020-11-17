package ui.windows.subwindows.inputwindows;

import ui.windows.Home;
import ui.windows.SubWindow;

import javax.swing.*;
import java.awt.*;

// Represents a window where the user can choose between the money movement functions
public abstract class InputWindow extends SubWindow {

    protected final Home home;

    public InputWindow(Container container, Home home) {
        super(container,home);
        this.home = home;
    }

    // EFFECTS: Creates a window like so:
    /*
            str |     | Submit
     */
    //          The user can type in the white box, and Submit is a button to do so
    //          Returns the box and the submit button so that the function that eventListeners can be added by
    //              the function that calls this
    protected void getGenericInput(String str) {
        reset();

        JTextArea message = new JTextArea(str);
        setClearUneditableTextArea(message);
        JTextArea inputArea = new JTextArea(1,5);
        JButton submit = new JButton("Submit");

        container.add(message);
        container.add(inputArea);
        container.add(submit);
    }
}
