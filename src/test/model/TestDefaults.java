package model;

import java.time.LocalDate;

public class TestDefaults {
    public static final Currency USD = new Currency("USD","$",1);
    public static final Currency RMB = new Currency("RMB","¥",0.15);
    public static final LocalDate date = LocalDate.of(2020,10,12);
}
