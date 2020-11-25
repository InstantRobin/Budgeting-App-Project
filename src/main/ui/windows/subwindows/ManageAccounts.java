package ui.windows.subwindows;

import ui.windows.Home;
import ui.windows.SubWindow;
import ui.windows.subwindows.inputwindows.InputWindow;
import ui.windows.subwindows.inputwindows.manageaccounts.MakeAccount;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewAccount;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewAccountHistory;
import ui.windows.subwindows.inputwindows.manageaccounts.ViewGraph;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents the window where the user can view an account balance, account history, or make a new account
public class ManageAccounts extends SubWindow {

    private final ArrayList<InputWindow> inputWindows = new ArrayList<>();

    private final JButton viewBalButton = new JButton("View Account Balance");
    private final JButton viewHistButton = new JButton("View Account History");
    private final JButton viewGraphButton = new JButton("View Account Graph");
    private final JButton makeAccButton = new JButton("Make New Account");

    // EFFECTS: Adds all the buttons declared above the buttons array
    public ManageAccounts(Container container, Home home) {
        super(container,home);
        initializeButtons();
        initializeClasses();
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
    @Override
    public void updateGUI() {
        reset();
        addButtons(buttons);
        createActionListeners();
        addBackButtonListener();
    }

    // MODIFIES: subWindows
    // EFFECTS: Instantiates new classes for the different top level GUI's: the home screen and the three buttons
    private void initializeClasses() {
        inputWindows.add(new ViewAccount(container,home));
        inputWindows.add(new ViewAccountHistory(container,home));
        inputWindows.add(new ViewGraph(container,home));
        inputWindows.add(new MakeAccount(container,home));
    }

    // EFFECTS: Makes buttons open up their desired GUIs
    private void createActionListeners() {
        for (int i = 0; i < inputWindows.size(); i++) {
            JButton button = (JButton)container.getComponent(i);
            InputWindow inputWindow = inputWindows.get(i);
            button.addActionListener(e -> inputWindow.updateGUI());
        }
    }
}
