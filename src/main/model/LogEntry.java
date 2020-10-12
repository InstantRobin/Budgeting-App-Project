package model;
// Represents a log entry of an account value change

import java.util.Date;

public class LogEntry {

    private Account acc;
    private int val;
    private String date;
    private Boolean add; // True if money added, false if money subtracted
    // TODO: Add Currency

    public LogEntry(Account acc, int val, Boolean add, String date) {
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

    public String getDate() {
        return date;
    }

    public String getYear() {
        return date.substring(0,4);
    }

    public String getMonth() {
        return date.substring(5,7);
    }

    public String getDay() {
        return date.substring(8,10);
    }
}
