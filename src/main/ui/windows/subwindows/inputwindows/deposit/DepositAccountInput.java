package ui.windows.subwindows.inputwindows.deposit;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountInput;

import java.awt.*;

public class DepositAccountInput extends AccountInput {

    int val;
    Deposit deposit;

    public DepositAccountInput(Container container, Home home, Deposit deposit, int val) {
        super(container,home);
        this.deposit = deposit;
        this.val = val;
    }

    @Override
    protected void inputButtonFunction(Account acc) {
        DepositDateInput dateInput = new DepositDateInput(container,home,deposit,val,acc);
        dateInput.updateGUI();
    }
}
