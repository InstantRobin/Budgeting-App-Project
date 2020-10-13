package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void addValueTest(){ //Checks if addValue adds value, logs properly
        acc.addValue(1000, date);

        List<LogEntry> result = new ArrayList<>();
        result.add(new LogEntry(acc,1000,true, date));

        assertEquals(1000,acc.getBalance());
        compareValues(acc,result);
    }

    @Test
    public void subValueTest(){ //Checks if subValue subtracts value, logs properly
        acc.addValue(1000, date);
        acc.subValue(500, date);

        List<LogEntry> result = new ArrayList<>();
        result.add(new LogEntry(acc,1000,true, date));
        result.add(new LogEntry(acc,500,false, date));

        assertEquals(500,acc.getBalance());
        compareValues(acc,result);
    }

    @Test
    public void subValueNegativeTest(){
        acc.addValue(500, date);
        acc.subValue(1000, date);

        List<LogEntry> result = new ArrayList<>();
        result.add(new LogEntry(acc,500,true, date));
        result.add(new LogEntry(acc,1000,false, date));

        assertEquals(-500,acc.getBalance());
        compareValues(acc,result);
    }

    @Test
    public void logEventTest(){
        acc.logEvent(500,true, date);
        List<LogEntry> result = new ArrayList<>();
        result.add(new LogEntry(acc,500,true, date));

        compareValues(acc,result);
    }

    @Test
    public void logEventTestMult(){
        acc.logEvent(700,true,date);
        acc.logEvent(900,false,date);

        List<LogEntry> result = new ArrayList<>();
        result.add(new LogEntry(acc,700,true, date));
        result.add(new LogEntry(acc,900,false, date));

        compareValues(acc,result);
    }
}