package ui.windows.subwindows.movemoneywindows.withdraw;

import ui.windows.Home;
import ui.windows.subwindows.movemoneywindows.MoneyInput;
import ui.windows.subwindows.movemoneywindows.deposit.Deposit;

import java.awt.*;

public class WithdrawMoneyInput extends MoneyInput {

    Withdraw withdraw;

    public WithdrawMoneyInput(Container container, Home home, Withdraw withdraw) {
        super(container,home);
        this.withdraw = withdraw;
    }

    @Override
    protected void inputButtonFunction(int val) {
        WithdrawAccountInput accountInput = new WithdrawAccountInput(container,home,withdraw, val);
        accountInput.updateGUI();
    }
}
