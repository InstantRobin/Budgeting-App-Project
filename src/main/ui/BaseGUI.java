package ui;

import ui.windows.Home;
import ui.windows.subwindows.ManageAccounts;
import ui.windows.subwindows.ManageSavedData;
import ui.windows.subwindows.MoveMoney;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        setSize(WIDTH,HEIGHT);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,10,(HEIGHT / 3));
        setLayout(layout);

        container = getContentPane();

        home = new Home(container, manager);
        moveMoney = new MoveMoney(container,home);
        manageAccounts = new ManageAccounts(container,home);
        manageSavedData = new ManageSavedData(container,home);

        createHomeButtons();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createHomeButtons() {
        home.updateGUI();
        ArrayList<JButton> buttons = home.getButtons();
        buttons.get(0).addActionListener(e -> createMoveMoneyButtons());
        buttons.get(1).addActionListener(e -> createManageAccountsButtons());
        buttons.get(2).addActionListener(e -> createManageSavedDataButtons());
    }

    private void createMoveMoneyButtons() {
        moveMoney.updateGUI();
    }

    private void createManageAccountsButtons() {
        manageAccounts.updateGUI();
    }

    private void createManageSavedDataButtons() {
        manageSavedData.updateGUI();
    }


    private int getVal() {
        JTextArea input = new JTextArea();
        JButton submit = new JButton();
        container.add(input,BorderLayout.CENTER);
        container.add(submit,BorderLayout.SOUTH);
        submit.addActionListener(e -> input.getText());
        return 0; //stub
    }

    private void reset() {
        container.removeAll();
        container.revalidate();
        container.repaint();
    }
}
