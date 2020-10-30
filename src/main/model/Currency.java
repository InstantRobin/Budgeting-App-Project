package model;


import org.json.JSONObject;
import persistence.Writable;

// Represents a form of currency with a name, symbol, and exchange rate of one unit into USD
public class Currency implements Writable {

    String name;
    String symbol;
    double exchangeRateUSD;

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

    // from CPSC 210 EdX JsonSerializationDemo
    // EFFECTS: Converts Currency into Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("symbol", symbol);
        json.put("exchangeRateUSD",exchangeRateUSD);
        return json;
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
}
