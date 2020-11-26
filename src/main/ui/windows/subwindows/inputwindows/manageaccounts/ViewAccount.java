package ui.windows.subwindows.inputwindows.manageaccounts;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.InputWindow;

// Represents a window to view an account's balance
public class ViewAccount extends InputWindow {

    public ViewAccount(Home home) {
        super(home);
    }

    // EFFECTS: Prompts user for an account, displays it
    @Override
    public void updateGUI() {
        super.getAccount(this::displayAccount);
    }

    // EFFECTS: Displays the name and value of the account
    private void displayAccount() {
        showMessageWindow(acc.getName() + ": " + acc.getStringBalance());
    }
}
