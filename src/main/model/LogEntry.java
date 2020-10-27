package model;
// Represents a log entry of an account value change event

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LogEntry implements Comparable<LogEntry>, Writable {

    private String acc; // Name of the Account
    private int val;
    private int total;
    private LocalDate date;
    // https://www.geeksforgeeks.org/localdate-format-method-in-java/
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // TODO: Add Currency

    // REQUIRES: Initializes LogEntry
    public LogEntry(Account acc, int val, int total, LocalDate date) {
        this.acc = acc.getName(); // Account of where even occurred
        this.val = val; // Value of the change (pos for deposit, negative for widthdrawl
        this.total = total; // The total money in the account at the time of the event
        this.date = date; // The date of the event
    }

    // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date/33790426
    // EFFECT: Allows comparing two LogEntries to quickly determine which comes before which, useful for sorting
    @Override
    public int compareTo(LogEntry o) {
        return getDate().compareTo(o.getDate());
    }

    public void setTotal(int i) {
        total = i;
    }

    // from CPSC 210 EdX JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("account",acc);
        json.put("val", val);
        json.put("total",total);
        json.put("date", date);
        return json;
    }

    // Getters

    public String getAcc() {
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

    // EFFECT: returns Date in YYYY-MM-DD String format
    public String getStringDate() {
        return (date.format(formatter));
    }
}
