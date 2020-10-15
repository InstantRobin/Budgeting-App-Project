package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LogEntryTest {

    Account acc = new Account("test",200);
    LogEntry log = new LogEntry(acc,200, acc.getBalance(), LocalDate.of(2020,10,12));

    @Test
    public void getTotalTest() {
        assertEquals(200,log.getTotal());
    }

    @Test
    public void getStringValTest() {
        LogEntry log = new LogEntry(acc,0, acc.getBalance(), LocalDate.of(2020,10,12));
        assertEquals("$0.00",log.getStringVal());

        log = new LogEntry(acc,1, acc.getBalance(), LocalDate.of(2020,10,12));
        assertEquals("$0.01",log.getStringVal());

        log = new LogEntry(acc,10, acc.getBalance(), LocalDate.of(2020,10,12));
        assertEquals("$0.10",log.getStringVal());

        log = new LogEntry(acc,11, acc.getBalance(), LocalDate.of(2020,10,12));
        assertEquals("$0.11",log.getStringVal());

        log = new LogEntry(acc,11, acc.getBalance(), LocalDate.of(2020,10,12));
        assertEquals("$0.11",log.getStringVal());

        log = new LogEntry(acc,20, acc.getBalance(), LocalDate.of(2020,10,12));
        assertEquals("$0.11",log.getStringVal());
    }

    @Test
    public void getStringTotalTest() {
        LogEntry log = new LogEntry(acc,200, 500, LocalDate.of(2020,10,12));
        assertEquals("$5.00",log.getStringTotal());
    }

    @Test
    public void getYearTest() {
        assertEquals(2020, log.getYear());
    }

    @Test
    public void getMonthTest() {
        assertEquals(10, log.getMonth());
    }

    @Test
    public void getDayTest() {
        assertEquals(12, log.getDay());
    }

    @Test
    public void getStringDate() {
        assertEquals("2020-10-12",log.getStringDate());
    }
}
