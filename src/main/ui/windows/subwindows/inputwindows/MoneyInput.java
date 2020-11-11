package ui.windows.subwindows.inputwindows;

import ui.windows.Home;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents a window where the user inputs a quantity of money
public abstract class MoneyInput extends InputWindow {

    private double val;

    public MoneyInput(Container container, Home home) {
        super(container,home);
        val = 0;
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will deposit inputted amount into a given acct
    public void updateGUI() {
        ArrayList<Accessible> components = getInputWindow();
        JTextArea enteredText = (JTextArea) components.get(0);
        JButton enterButton = (JButton) components.get(1);

        enterButton.addActionListener(e -> verifyValThenContinue(enteredText.getText()));
    }

    // EFFECTS: Ensures user input is a double,
    //              if is, turns input into an integer of cents, passes it into getAccount
    //              if not, throws error screen, returns user to home
    private void verifyValThenContinue(String str) {
        try {
            val = Double.parseDouble(str);
            inputButtonFunction((int)(val * 100));
        } catch (NumberFormatException e) {
            showMessageWindow("Unrecognized number, please try again");
        }
    }

    protected abstract void inputButtonFunction(int val);
}
