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
    public void getStringValTest(){
        // TODO
    }

    @Test
    public void getStringTotalTest() {
        // TODO
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
