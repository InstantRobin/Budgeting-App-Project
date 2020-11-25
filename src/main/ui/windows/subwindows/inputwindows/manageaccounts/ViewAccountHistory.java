package ui.windows.subwindows.inputwindows.manageaccounts;

import model.History;
import model.LogEntry;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.InputWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static model.MoveMoneyFunctions.moneyToString;

// Represents a window to view the history of actions on an account
public class ViewAccountHistory extends InputWindow {

    private  ArrayList<String> dates = new ArrayList<>();
    private  ArrayList<String> changes = new ArrayList<>();
    private  ArrayList<String> totals = new ArrayList<>();
    private  JTextArea dateColumn = new JTextArea();
    private  JTextArea changeColumn = new JTextArea();
    private  JTextArea totalColumn = new JTextArea();

    public ViewAccountHistory(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Prompts user for an account, loads history as a table
    @Override
    public void updateGUI() {
        getAccount(this::initTable);
    }

    // MODIFIES: container, dateColumn, changeColumn, totalColumn, acc
    // EFFECTS: Readies the GUI, loads 3 columns, loads history into them
    private void initTable() {
        resetAll();

        setClearUneditableTextArea(dateColumn);
        setClearUneditableTextArea(changeColumn);
        setClearUneditableTextArea(totalColumn);
        JButton close = new JButton("Close");

        container.add(dateColumn);
        container.add(changeColumn);
        container.add(totalColumn);
        container.add(close);

        close.addActionListener(e -> home.updateGUI());

        loadHistory();
    }

    // MODIFIES: dates, changes, totals, dateColumn, changeColumnm, totalColumn
    // EFFECTS: resets all variables, if not reset then when window re-opened, all prev loaded tables will load again
    private void resetAll() {
        reset();
        dates = new ArrayList<>();
        changes = new ArrayList<>();
        totals = new ArrayList<>();
        dateColumn = new JTextArea();
        changeColumn = new JTextArea();
        totalColumn = new JTextArea();
    }

    // MODIFIES: dates, changes, totals, acc
    // EFFECTS: Adds history into the columns from Account
    private void loadHistory() {
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

    // MODIFIES: dateColumn
    // EFFECTS: Loads the event dates
    private void initDateColumn() {
        dateColumn.setColumns(dates.size());
        dateColumn.append("Date\n");
        for (String str: dates) {
            dateColumn.append(str + "\n");
        }
    }

    // MODIFIES: changeColumn
    // EFFECTS: Loads the amounts changed
    private void initChangeColumn() {
        changeColumn.setColumns(dates.size());
        changeColumn.append("Change\n");
        for (String str: changes) {
            changeColumn.append(str + "\n");
        }
    }

    // MODIFIES: totalColumn
    // EFFECTS: Loads the total value
    private void initTotalColumn() {
        totalColumn.setColumns(dates.size());
        totalColumn.append("Total\n");
        for (String str: totals) {
            totalColumn.append(str + "\n");
        }
    }
}
