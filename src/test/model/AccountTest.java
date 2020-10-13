package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account acc = new Account("test",0);
    String date = "2020-10-12";
    List<LogEntry> result;

    public void compareValues(Account account, List<LogEntry> res){

        List<LogEntry> history = account.getHistory();

        for (int ent = 0; ent < history.size(); ent++){
            assertEquals(res.get(ent).getAcc(), history.get(ent).getAcc());
            assertEquals(res.get(ent).getVal(), history.get(ent).getVal());
            assertEquals(res.get(ent).getAdd(), history.get(ent).getAdd());
            assertEquals(res.get(ent).getDate(), history.get(ent).getDate());
        }
    }

    public LogEntry newLogEntry(int val, Boolean add){
        return (new LogEntry(acc,val,add,date));
    }

    @Test
    public void addValueTest(){ //Checks if addValue adds value, logs properly
        acc.addValue(1000, date);

        List<LogEntry> result = new ArrayList<>();
        result.add(newLogEntry(1000,true));

        assertEquals(1000,acc.getBalance());
        compareValues(acc,result);
    }

    @Test
    public void subValueTest(){ //Checks if subValue subtracts value, logs properly
        acc.addValue(1000, date);
        acc.subValue(500, date);

        List<LogEntry> result = new ArrayList<>();
        result.add(newLogEntry(1000,true));
        result.add(newLogEntry(500,false));

        assertEquals(500,acc.getBalance());
        compareValues(acc,result);
    }

    @Test
    public void subValueNegativeTest(){ //Checks if subvalue can set to negative amount, logs properly
        acc.addValue(500, date);
        acc.subValue(1000, date);

        List<LogEntry> result = new ArrayList<>();
        result.add(newLogEntry(500,true));
        result.add(newLogEntry(1000,false));

        assertEquals(-500,acc.getBalance());
        compareValues(acc,result);
    }

    @Test
    public void logEventTest(){ //Checks if logEvent works properly with one event
        acc.logEvent(500,true, date);
        List<LogEntry> result = new ArrayList<>();
        result.add(newLogEntry(500,true));

        compareValues(acc,result);
    }

    @Test
    public void logEventTestMult(){ //Checks if logEvent works properly with multiple events
        acc.logEvent(700,true,date);
        acc.logEvent(900,false,date);

        List<LogEntry> result = new ArrayList<>();
        result.add(newLogEntry(700,true));
        result.add(newLogEntry(900,false));

        compareValues(acc,result);
    }
}