package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountSelectWindow;

import java.awt.*;

public class ViewAccount extends AccountSelectWindow {

    public ViewAccount(Container container, Home home) {
        super(container,home);
    }

    @Override
    public void updateGUI() {
        super.updateGUI(this::displayAccount);
    }

    private void displayAccount(Account acc) {
        showMessageWindow(acc.getName() + ": " + acc.getStringBalance());
    }
}
