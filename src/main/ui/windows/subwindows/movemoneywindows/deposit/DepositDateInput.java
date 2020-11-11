package ui.windows.subwindows.movemoneywindows.deposit;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.movemoneywindows.DateInput;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DepositDateInput extends DateInput {

    int val;
    Account acc;
    private Home home;

    public DepositDateInput(Container container, Home home, int val, Account acc) {
        super(container,home);
        this.home = home;
        this.val = val;
        this.acc = acc;
    }

    // EFFECTS: Loads an Input Window, when submit is pressed, takes the TextArea value and verifies it as a date
    public void updateGUI() {
        ArrayList<Accessible> components = getDateWindow();
        JTextArea enteredText = (JTextArea) components.get(0);
        JButton enterButton = (JButton) components.get(1);

        enterButton.addActionListener(e -> verifyDateThenContinue(val, acc, enteredText.getText()));
    }

    // EFFECTS: Ensures user input is a properly formatted date,
    //              if is, passes it and all past inputs into makeDeposit
    //              if not, throws error screen, returns user to home
    private void verifyDateThenContinue(int val, Account acc, String str) {
        try {
            LocalDate date = LocalDate.parse(str);
            inputButtonFunction(date);
        } catch (Exception e) {
            showMessageWindow("Unrecognized Date, please try again");
        }
    }

    @Override
    protected void inputButtonFunction(LocalDate date) {

    }
}
