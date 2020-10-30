package persistence;
// based on CPSC 210 EdX JsonSerializationDemo

import model.Account;
import model.Currency;
import model.History;
import model.LogEntry;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonTest {
    public static void checkAccount(List<Account> expected, List<Account> result) {
        for (int i = 0; i < expected.size(); i++) {
            Account exp = expected.get(i);
            Account res = result.get(i);
            assertEquals(exp.getName(),res.getName());
            assertEquals(exp.getBalance(),res.getBalance());
            compareCurrency(exp.getCurrency(),res.getCurrency());
            compareHistory(exp.getHistory(),res.getHistory());
        }
    }

    private static void compareCurrency(Currency expected, Currency result) {
        assertEquals(expected.getName(),result.getName());
        assertEquals(expected.getSymbol(),result.getSymbol());
        assertEquals(expected.getExchangeRateUSD(),result.getExchangeRateUSD());
    }

    private static void compareHistory(History expected, History result) {
        if (expected.size() != result.size()) {
            fail("Histories do not match");
        } else {
            for (int i = 0; i < expected.size(); i++) {
                LogEntry expEntry = expected.get(i);
                LogEntry resEntry = result.get(i);

                assertEquals(expEntry.getVal(),resEntry.getVal());
                assertEquals(expEntry.getDate(),resEntry.getDate());
            }
        }
    }
}

