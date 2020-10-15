package model;
// Represents the event history of an account

import java.util.ArrayList;
import java.util.Collections;

public class History extends ArrayList<LogEntry> {

    public History() {
        super();
    }

    // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date/33790426
    // EFFECT: Sorts the History List, because items can be added non-chronologically
    public void sort() {
        Collections.sort(this);
    }

    // EFFECT: Sorts history, then goes through chronologically to update the total values to be accurate
    public void updateTotals() {
        sort();
        for (int loc = 0; loc < this.size(); loc++) {
            LogEntry item = this.get(loc);
            if (loc == 0) {
                item.setTotal(item.getVal());
            } else {
                item.setTotal(this.get(loc - 1).getTotal() + item.getVal());
            }
        }
    }
}
