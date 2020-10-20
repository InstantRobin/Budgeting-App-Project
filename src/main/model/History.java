package model;
// Represents the event history of an account

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
