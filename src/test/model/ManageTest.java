package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Manage;

import static org.junit.jupiter.api.Assertions.*;

public class ManageTest {
    Account acc1 = new Account("test",0);
    Account acc2 = new Account("test",0);
    String date = "2020-10-12";

    @BeforeEach
    public void beforeEach(){
        acc1.subValue(acc1.getBalance(), date);
        acc2.subValue(acc2.getBalance(), date);
    }

    @Test
    public void moveMoneyTest(){
        acc1.addValue(1000, date);
        Manage.moveMoney(acc1,acc2,100,"2020-10-13");
        assertEquals(900,acc1.getBalance());
        assertEquals(100,acc2.getBalance());
    }
}
