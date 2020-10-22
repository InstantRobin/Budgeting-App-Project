package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryTest {
    History hist = new History();
    Account acc = new Account("test",0, TestDefaults.USD);
    LocalDate date = LocalDate.of(2020,10,12);
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
    public void getDateRangeTest() {
        LocalDate date1 = LocalDate.of(2018,5,17);
        LocalDate date2 = LocalDate.of(2019,3,17);
        LocalDate date3 = LocalDate.of(2019,3,18);
        LocalDate date4 = LocalDate.of(2019,3,19);
        LocalDate date5 = LocalDate.of(2019,3,21);

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
}
