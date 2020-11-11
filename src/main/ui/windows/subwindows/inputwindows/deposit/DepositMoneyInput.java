package ui.windows.subwindows.inputwindows.deposit;

import ui.windows.Home;
import ui.windows.subwindows.inputwindows.MoneyInput;

import java.awt.*;

public class DepositMoneyInput extends MoneyInput {

    Deposit deposit;

    public DepositMoneyInput(Container container, Home home, Deposit deposit) {
        super(container,home);
        this.deposit = deposit;
    }

    @Override
    protected void inputButtonFunction(int val) {
        DepositAccountInput accountInput = new DepositAccountInput(container,home,deposit, val);
        accountInput.updateGUI();
    }
}