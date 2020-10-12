package model;
// Represents a log entry of an account value change

import java.util.Date;

public class LogEntry {

    Account acc;
    int val;
    String date;
    // TODO: Add Currency

    public LogEntry(Account acc, int val, String date) {
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
