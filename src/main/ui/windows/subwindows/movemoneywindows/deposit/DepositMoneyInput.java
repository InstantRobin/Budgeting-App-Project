package ui.windows.subwindows.movemoneywindows.deposit;

import ui.windows.Home;
import ui.windows.subwindows.movemoneywindows.MoneyInput;

import java.awt.*;

public class DepositMoneyInput extends MoneyInput {

    Home home;
    Deposit deposit;

    private DepositMoneyInput(Container container, Home home, Deposit deposit) {
        super(container,home);
        this.home = home;
        this.deposit = deposit;
    }

    @Override
    protected void inputButtonFunction(int val) {
        DepositAccountInput accountInput = new DepositAccountInput(container,home,deposit, val);
        accountInput.updateGUI();
    }
}
