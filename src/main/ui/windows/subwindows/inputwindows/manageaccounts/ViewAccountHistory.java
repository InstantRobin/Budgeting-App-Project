package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Account;
import model.History;
import model.LogEntry;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.GetterWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static model.MoveMoneyFunctions.moneyToString;

public class ViewAccountHistory extends GetterWindow {

    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> changes = new ArrayList<>();
    ArrayList<String> totals = new ArrayList<>();
    JTextArea dateColumn = new JTextArea();
    JTextArea changeColumn = new JTextArea();
    JTextArea totalColumn = new JTextArea();

    public ViewAccountHistory(Container container, Home home) {
        super(container, home);
    }

    @Override
    public void updateGUI() {
        getAccount(this::initGUI);
    }

    private void initGUI() {
        reset();
        setClearUneditableTextArea(dateColumn);
        setClearUneditableTextArea(changeColumn);
        setClearUneditableTextArea(totalColumn);
        JButton close = new JButton("Close");

        container.add(dateColumn);
        container.add(changeColumn);
        container.add(totalColumn);
        container.add(close);

        close.addActionListener(e -> home.updateGUI());

        loadHistory(acc);
    }

    private void loadHistory(Account acc) {
        History hist = acc.getHistory();
        hist.updateTotals();
        for (LogEntry entry: hist) {
            dates.add(entry.getDate().toString());
            changes.add(moneyToString(entry.getVal(),acc.getCurrency()));
            totals.add(moneyToString(entry.getTotal(),acc.getCurrency()));
        }

        initDateColumn();
        initChangeColumn();
        initTotalColumn();
    }

    private void initDateColumn() {
        dateColumn.setColumns(dates.size());
        dateColumn.append("Date\n");
        for (String str: dates) {
            dateColumn.append(str + "\n");
        }
    }

    private void initChangeColumn() {
        changeColumn.setColumns(dates.size());
        changeColumn.append("Change\n");
        for (String str: changes) {
            changeColumn.append(str + "\n");
        }
    }

    private void initTotalColumn() {
        totalColumn.setColumns(dates.size());
        totalColumn.append("Total\n");
        for (String str: totals) {
            totalColumn.append(str + "\n");
        }
    }
}
