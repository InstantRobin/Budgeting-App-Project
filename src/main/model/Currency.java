package model;


import org.json.JSONObject;
import persistence.Writable;

// Represents a form of currency with a name, symbol, and exchange rate of one unit into USD
public class Currency implements Writable {

    private final String name;
    private final String symbol;
    private double exchangeRateUSD;

    // EFFECTS: Initializes a currency with a given name, symbol, and exchange rate of one unit into USD
    public Currency(String name, String symbol, double exchangeRateUSD) {
        this.name = name;
        this.symbol = symbol;
        this.exchangeRateUSD = exchangeRateUSD;
    }

    // REQUIRES: newRate > 0
    // MODIFIES: this.exchangeRateUSD
    // EFFECTS: Sets exchangeRateUSD to new given value to reflect currency ratio shifts
    public void updateExchangeRate(double newRate) {
        this.exchangeRateUSD = newRate;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getExchangeRateUSD() {
        return exchangeRateUSD;
    }

    //// SOURCE: ////
    // Save / Load System file structure based on example system JsonSerializationDemo provided by UBC CPSC 210 course
    // Adapted from the example fn given in the source
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("symbol", symbol);
        json.put("exchangeRateUSD",exchangeRateUSD);
        return json;
    }

    //// SOURCE: ////
    // https://stackoverflow.com/questions/46444855/checking-if-arraylist-contains-an-object-with-an-attribute
    // Implementation fully sourced from AxelH's response
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Currency) {
            Currency currency = (Currency) obj;
            return this.name.equals(currency.name);
        } else {
            return false;
        }
    }
}
