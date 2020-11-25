package ui.windows.subwindows.inputwindows.movemoney;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.InputWindow;
import ui.windows.subwindows.inputwindows.NegativeValueException;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Represents a window that will be used to move money into, out of, or between acconts
public abstract class MoveMoneyWindow extends InputWindow {

    protected int val;
    protected LocalDate date;

    public MoveMoneyWindow(Container container, Home home) {
        super(container, home);
    }


    // INPUT METHOD SERIES


    // EFFECTS: Loads an Input Window, when submit is pressed, prompts user for a number, turns it into an int
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will preform final action as determined by subclass
    @Override
    public void updateGUI() {
        getInt(this::getAccountInput);
    }

    // EFFECTS: Prompts user for an account, continues chain
    protected void getAccountInput() {
        getAccount(this::getDateInput);
    }

    // EFFECTS: Prompts user for a date, continues chain
    protected void getDateInput() {
        getDate(this::finalFn);
    }

    protected abstract void finalFn();


    // OTHER METHODS


    // MODIFIES val
    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          If input is good, turns it into an int of cents, calls given fn with the inputted value
    //          If not, throws error, returns user to home
    protected void getInt(Runnable fn) {
        getGenericInput("Input Amount: ", () -> {
            try {
                this.val = verifyVal(textArea.getText());
                fn.run();
            } catch (NumberFormatException exception) {
                showMessageWindow("Unrecognized number, please try again");
            } catch (NegativeValueException exception) {
                showMessageWindow("Negative number not allowed, please try again");
            }
        });
    }

    // EFFECTS: Verifies inputted value is a positive double, returns value as an int of cents
    //          If not number, throws NumberFormatException
    //          If negatiev, throws NegativeValueException
    public int verifyVal(String str) throws NumberFormatException, NegativeValueException {
        double val;
        val = Double.parseDouble(str);
        if (val <= 0) {
            throw new NegativeValueException();
        }
        return (int)(val * 100);
    }

    // MODIFIES: date
    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a date
    //              if is, passes it and all past inputs into given fn
    //              if not, throws error screen, returns user to home
    protected void getDate(Runnable fn) {
        getGenericInput("Enter Date As YYYY-MM-DD:", () -> {
            try {
                this.date = LocalDate.parse(textArea.getText());
                fn.run();
            } catch (DateTimeParseException exception) {
                showMessageWindow("Unrecognized Date, please try again");
            }
        });
    }
}
