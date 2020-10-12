package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogEntryTest {

    Account acc = new Account("test",0);
    LogEntry log = new LogEntry(acc,200,true,"2020-10-12");

    @Test
    public void getYearTest() {
        assertEquals("2020", log.getYear());
    }

    @Test
    public void getMonthTest() {
        assertEquals("10", log.getMonth());
    }

    @Test
    public void getDayTest() {
        assertEquals("12", log.getDay());
    }
}
