package ui.windows.subwindows.movemoneywindows.deposit;

import ui.windows.Home;
import ui.windows.subwindows.movemoneywindows.MoneyInput;

import java.awt.*;

public class DepositMoneyInput extends MoneyInput {

    Home home;

    private DepositMoneyInput(Container container, Home home) {
        super(container,home);
        this.home = home;
    }

    @Override
    protected void inputButtonFunction(int val) {
        DepositAccountInput accountInput = new DepositAccountInput(container,home,val);
        accountInput.updateGUI();
    }
}
