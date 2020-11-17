package ui.windows.subwindows.inputwindows.movemoney;

import model.Account;
import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.AccountInput;
import ui.windows.subwindows.inputwindows.DateInput;
import ui.windows.subwindows.inputwindows.MoneyInput;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.function.Consumer;

public abstract class MoveMoney extends SubWindow {

    protected int val;
    protected Account acc;
    protected LocalDate date;

    public MoveMoney(Container container, Home home) {
        super(container, home);
    }

    protected void getInt(Consumer<Integer> fn) {
        MoneyInput moneyInput = new MoneyInput(container,home);
        moneyInput.updateGUI();

        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> fn.accept(moneyInput.verifyVal(textArea.getText())));
    }

    protected void getAccount(int val, Consumer<Account> fn) {
        this.val = val;
        AccountInput accountInput = new AccountInput(container,home);
        accountInput.updateGUI();

        for (int i = 0; i < container.getComponents().length; i++) {
            JButton button = (JButton)container.getComponent(i);
            Account account = manager.getAccounts().get(i);
            button.addActionListener(e -> fn.accept(account));
        }
    }

    protected void getDate(Account acc, Runnable fn) {
        this.acc = acc;
        DateInput dateInput = new DateInput(container,home);
        dateInput.updateGUI();

        JButton button = (JButton)container.getComponent(2);
        JTextArea textArea = (JTextArea)container.getComponent(1);
        button.addActionListener(e -> {
            this.date = dateInput.verifyDate(textArea.getText());
            fn.run();
        });
    }
}
