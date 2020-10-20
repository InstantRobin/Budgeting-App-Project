package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyTest {
    Currency test1 = new Currency("test","£",0.5);

    @Test
    public void testUpdateExchangeRate() {
        test1.updateExchangeRate(2);
        assertEquals(2, test1.getExchangeRateUSD());
    }
}
