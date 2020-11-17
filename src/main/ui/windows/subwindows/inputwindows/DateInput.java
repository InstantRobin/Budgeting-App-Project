package ui.windows.subwindows.inputwindows;

import ui.windows.Home;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DateInput extends InputWindow {

    public DateInput(Container container, Home home) {
        super(container,home);
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a date
    public void updateGUI() {
        getDateWindow();
    }

    // EFFECTS: Ensures user input is a properly formatted date,
    //              if is, passes it and all past inputs into makeDeposit
    //              if not, throws error screen, returns user to home
    public LocalDate verifyDate(String str) {
        try {
            return LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            showMessageWindow("Unrecognized Date, please try again");
            return null;
        }
    }
}
