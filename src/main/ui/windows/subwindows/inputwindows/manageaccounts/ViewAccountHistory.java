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

    private  ArrayList<String> dates;
    private  ArrayList<String> changes;
    private  ArrayList<String> totals;
    private  JTextArea dateColumn;
    private  JTextArea changeColumn;
    private  JTextArea totalColumn;

    public ViewAccountHistory(Container container, Home home) {
        super(home);
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

        int sum = 0;
        for (LogEntry entry: hist) {
            dates.add(entry.getDate().toString());
            changes.add(moneyToString(entry.getVal(),acc.getCurrency()));
            sum += entry.getVal();
        }
        // This needs to be separate as an account's initial balance is technically never deposited, as is not
        //      stored so dif is equal to that amount, as calculated by subtracting the total of all past actions
        // This is probably not great design, should really be overhauled at some point
        int dif = acc.getBalance() - sum;
        for (LogEntry entry: hist) {
            totals.add(moneyToString(entry.getTotal() + dif, acc.getCurrency()));
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
