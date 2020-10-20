package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryTest {
    History hist = new History();
    Account acc = new Account("test",0, TestDefaults.USD);
    LocalDate date = LocalDate.of(2020,10,12);

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

    @Test
    public void sortHistoryTestBackwards() {
        hist.add(logEvent(500, LocalDate.of(2020,5,20)));
        hist.add(logEvent(800,LocalDate.of(2020,5,19)));
        hist.sort();

        List<LogEntry> result = new ArrayList<>();
        result.add(new LogEntry(acc,800, acc.getBalance(),LocalDate.of(2020,5,19)));
        result.add(new LogEntry(acc,500, acc.getBalance(),LocalDate.of(2020,5,20)));

        compareValues(hist,result);
    }

    @Test
    public void sortHistoryTestForwards() {
        hist.add(logEvent(800,LocalDate.of(2020,5,19)));
        hist.add(logEvent(500,LocalDate.of(2020,5,20)));
        hist.sort();

        List<LogEntry> result = new ArrayList<>();
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

        List<LogEntry> result = new ArrayList<>();
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

        List<LogEntry> result = new ArrayList<>();
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

        List<LogEntry> result = new ArrayList<>();
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

        List<LogEntry> result = new ArrayList<>();
        result.add(new LogEntry(acc,200,200,LocalDate.of(2018,5,17)));
        result.add(new LogEntry(acc,-500,-300,LocalDate.of(2019,3,19)));
        result.add(new LogEntry(acc,800,500,LocalDate.of(2020,2,15)));

        compareValues(hist,result);
    }
}
