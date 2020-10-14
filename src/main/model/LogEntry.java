package model;
// Represents a log entry of an account value change

import java.time.LocalDate;
import java.util.Date;

public class LogEntry {

    private Account acc;
    private int val;
    private LocalDate date;
    private Boolean add; // True if money added, false if money subtracted
    // TODO: Add Currency

    public LogEntry(Account acc, int val, Boolean add, LocalDate date) {
        this.acc = acc;
        this.val = val;
        this.add = add;
        this.date = date; // must be in YYYY-MM-DD format
    }

    public Account getAcc() {
        return acc;
    }

    public int getVal() {
        return val;
    }

    public Boolean getAdd() {
        return add;
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
}
