package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountSelectWindow;

import java.awt.*;

public class ViewGraph extends AccountSelectWindow {

    public ViewGraph(Container container, Home home) {
        super(container, home);
    }

    @Override
    public void updateGUI() {
        super.updateGUI(this::loadGraph);
    }

    private void loadGraph(Account acc) {
        MoveMoneyFunctions.buildData(acc);
    }

}
