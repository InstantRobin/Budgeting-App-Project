package ui.windows.subwindows.inputwindows.deposit;

import model.Account;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.DateInput;

import java.awt.*;
import java.time.LocalDate;

public class DepositDateInput extends DateInput {

    int val;
    Account acc;
    Deposit deposit;

    public DepositDateInput(Container container, Home home, Deposit deposit, int val, Account acc) {
        super(container,home);
        this.deposit = deposit;
        this.val = val;
        this.acc = acc;
    }


    @Override
    protected void inputButtonFunction(LocalDate date) {
        deposit.makeDeposit(val,acc,date);
    }
}
