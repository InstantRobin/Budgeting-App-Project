package ui.windows.subwindows.inputwindows.withdraw;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.MoneyInput;

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
