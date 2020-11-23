package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.manageaccounts.MakeAccount;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewAccount;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewAccountHistory;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewGraph;

import javax.swing.*;
import java.awt.*;

// Represents the window where the user can view an account balance, account history, or make a new account
public class ManageAccounts extends SubWindow {

    private final JButton viewBalButton = new JButton("View Account Balance");
    private final JButton viewHistButton = new JButton("View Account History");
    private final JButton viewGraphButton = new JButton("View Account Graph");
    private final JButton makeAccButton = new JButton("Make New Account");

    public ManageAccounts(Container container, Home home) {
        super(container,home);
        initializeButtons();
    }

    // MODIFIES: buttons
    // EFFECTS:Adds the four buttons to the buttons array
    private void initializeButtons() {
        buttons.add(viewBalButton);
        buttons.add(viewHistButton);
        buttons.add(viewGraphButton);
        buttons.add(makeAccButton);
        buttons.add(back);
    }

    // MODIFIES: viewBalButton, viewHistButton, viewGraphButton, makeAccButton
    // EFFECTS: Clears GUI, loads buttons and their actionListeners
    public void updateGUI() {
        reset();
        addButtons(buttons);
        viewBalButton.addActionListener(e -> viewAcctBal());
        viewHistButton.addActionListener(e -> viewAcctHist());
        viewGraphButton.addActionListener(e -> viewGraph());
        makeAccButton.addActionListener(e -> makeNewAcct());
        addBackButtonListener();
    }

    // EFFECTS: Clears GUI, prints the values of all the accounts
    public void viewAcctBal() {
        ViewAccount viewAccount = new ViewAccount(container,home);
        viewAccount.updateGUI();
    }

    // EFFECTS: Clears GUI, Allows user to select an account, displays said account's full history
    private void viewAcctHist() {
        ViewAccountHistory viewAccountHistory = new ViewAccountHistory(container,home);
        viewAccountHistory.updateGUI();
    }

    private void viewGraph() {
        ViewGraph viewGraph = new ViewGraph(container,home);
        viewGraph.updateGUI();
    }

    // EFFECTS: Makes window where user can input info to make a new account, adds account to manage.accounts
    private void makeNewAcct() {
        MakeAccount makeAccount = new MakeAccount(container,home);
        makeAccount.updateGUI();
    }
}
