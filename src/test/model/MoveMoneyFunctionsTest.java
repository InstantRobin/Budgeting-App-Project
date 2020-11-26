package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static model.MoveMoneyFunctions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveMoneyFunctionsTest extends TestDefaults{
    private Account acc1;
    private Account acc2;
    private Account acc3;
    private History history = new History();

    @BeforeEach
    public void beforeEach() {
        acc1 = new Account("acc1",5000,USD);
        acc2 = new Account("acc2",1000,USD);
        acc3 = new Account("acc3",2000,RMB);
    }

    @Test
    public void depositTest() {
        deposit(acc1,500,date);
        assertEquals(5500, acc1.getBalance());

        history.add(new LogEntry(500,5500,date));
        assertEquals(history,acc1.getHistory());
    }

    @Test
    public void withdrawTest() {
        withdraw(acc1,500,date);
        assertEquals(4500, acc1.getBalance());

        history.add(new LogEntry(-500,4500,date));
        assertEquals(history,acc1.getHistory());
    }

    @Test
    public void transferTestSameCurrency() {
        transfer(acc1, acc2, 500,date);
        assertEquals(4500, acc1.getBalance());
        assertEquals(1500, acc2.getBalance());

        history.add(new LogEntry(-500,4500,date));
        assertEquals(history,acc1.getHistory());

        history = new History();
        history.add(new LogEntry(500,1500,date));
        assertEquals(history,acc2.getHistory());
    }

    @Test
    public void transferTestDifferentCurrencyFromUSD() {
        transfer(acc1, acc3, 500,date);
        int newAmount = (int) (500 / RMB.getExchangeRateUSD());
        assertEquals(4500, acc1.getBalance());
        assertEquals(2000 + newAmount , acc3.getBalance());

        history.add(new LogEntry(-500,4500,date));
        assertEquals(history,acc1.getHistory());

        history = new History();
        history.add(new LogEntry(newAmount,2000 + newAmount,date));
        assertEquals(history,acc3.getHistory());
    }

    @Test
    public void transferTestDifferentCurrencyToUSD() {
        transfer(acc3, acc1, 500,date);
        int newAmount = (int) (500 * RMB.getExchangeRateUSD());
        assertEquals(5000 + newAmount, acc1.getBalance());
        assertEquals(1500, acc3.getBalance());

        history.add(new LogEntry(newAmount,5000 + newAmount,date));
        assertEquals(history,acc1.getHistory());

        history = new History();
        history.add(new LogEntry(-500,1500,date));
        assertEquals(history,acc3.getHistory());
    }

    @Test
    public void buildDataTestEmpty() {
        assertEquals(history,buildData(acc1));
    }


    @Test
    public void buildDataTest() {
        LocalDate date1 = LocalDate.of(2020,8,15);
        LocalDate date2 = LocalDate.of(2020,8,18);
        LocalDate date3 = LocalDate.of(2020,8,20);
        deposit(acc1,50, date1);
        withdraw(acc1,20, date2);
        withdraw(acc1,35, date3);

        history.add(new LogEntry(50,50,date1));
        history.add(new LogEntry(0,50,date1.plusDays(1)));
        history.add(new LogEntry(0,50,date1.plusDays(2)));
        history.add(new LogEntry(-20,30,date2));
        history.add(new LogEntry(0,30,date2.plusDays(1)));
        history.add(new LogEntry(-35,-5,date3));
        History result = buildData(acc1);
        assertEquals(history,result);
    }

    @Test
    public void buildDataTestDuplicateDate() {
        LocalDate date1 = LocalDate.of(2020,8,15);
        LocalDate date2 = LocalDate.of(2020,8,18);
        deposit(acc1,50, date1);
        withdraw(acc1,20, date2);
        withdraw(acc1,35, date2);

        history.add(new LogEntry(50,50,date1));
        history.add(new LogEntry(0,50,date1.plusDays(1)));
        history.add(new LogEntry(0,50,date1.plusDays(2)));
        history.add(new LogEntry(-55,-5,date2));

        assertEquals(history,buildData(acc1));
    }
}
