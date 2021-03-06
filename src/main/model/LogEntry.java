package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Represents a log entry of an account changing in value
public class LogEntry implements Comparable<LogEntry>, Writable {

    private final int val;
    private int total;
    private final LocalDate date;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Modifies: this
    // EFFECTS: Initializes LogEntry with variables as explained below
    public LogEntry(int val, int total, LocalDate date) {
        this.val = val; // Value of the change (pos for deposit, negative for withdrawal
        this.total = total; // The total money in the account at the time of the event
        this.date = date; // The date of the event
    }

    //// SOURCE: ////
    // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date/33790426
    // Implementation of sort, Comparable across History and LogEntry classes based on the structure provided by Domchi
    // EFFECT: Allows comparing two LogEntries to quickly determine which comes before which, useful for sorting
    @Override
    public int compareTo(LogEntry o) {
        return getDate().compareTo(o.getDate());
    }

    public void setTotal(int i) {
        total = i;
    }

    // Getters

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

    //// SOURCE: ////
    // Save / Load System file structure based on example system JsonSerializationDemo provided by UBC CPSC 210 course
    // Adapted from an example fn given in the source

    // EFFECTS: Converts LogEntry into Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("val", val);
        json.put("total",total);
        json.put("date", date);
        return json;
    }
}
