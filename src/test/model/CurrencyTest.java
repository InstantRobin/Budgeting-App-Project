package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CurrencyTest extends TestDefaults{
    final Currency test1 = new Currency("test","£",0.5);
    final Currency test2 = new Currency("test","£",0.5);
    final Currency test3 = new Currency("test3","$",2);

    @Test
    public void testUpdateExchangeRate() {
        test1.updateExchangeRate(2);
        assertEquals(2, test1.getExchangeRateUSD());
    }

    @Test
    public void equalsTestDifObject() {
        //noinspection AssertBetweenInconvertibleTypes
        assertNotEquals(test1,date);
    }

    @Test
    public void equalsTestSame() {
        assertEquals(test1, test2);
    }

    @Test
    public void equalsTestDifferent() {
        assertNotEquals(test1, test3);
    }
}
