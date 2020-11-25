package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.manageaccounts.MakeAccount;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewAccount;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewAccountHistory;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewGraph;

import javax.swing.*;
import java.awt.*;

// Represents the window where the user can view an account balance, account history, or make a new account
public class ManageAccounts extends SubWindowWithInputs {

    private final JButton viewBalButton = new JButton("View Account Balance");
    private final JButton viewHistButton = new JButton("View Account History");
    private final JButton viewGraphButton = new JButton("View Account Graph");
    private final JButton makeAccButton = new JButton("Make New Account");

    // EFFECTS: Adds all the buttons declared above the buttons array
    public ManageAccounts(Container container, Home home) {
        super(container,home);
        initializeButtons();
        initializeClasses();
    }

    // MODIFIES: buttons
    // EFFECTS:Adds the four buttons to the buttons array
    @Override
    protected void initializeButtons() {
        buttons.add(viewBalButton);
        buttons.add(viewHistButton);
        buttons.add(viewGraphButton);
        buttons.add(makeAccButton);
        buttons.add(back);
    }

    // MODIFIES: subWindows
    // EFFECTS: Instantiates new classes for the different top level GUI's: the home screen and the three buttons
    @Override
    protected void initializeClasses() {
        inputWindows.add(new ViewAccount(container,home));
        inputWindows.add(new ViewAccountHistory(container,home));
        inputWindows.add(new ViewGraph(container,home));
        inputWindows.add(new MakeAccount(container,home));
    }
}
