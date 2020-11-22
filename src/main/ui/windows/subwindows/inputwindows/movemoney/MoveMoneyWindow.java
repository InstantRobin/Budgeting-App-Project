package ui.windows.subwindows.inputwindows.movemoney;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.GetterWindow;
import ui.windows.subwindows.inputwindows.NegativeValueException;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Represents a window that will be used to move money into, out of, or between acconts
public abstract class MoveMoneyWindow extends GetterWindow {

    protected int val;
    protected LocalDate date;

    public MoveMoneyWindow(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          If input is good, turns it into an int of cents, calls given fn with the inputted value
    //          If not, throws error, returns user to home
    protected void getInt(Runnable fn) {
        getString("Input Amount: ", () -> {
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

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a date
    //              if is, passes it and all past inputs into given fn
    //              if not, throws error screen, returns user to home
    protected void getDate(Runnable fn) {
        getString("Enter Date As YYYY-MM-DD:", () -> {
            try {
                this.date = LocalDate.parse(textArea.getText());
                fn.run();
            } catch (DateTimeParseException exception) {
                showMessageWindow("Unrecognized Date, please try again");
            }
        });
    }
}
