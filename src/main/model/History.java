package model;
// Represents the event history of an account

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class History {

    private ArrayList<LogEntry> history = new ArrayList<>();

    public History() {
        super();
    }

    // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date/33790426
    // EFFECT: Sorts the History List, because items can be added non-chronologically
    public void sort() {
        Collections.sort(history);
    }

    // EFFECT: Sorts history, then goes through chronologically to update the total values to be accurate
    public void updateTotals() {
        sort();
        for (int loc = 0; loc < history.size(); loc++) {
            LogEntry item = history.get(loc);
            if (loc == 0) {
                item.setTotal(item.getVal());
            } else {
                item.setTotal(history.get(loc - 1).getTotal() + item.getVal());
            }
        }
    }

    // https://stackoverflow.com/questions/56357708/joda-localdate-compare-if-equal-or-before-and-after
    // EFFECT: Returns all entries in a given date range
    public History getDateRange(LocalDate start, LocalDate end) {
        History dateRange = new History();
        sort();
        for (LogEntry entry : history) {
            if (!entry.getDate().isAfter(start)) {
                continue;
            } else if (!entry.getDate().isAfter(end)) {
                dateRange.add(entry);
            } else {
                break;
            }
        }
        return dateRange;
    }

    public void add(LogEntry ent) {
        history.add(ent);
    }

    public LogEntry get(int i) {
        return history.get(i);
    }

    public int size() {
        return history.size();
    }
}
