package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.manageaccounts.MakeAccount;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewAccount;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewAccountHistory;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewGraph;

import java.awt.*;

// Represents the window where the user can view an account balance, account history, or make a new account
public class ManageAccounts extends SubWindowWithInputs {

    // EFFECTS: Adds all the buttons declared above the buttons array
    public ManageAccounts(Home home) {
        super(home);
        initializeButtons();
    }

    // MODIFIES: buttons
    // EFFECTS: Adds and initializes all Manage Accounts buttons
    @Override
    protected void initializeButtons() {
        addButton("View Account Balance", new ViewAccount(home));
        addButton("View Account History", new ViewAccountHistory(home));
        addButton("View Account Graph", new ViewGraph(home));
        addButton("Make New Account", new MakeAccount(home));
        buttons.add(back);
    }
}
