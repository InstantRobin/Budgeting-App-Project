package ui.windows.subwindows.inputwindows.viewaccount;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountInput;

import java.awt.*;

public class ViewAccountAccountInput extends AccountInput {

    public ViewAccountAccountInput(Container container, Home home) {
        super(container,home);
    }

    @Override
    protected void inputButtonFunction(Account acc) {
        showMessageWindow(acc.getName() + ": " + acc.getStringBalance());
    }
}
