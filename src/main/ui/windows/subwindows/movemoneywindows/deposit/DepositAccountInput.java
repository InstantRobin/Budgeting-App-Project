package ui.windows.subwindows.movemoneywindows.deposit;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.movemoneywindows.AccountInput;

import java.awt.*;

public class DepositAccountInput extends AccountInput {

    int val;
    private Home home;
    Deposit deposit;

    public DepositAccountInput(Container container, Home home, Deposit deposit, int val) {
        super(container,home);
        this.home = home;
        this.deposit = deposit;
        this.val = val;
    }

    @Override
    protected void inputButtonFunction(Account acc) {
        DepositDateInput dateInput = new DepositDateInput(container,home,deposit,val,acc);
        dateInput.updateGUI();
    }
}
