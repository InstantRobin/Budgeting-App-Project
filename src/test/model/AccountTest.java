package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account acc = new Account("test",0);
    LocalDate date = LocalDate.of(2020,10,12);

    public void compareValues(Account account, List<LogEntry> result){

        List<LogEntry> history = account.getHistory();

        for (int ent = 0; ent < history.size(); ent++){
            assertEquals(result.get(ent).getAcc(), history.get(ent).getAcc());
            assertEquals(result.get(ent).getVal(), history.get(ent).getVal());
            assertEquals(result.get(ent).getTotal(), history.get(ent).getTotal());
            assertEquals(result.get(ent).getDate(), history.get(ent).getDate());
        }
    }

    public LogEntry newLogEntry(int val, int total){
        return (new LogEntry(acc,val,total,date));
    }


    @Test
    public void getNameTest() {
        assertEquals("test",acc.getName());
    }

    @Test
    public void addValueTest(){ //Checks if addValue adds value, logs properly
        acc.addValue(1000, date);

        List<LogEntry> result = new ArrayList<>();
        result.add(newLogEntry(1000,1000));

        assertEquals(1000,acc.getBalance());
        compareValues(acc,result);
    }

    @Test
    public void subValueTest(){ //Checks if subValue subtracts value, logs properly
        acc.addValue(1000, date);
        acc.subValue(500, date);

        List<LogEntry> result = new ArrayList<>();
        result.add(newLogEntry(1000,1000));
        result.add(newLogEntry(-500,500));

        assertEquals(500,acc.getBalance());
        compareValues(acc,result);
    }

    @Test
    public void subValueNegativeTest(){ //Checks if subvalue can set to negative amount, logs properly
        acc.addValue(500, date);
        acc.subValue(1000, date);

        List<LogEntry> result = new ArrayList<>();
        result.add(newLogEntry(500,500));
        result.add(newLogEntry(-1000,-500));

        assertEquals(-500,acc.getBalance());
        compareValues(acc,result);
    }

    @Test
    public void logEventTest(){ //Checks if logEvent works properly with one event
        acc.logEvent(500, date);
        List<LogEntry> result = new ArrayList<>();
        result.add(newLogEntry(500,0));

        compareValues(acc,result);
    }

    @Test
    public void logEventTestMult(){ //Checks if logEvent works properly with multiple events
        acc.logEvent(700, date);
        acc.logEvent(-900, date);

        List<LogEntry> result = new ArrayList<>();
        result.add(newLogEntry(700,0));
        result.add(newLogEntry(-900,0));

        compareValues(acc,result);
    }



    @Test
    public void getStringBalanceTest() { // Checks to make sure returns properly for nums in all decimal places
        assertEquals("$0.00", acc.getStringBalance());

        acc.addValue(1, date);
        assertEquals("$0.01", acc.getStringBalance());

        acc.addValue(9, date);
        assertEquals("$0.10", acc.getStringBalance());

        acc.addValue(1, date);
        assertEquals("$0.11", acc.getStringBalance());

        acc.addValue(89, date);
        assertEquals("$1.00", acc.getStringBalance());

        acc.addValue(1, date);
        assertEquals("$1.01", acc.getStringBalance());

        acc.addValue(9, date);
        assertEquals("$1.10", acc.getStringBalance());

        acc.addValue(1, date);
        assertEquals("$1.11", acc.getStringBalance());

        acc.addValue(1000, date);
        assertEquals("$11.11", acc.getStringBalance());
    }

    @Test
    public void getStringBalanceNegativeTest() { // Checks to make sure returns properly for nums in all decimal places
        acc.subValue(100,date);
        assertEquals("$-1.00", acc.getStringBalance());

        acc.subValue(25,date);
        assertEquals("$-1.25", acc.getStringBalance());
    }
}