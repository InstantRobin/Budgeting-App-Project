package ui.windows.subwindows.inputwindows.movemoney;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.GetterWindow;
import ui.windows.subwindows.inputwindows.NegativeValueException;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class MoveMoneyWindow extends GetterWindow {

    protected int val;
    protected LocalDate date;

    public MoveMoneyWindow(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a Double
    //          Initializes function chain where windows are loaded to get user input, past input is passed along
    //          By end of function chain, assuming good user input, will deposit inputted amount into a given acct
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

    public int verifyVal(String str) throws NumberFormatException, NegativeValueException {
        double val;
        val = Double.parseDouble(str);
        if (val <= 0) {
            throw new NegativeValueException();
        }
        return (int)(val * 100);
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a date
    //              if is, passes it and all past inputs into makeDeposit
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
