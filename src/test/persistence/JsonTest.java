package persistence;
// based on CPSC 210 EdX JsonSerializationDemo

import model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonTest {
    Account account2 = new Account("test2",543, TestDefaults.USD);
    Account account1 = new Account("test1",10, TestDefaults.USD);

    public void setAccounts() {
        account2.logEvent(50, LocalDate.of(2020, 10, 29));
        account2.logEvent(80, LocalDate.of(2020, 11, 19));
        account1.logEvent(-60, LocalDate.of(2019, 9, 15));
        account1.logEvent(230, LocalDate.of(2020, 2, 12));
    }

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
            fail("History sizes do not match");
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

