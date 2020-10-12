package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account acc = new Account("test",0);

    @BeforeEach
    public void beforeEach(){
        acc.subValue(acc.getBalance());
    }

    @Test
    public void addValueTest(){
        acc.addValue(1000);
        assertEquals(1000,acc.getBalance());
    }

    @Test
    public void subValueTest(){
        acc.addValue(1000);
        acc.subValue(500);
        assertEquals(500,acc.getBalance());
    }

    @Test
    public void subValueNegativeTest(){
        acc.addValue(500);
        acc.subValue(1000);
        assertEquals(-500,acc.getBalance());
    }
}