package model;
// Represents a form of currency

public class Currency {

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
