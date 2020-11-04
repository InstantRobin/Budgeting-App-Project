package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.Actions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionsTest extends TestDefaults{
    private Account acc1;
    private Account acc2;
    private Account acc3;
    private History history = new History();

    @BeforeEach
    public void beforeEach() {
        acc1 = new Account("acc1",5000,USD);
        acc2 = new Account("acc2",1000,USD);
        acc3 = new Account("acc2",1000,RMB);
    }

    @Test
    public void depositTest() {
        deposit(acc1,500,date);
        assertEquals(5500, acc1.getBalance());

        assertEquals(history,acc1.getHistory());
    }

    @Test
    public void withdrawTest() {
        withdraw(acc1,500,date);
        assertEquals(4500, acc1.getBalance());
    }

    @Test
    public void transferTestSameCurrency() {
        transfer(acc1, acc2, 500,date);
        assertEquals(4500, acc1.getBalance());
        assertEquals(1500, acc2.getBalance());
    }
}
