package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Currency;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.GetterWindow;

import java.awt.*;
import java.util.function.Consumer;

public class MakeCurrency extends GetterWindow {

    private String curName;
    private String curSymbol;
    private double curExchange;
    private Consumer<Currency> cons;

    public MakeCurrency(Container container, Home home, Consumer<Currency> cons) {
        super(container, home);
        this.cons = cons;
    }

    @Override
    public void updateGUI() {
        createCurrency();
    }

    // Currency Related

    private void createCurrency() {
        getString("New Currency Name:", () -> {
            curName = textArea.getText();
            getSymbol();
        });
    }

    private void getSymbol() {
        getString("Symbol:", () -> {
            curSymbol = textArea.getText();
            getExchange();
        });
    }

    private void getExchange() {
        getString("Exchange Rate into USD:", () -> {
            curExchange = Double.parseDouble(textArea.getText());
            if (curExchange <= 0) {
                showMessageWindow("Error: Value must be positive");
                return;
            }
            Currency currency = new Currency(curName,curSymbol,curExchange);

            cons.accept(currency);
//            manager.addCurrency(currency);
//            createAccount();
        });
    }
}
