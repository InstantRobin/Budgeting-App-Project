package model;
// Represents a log entry of an account value change

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LogEntry implements Comparable<LogEntry> {

    private Account acc;
    private int val;
    private int total;
    private LocalDate date;
    // https://www.geeksforgeeks.org/localdate-format-method-in-java/
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // TODO: Add Currency

    public LogEntry(Account acc, int val, int total, LocalDate date) {
        this.acc = acc;
        this.val = val;
        this.total = total;
        this.date = date; // must be in YYYY-MM-DD format
    }

    // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date/33790426
    @Override
    public int compareTo(LogEntry o) {
        return getDate().compareTo(o.getDate());
    }

    public void updateTotal(int i) {
        total = i;
    }

    public Account getAcc() {
        return acc;
    }

    public int getVal() {
        return val;
    }

    public int getTotal() {
        return total;
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

    public String getStringMoney(int balance) {
        int before = ((balance - (balance % 100)) / 100);
        int after = balance % 100;

        if (after < 10) {
            return ("$" + before + "." + "0" + after);
        } else {
            return ("$" + before + "." + after);
        }
    }

    public String getStringVal() {
        return getStringMoney(val);
    }

    public String getStringTotal() {
        return getStringMoney(total);
    }

}
