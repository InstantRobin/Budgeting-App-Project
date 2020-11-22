package ui.windows.subwindows.inputwindows;

import ui.windows.Home;

import java.awt.*;

// Represents a window where the user inputs a quantity of money
public class MoneyInput extends InputWindow {

    public MoneyInput(Container container, Home home) {
        super(container,home);
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will deposit inputted amount into a given acct
    public void updateGUI() {
        getGenericInput("Input Amount: ");
    }

    public int verifyVal(String str) throws NumberFormatException, NegativeValueException {
        double val;
        val = Double.parseDouble(str);
        if (val <= 0) {
            throw new NegativeValueException();
        }
        return (int)(val * 100);
    }

}
