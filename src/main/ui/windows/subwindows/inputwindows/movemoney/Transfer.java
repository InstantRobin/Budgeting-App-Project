package ui.windows.subwindows.inputwindows.movemoney;

import model.Account;
import model.MoveMoneyFunctions;
import ui.windows.Home;


// Represents a window where the user can transfer money between two accounts
public class Transfer extends MoveMoneyWindow {

    private Account acc1;
    private Account acc2;

    // EFFECTS: Creates a new MoveMoneyWindow with the given Home
    public Transfer(Home home) {
        super(home);
    }

    // MODIFIES: acc1
    // EFFECTS: Prompts user for source account, stores it as acc1, continues fn chain
    @Override
    protected void getAccountInput() {
        super.getAccount(() -> {
            acc1 = acc;
            getSecondAccountInput();
        });
    }

    // MODIFIES: acc2
    // EFFECTS: Prompts user for target account, stores it as acc2, continues fn chain
    private void getSecondAccountInput() {
        super.getAccount(() -> {
            acc2 = acc;
            getDateInput();
        });
    }

    // EFFECTS: calls makeTransfer once all other input steps completed
    @Override
    protected void finalFn() {
        makeTransfer();
    }

    // MODIFIES: acc1, acc2
    // EFFECTS: Takes all user input so far, transfers val from acc1 to acc2, including currency exchange
    //          Displays a summary of the action to the user
    public void makeTransfer() {
        MoveMoneyFunctions.transfer(acc1, acc2, val, date);
        showMessageWindow("Transferred " + MoveMoneyFunctions.moneyToString(val, acc1.getCurrency()) + " from "
                + acc1.getName() + " to " + acc2.getName() + " on " + date.toString());
    }
}
