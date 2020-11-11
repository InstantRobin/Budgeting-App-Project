package ui.windows.subwindows.inputwindows.viewaccount;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.InputWindow;

import java.awt.*;

public class ViewAccount extends InputWindow {

    public ViewAccount(Container container, Home home) {
        super(container,home);
    }

    @Override
    public void updateGUI() {
        reset();
        ViewAccountAccountInput accountInput = new ViewAccountAccountInput(container,home);
        accountInput.updateGUI();
    }
}
