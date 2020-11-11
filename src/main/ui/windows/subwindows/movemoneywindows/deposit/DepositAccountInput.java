package ui.windows.subwindows.movemoneywindows.deposit;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.movemoneywindows.AccountInput;

import java.awt.*;

public class DepositAccountInput extends AccountInput {

    int val;
    private Home home;

    public DepositAccountInput(Container container, Home home, int val) {
        super(container,home);
        this.home = home;
        this.val = val;
    }

    @Override
    protected void inputButtonFunction(Account acc) {
//        DepositAccountInput accountInput = new DepositAccountInput(container,home,val);
//        accountInput.updateGUI()
//        do this for DepositDateInput
    }
}
