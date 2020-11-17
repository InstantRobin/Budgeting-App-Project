package ui.windows.subwindows.inputwindows.movemoney;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.AccountInput;
import ui.windows.subwindows.inputwindows.DateInput;
import ui.windows.subwindows.inputwindows.MoneyInput;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class Transfer extends SubWindow {

    public Transfer(Container container, Home home) {
        super(container,home);
    }

    @Override
    public void updateGUI() {
        MoneyInput moneyInput = new MoneyInput(container,home);
        moneyInput.updateGUI();

        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> getFirstAccount(moneyInput.verifyVal(textArea.getText())));
    }

    private void getFirstAccount(int val) {
        AccountInput accountInput = new AccountInput(container,home);
        accountInput.updateGUI();

        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton)container.getComponent(i);
            Account acc1 = manager.getAccounts().get(i);
            button.addActionListener(e -> getSecondAccount(val,acc1));
        }
    }

    private void getSecondAccount(int val, Account acc1) {
        AccountInput accountInput = new AccountInput(container,home);
        accountInput.updateGUI();

        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton)container.getComponent(i);
            Account acc2 = manager.getAccounts().get(i);
            button.addActionListener(e -> getDate(val,acc1,acc2));
        }
    }

    private void getDate(int val, Account acc1, Account acc2) {
        DateInput dateInput = new DateInput(container,home);
        dateInput.updateGUI();

        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> makeTransfer(val,acc1,acc2,dateInput.verifyDate(textArea.getText())));
    }

    public void makeTransfer(int val, Account acc1, Account acc2, LocalDate date) {
        MoveMoneyFunctions.transfer(acc1, acc2, val, date);
        showMessageWindow("Transferred " + MoveMoneyFunctions.moneyToString(val, acc1.getCurrency()) + " from "
                + acc1.getName() + " to " + acc2.getName() + " on " + date.toString());
    }
}
