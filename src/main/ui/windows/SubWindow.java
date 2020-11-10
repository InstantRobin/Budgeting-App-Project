package ui.windows;

import model.Account;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SubWindow extends Window {

    protected Home home;
    protected JButton back = new JButton("Back");
    String input;

    public SubWindow(Container container, Home home) {
        super(container,home.manager);
        this.home = home;
    }

    protected void addBackButtonListener() {
        back.addActionListener(e -> home.updateGUI());
    }

    protected ArrayList<Accessible> getInputWindow() {
        reset();

        JTextArea message = new JTextArea("Input Amount: ");
        setClearUneditableTextArea(message);
        JTextArea inputArea = new JTextArea(1,5);
        JButton submit = new JButton("Submit");

        container.add(message);
        container.add(inputArea);
        container.add(submit);

        ArrayList<Accessible> components = new ArrayList<>();

        components.add(inputArea);
        components.add(submit);

        //submit.addActionListener(e -> setInput(inputArea.getText()));
        return components;
    }

    protected void setInput(String userInput) {
        input = userInput;
        home.updateGUI();
    }

    protected void getAccountWindow() { // should return Account
        reset();
        List<Account> accounts = manager.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            JButton accountButton = new JButton(accounts.get(i).getName());
            container.add(accountButton);
            //accountButton.addActionListener(e -> return);
        }
    }

    protected void setClearUneditableTextArea(JTextArea area) {
        area.setBackground(container.getBackground());
        area.setEditable(false);
    }
}
