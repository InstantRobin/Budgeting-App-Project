package ui.windows.subwindows.movemoneywindows.withdraw;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.movemoneywindows.AccountInput;
import ui.windows.subwindows.movemoneywindows.deposit.Deposit;

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
