package ui;

import ui.windows.Home;
import ui.windows.ManageAccounts;
import ui.windows.ManageSavedData;
import ui.windows.MoveMoney;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BaseGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    Manage manage = new Manage();
    Container container;

    public BaseGUI() {
        super("Budgeteer");

        setSize(WIDTH,HEIGHT);
        setLayout(new FlowLayout());

        container = getContentPane();
        createHomeButtons();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createHomeButtons() {
        Home home = new Home(container);
        home.updateGUI();
        ArrayList<JButton> buttons = home.getButtons();
        buttons.get(0).addActionListener(e -> createMoveMoneyButtons());
        buttons.get(1).addActionListener(e -> createManageAccountsButtons());
        buttons.get(2).addActionListener(e -> createManageSavedDataButtons());
    }

    private void createMoveMoneyButtons() {
        MoveMoney moveMoney = new MoveMoney(container);
        moveMoney.updateGUI();
    }


    private void createManageAccountsButtons() {
        ManageAccounts manageAccounts = new ManageAccounts(container);
        manageAccounts.updateGUI();
    }

    private void createManageSavedDataButtons() {
        ManageSavedData manageSavedData = new ManageSavedData(container);
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
