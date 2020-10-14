package model;
// Represents a log entry of an account value change

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LogEntry {

    private Account acc;
    private int val;
    private LocalDate date;
    // https://www.geeksforgeeks.org/localdate-format-method-in-java/
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // TODO: Add Currency

    public LogEntry(Account acc, int val, LocalDate date) {
        this.acc = acc;
        this.val = val;
        this.date = date; // must be in YYYY-MM-DD format
    }

    public Account getAcc() {
        return acc;
    }

    public int getVal() {
        return val;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public String getStringDate() {
        return (date.format(formatter));
    }
}
