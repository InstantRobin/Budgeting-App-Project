package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account acc = new Account("test",0);
    String date = "2020-10-12";

    @BeforeEach
    public void beforeEach(){
        acc.subValue(acc.getBalance(), date);
    }

    @Test
    public void addValueTest(){
        acc.addValue(1000, date);
        assertEquals(1000,acc.getBalance());
    }

    @Test
    public void subValueTest(){
        acc.addValue(1000, date);
        acc.subValue(500, date);
        assertEquals(500,acc.getBalance());
    }

    @Test
    public void subValueNegativeTest(){
        acc.addValue(500, date);
        acc.subValue(1000, date);
        assertEquals(-500,acc.getBalance());
    }
}