package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Currency;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.InputWindow;

import java.awt.*;
import java.util.function.Consumer;

// Represents a window to create a new Currency
public class MakeCurrency extends InputWindow {

    private String curName;
    private String curSymbol;
    private double curExchange;
    private Consumer<Currency> cons;

    public MakeCurrency(Container container, Home home, Consumer<Currency> cons) {
        super(container, home);
        this.cons = cons;
    }

    // EFFECTS: Updates GUI to initiate Currency creation process
    @Override
    public void updateGUI() {
        createCurrency();
    }

    // MODIFIES: curName
    // EFFECTS: Prompts user for a name
    private void createCurrency() {
        getGenericInput("New Currency Name:", () -> {
            curName = textArea.getText();
            getSymbol();
        });
    }

    // MODIFIES: curSymbol
    // EFFECTS: Prompts user for a symbol, can be anything, even nothing
    private void getSymbol() {
        getGenericInput("Symbol:", () -> {
            curSymbol = textArea.getText();
            getExchange();
        });
    }

    // MODIFIES: curExchange
    // EFFECTS: Prompts user for an exchange rate to USD
    //          Runs whatever fn requires the currency
    private void getExchange() {
        getGenericInput("Exchange Rate into USD:", () -> {
            curExchange = Double.parseDouble(textArea.getText());
            if (curExchange <= 0) {
                showMessageWindow("Error: Value must be positive");
                return;
            }
            cons.accept(new Currency(curName,curSymbol,curExchange));
        });
    }
}
