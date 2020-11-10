package ui;

import ui.windows.Home;
import ui.windows.subwindows.ManageAccounts;
import ui.windows.subwindows.ManageSavedData;
import ui.windows.subwindows.MoveMoney;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Initializes the Graphic Interface of the Program using JFrame
public class BaseGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    Manager manager = new Manager();
    Container container;

    // Windows
    Home home;
    MoveMoney moveMoney;
    ManageAccounts manageAccounts;
    ManageSavedData manageSavedData;

    public BaseGUI() {
        super("Budgeteer");
        initializeGUI();
        initializeClasses();
        createHomeButtons();
    }

    // EFFECTS: Sets GUI to be given size, using a centered layout, to end program when closed, and to be visible
    private void initializeGUI() {
        setSize(WIDTH,HEIGHT);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,10,(HEIGHT / 3));
        setLayout(layout);

        container = getContentPane();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // EFFECTS: Creates new classes for the different top level GUI's: the home screen and the three buttons
    private void initializeClasses() {
        home = new Home(container, manager);
        moveMoney = new MoveMoney(container,home);
        manageAccounts = new ManageAccounts(container,home);
        manageSavedData = new ManageSavedData(container,home);
    }

    // EFFECTS: Loads the Home gui, makes buttons open up their desired GUIs
    private void createHomeButtons() {
        home.updateGUI();
        ArrayList<JButton> buttons = home.getButtons();
        buttons.get(0).addActionListener(e -> moveMoney.updateGUI());
        buttons.get(1).addActionListener(e -> manageAccounts.updateGUI());
        buttons.get(2).addActionListener(e -> manageSavedData.updateGUI());
    }
}
