package model;

import java.time.LocalDate;

public class TestDefaults {
    public static Currency USD = new Currency("USD","$",1);
    public static Currency RMB = new Currency("RMB","¥",0.15);
    public static LocalDate date = LocalDate.of(2020,10,12);
}
