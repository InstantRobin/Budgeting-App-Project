package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Currency;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.GetterWindow;

import java.awt.*;
import java.util.function.Consumer;

// Represents a window to create a new Currency
public class MakeCurrency extends GetterWindow {

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

    // EFFECTS: Prompts user for a name
    private void createCurrency() {
        getString("New Currency Name:", () -> {
            curName = textArea.getText();
            getSymbol();
        });
    }

    // EFFECTS: Prompts user for a symbol, can be anything, even nothing
    private void getSymbol() {
        getString("Symbol:", () -> {
            curSymbol = textArea.getText();
            getExchange();
        });
    }

    // EFFECTS: Prompts user for an exchange rate to USD
    //          Runs whatever fn requires the currency
    private void getExchange() {
        getString("Exchange Rate into USD:", () -> {
            curExchange = Double.parseDouble(textArea.getText());
            if (curExchange <= 0) {
                showMessageWindow("Error: Value must be positive");
                return;
            }
            Currency currency = new Currency(curName,curSymbol,curExchange);
            cons.accept(currency);
        });
    }
}