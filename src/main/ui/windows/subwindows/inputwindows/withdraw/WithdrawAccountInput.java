package ui.windows.subwindows.inputwindows.withdraw;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountInput;

import java.awt.*;

public class WithdrawAccountInput extends AccountInput {

    int val;
    Withdraw withdraw;

    public WithdrawAccountInput(Container container, Home home, Withdraw withdraw, int val) {
        super(container,home);
        this.withdraw = withdraw;
        this.val = val;
    }

    @Override
    protected void inputButtonFunction(Account acc) {
        WithdrawDateInput dateInput = new WithdrawDateInput(container,home,withdraw,val,acc);
        dateInput.updateGUI();
    }
}
