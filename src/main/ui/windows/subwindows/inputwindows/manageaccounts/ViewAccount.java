package ui.windows.subwindows.inputwindows.manageaccounts;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.GetterWindow;

import java.awt.*;

public class ViewAccount extends GetterWindow {

    public ViewAccount(Container container, Home home) {
        super(container,home);
    }

    @Override
    public void updateGUI() {
        super.getAccount(this::displayAccount);
    }

    private void displayAccount() {
        showMessageWindow(acc.getName() + ": " + acc.getStringBalance());
    }
}
