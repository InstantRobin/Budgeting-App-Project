package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

// Represents the event history of all actions upon an account
public class History implements Writable {

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
            if (entry.getDate().isBefore(start)) {
                continue;
            } else if (!entry.getDate().isAfter(end)) {
                dateRange.add(entry);
            } else {
                break;
            }
        }
        return dateRange;
    }

    // from CPSC 210 EdX JsonSerializationDemo
    // EFFECTS: Converts History into a JsonArray
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("array",logEntriesToJson());
        return json;
    }

    // from CPSC 210 EdX JsonSerializationDemo
    // EFFECTS: Converts internal history into a JsonArray
    private JSONArray logEntriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (LogEntry entry : history) {
            jsonArray.put(entry.toJson());
        }

        return jsonArray;
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
