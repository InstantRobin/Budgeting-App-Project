package ui;

import ui.windows.Home;

import javax.swing.*;
import java.awt.*;

// Initializes the Graphic Interface of the Program using JFrame
public class BaseGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    Container container;

    // Windows
    Home home;

    // Modifies: this
    // EFFECTS: Starts up the GUI, loads the homepage
    public BaseGUI() {
        super("Budgeteer");
        initializeGUI();
        loadHome();
    }

    // MODIFIES: container
    // EFFECTS: Creates the base information of a centered GUI
    private void initializeGUI() {
        setSize(WIDTH,HEIGHT);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,10,(HEIGHT / 3));
        setLayout(layout);

        container = getContentPane();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // EFFECTS: Builds and loads the Home GUI
    private void loadHome() {
        home = new Home(container, new Manager());
        home.updateGUI();
    }
}
