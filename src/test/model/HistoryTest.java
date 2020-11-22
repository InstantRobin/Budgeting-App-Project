package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryTest extends TestDefaults{
    final History hist = new History();
    final History hist2 = new History();
    final Account acc = new Account("test",0, USD);
    List<LogEntry> result;

    public LogEntry logEvent(int val, LocalDate date){
        return (new LogEntry(acc,val,0,date));
    }

    public void compareValues(History history, List<LogEntry> result){
        for (int entry = 0; entry < history.size(); entry++){
            assertEquals(result.get(entry).getAcc(), history.get(entry).getAcc());
            assertEquals(result.get(entry).getVal(), history.get(entry).getVal());
            assertEquals(result.get(entry).getTotal(), history.get(entry).getTotal());
            assertEquals(result.get(entry).getDate(), history.get(entry).getDate());
        }
    }

    @BeforeEach
    public void beforeEach() {
        result = new ArrayList<>();
    }


    @Test
    public void sortHistoryTestBackwards() {
        hist.add(logEvent(500, LocalDate.of(2020,5,20)));
        hist.add(logEvent(800,LocalDate.of(2020,5,19)));
        hist.sort();

        result.add(new LogEntry(acc,800, acc.getBalance(),LocalDate.of(2020,5,19)));
        result.add(new LogEntry(acc,500, acc.getBalance(),LocalDate.of(2020,5,20)));

        compareValues(hist,result);
    }

    @Test
    public void sortHistoryTestForwards() {
        hist.add(logEvent(800,LocalDate.of(2020,5,19)));
        hist.add(logEvent(500,LocalDate.of(2020,5,20)));
        hist.sort();

        result.add(new LogEntry(acc,800, acc.getBalance(),LocalDate.of(2020,5,19)));
        result.add(new LogEntry(acc,500, acc.getBalance(),LocalDate.of(2020,5,20)));

        compareValues(hist,result);
    }

    @Test
    public void sortHistoryTestTriple() {
        hist.add(logEvent(200,LocalDate.of(2020,5,17)));
        hist.add(logEvent(800,LocalDate.of(2020,5,19)));
        hist.add(logEvent(500,LocalDate.of(2020,5,18)));
        hist.sort();

        result.add(new LogEntry(acc,200, acc.getBalance(),LocalDate.of(2020,5,17)));
        result.add(new LogEntry(acc,500, acc.getBalance(),LocalDate.of(2020,5,18)));
        result.add(new LogEntry(acc,800, acc.getBalance(),LocalDate.of(2020,5,19)));

        compareValues(hist,result);
    }

    @Test
    public void sortHistoryTestMonths() {
        hist.add(logEvent(200,LocalDate.of(2020,2,17)));
        hist.add(logEvent(800,LocalDate.of(2020,4,15)));
        hist.add(logEvent(500,LocalDate.of(2020,3,19)));
        hist.sort();

        result.add(new LogEntry(acc,200, acc.getBalance(),LocalDate.of(2020,2,17)));
        result.add(new LogEntry(acc,500, acc.getBalance(),LocalDate.of(2020,3,19)));
        result.add(new LogEntry(acc,800, acc.getBalance(),LocalDate.of(2020,4,15)));

        compareValues(hist,result);
    }

    @Test
    public void sortHistoryTestYears() {
        hist.add(logEvent(200,LocalDate.of(2018,5,17)));
        hist.add(logEvent(800,LocalDate.of(2020,2,15)));
        hist.add(logEvent(500,LocalDate.of(2019,3,19)));
        hist.sort();

        result.add(new LogEntry(acc,200, acc.getBalance(),LocalDate.of(2018,5,17)));
        result.add(new LogEntry(acc,500, acc.getBalance(),LocalDate.of(2019,3,19)));
        result.add(new LogEntry(acc,800, acc.getBalance(),LocalDate.of(2020,2,15)));

        compareValues(hist,result);
    }

    @Test
    public void updateHistoryTotalsTest() {
        hist.add(logEvent(200,LocalDate.of(2018,5,17)));
        hist.add(logEvent(800,LocalDate.of(2020,2,15)));
        hist.add(logEvent(-500,LocalDate.of(2019,3,19)));
        hist.updateTotals();

        result.add(new LogEntry(acc,200,200,LocalDate.of(2018,5,17)));
        result.add(new LogEntry(acc,-500,-300,LocalDate.of(2019,3,19)));
        result.add(new LogEntry(acc,800,500,LocalDate.of(2020,2,15)));

        compareValues(hist,result);
    }

    @Test
    public void equalsTestDifObject() {
        //noinspection AssertBetweenInconvertibleTypes
        assertNotEquals(hist2,date);
    }

    @Test
    public void equalsTestEmpty() {
        assertEquals(hist, hist2);
    }

    @Test
    public void equalsTestSame() {
        Account acc2 = new Account("test2",1200,USD);
        LocalDate date2 = LocalDate.of(2020,1,1);

        hist.add(new LogEntry(acc,500,500,date2));
        hist2.add(new LogEntry(acc,500,500,date2));
        assertEquals(hist, hist2);

        hist.add(new LogEntry(acc2,1400,1900,date));
        hist2.add(new LogEntry(acc2,1400,1900,date));
        assertEquals(hist, hist2);
    }

    @Test
    public void equalsTestDifSize() {
        hist.add(new LogEntry(acc,500,500,date));
        assertNotEquals(hist, hist2);

        hist.add(new LogEntry(acc,500,500,date));
        hist2.add(new LogEntry(acc,500,500,date));
        assertNotEquals(hist, hist2);
    }

    @Test
    public void equalsTestDifAccounts() {
        Account acc2 = new Account("test2",1200,USD);

        hist.add(new LogEntry(acc,500,100,date));
        hist2.add(new LogEntry(acc2,500,100,date));
        assertNotEquals(hist,hist2);
    }

    @Test
    public void equalsTestDifVal() {
        hist.add(new LogEntry(acc,500,100,date));
        hist2.add(new LogEntry(acc,400,100,date));
        assertNotEquals(hist,hist2);
    }

    @Test
    public void equalsTestDifDate() {
        LocalDate date2 = LocalDate.of(2020,1,1);

        hist.add(new LogEntry(acc,500,100,date));
        hist2.add(new LogEntry(acc,500,100,date2));
        assertNotEquals(hist,hist2);
    }

    @Test
    public void getDateRangeTest() {
        LocalDate date1 = LocalDate.of(2018,5,17);
        LocalDate date2 = LocalDate.of(2019,3,17);
        LocalDate date3 = LocalDate.of(2019,3,18);
        LocalDate date4 = LocalDate.of(2019,3,19);
        LocalDate date5 = LocalDate.of(2019,3,21);

        compareValues(hist.getDateRange(LocalDate.of(2018,4,16),
                LocalDate.of(2018,5,16)),
                result);

        hist.add(logEvent(200,date1));
        hist.add(logEvent(-300,date2));
        hist.add(logEvent(-400,date3));
        hist.add(logEvent(500,date4));
        hist.add(logEvent(-600,date5));

        compareValues(hist.getDateRange(LocalDate.of(2018,4,16),
                LocalDate.of(2018,5,16)),
                result);

        result.add(logEvent(200,date1));

        compareValues(hist.getDateRange(date1,
                date2.minusDays(1)),
                result);

        result.add(logEvent(-300,date2));

        compareValues(hist.getDateRange(date1,
                                        date2),
                    result);

        result.clear();

        result.add(logEvent(-300,date2));
        result.add(logEvent(-400,date3));
        result.add(logEvent(500,date4));

        compareValues(hist.getDateRange(date2,
                date4),
                result);
    }

    @Test
    public void iterableTest() {
        int i = 0;
        hist.add(logEvent(200,date));
        hist.add(logEvent(-300,date));
        hist.add(logEvent(-400,date));
        hist.add(logEvent(500,date));
        hist.add(logEvent(-600,date));
        for (LogEntry entry : hist) {
            assertEquals(entry,hist.get(i));
            i += 1;
        }
    }
}
