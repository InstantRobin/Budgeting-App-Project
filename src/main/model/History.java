package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

// Represents the event history of all actions upon an account
public class History implements Writable, Iterable<LogEntry> {

    private final ArrayList<LogEntry> history = new ArrayList<>();

    public History() {
    }

    //// SOURCE: ////
    // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date/33790426
    // Implementation of sort, Comparable across History and LogEntry classes based on the structure provided by Domchi

    // EFFECTS: Sorts the History List, because items can be added non-chronologically
    public void sort() {
        Collections.sort(history);
    }

    // EFFECTS: Sorts history, then goes through chronologically to update the total values to be accurate
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

    //// SOURCE: ////
    // https://stackoverflow.com/questions/56357708/joda-localdate-compare-if-equal-or-before-and-after
    // Got before-or-equal structure for first else if from Borgy Manotoy's response

    // EFFECTS: Returns all entries in a given date range
    public History getDateRange(LocalDate start, LocalDate end) {
        History dateRange = new History();
        sort();
        for (LogEntry entry : history) {
            if (entry.getDate().isBefore(start)) {
                //noinspection UnnecessaryContinue
                continue;
            } else if (!entry.getDate().isAfter(end)) {
                dateRange.add(entry);
            } else {
                break;
            }
        }
        return dateRange;
    }

    // MODIFIES: history
    // EFFECTS: Adds the given logEntry to the history
    public void add(LogEntry ent) {
        history.add(ent);
    }

    // MODIFIES: history
    // EFFECTS: Removes the given logEntry index from the history
    public void remove(int loc) {
        history.remove(loc);
    }

    // EFFECTS: Fetches the logEntry at a given index
    public LogEntry get(int i) {
        return history.get(i);
    }

    // EFFECTS: Returns the the number of entries in the history
    public int size() {
        return history.size();
    }

    //// SOURCE: ////
    // Save / Load System file structure based on example system JsonSerializationDemo provided by UBC CPSC 210 course
    // Adapted from an example fn given in the source

    // EFFECTS: Converts the History object into a Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("array",logEntriesToJson());
        return json;
    }

    // EFFECTS: Goes through the list of log entries, and turns them into a JSON array
    private JSONArray logEntriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (LogEntry entry : history) {
            jsonArray.put(entry.toJson());
        }

        return jsonArray;
    }

    //// SOURCE: ////
    // https://stackoverflow.com/questions/46444855/checking-if-arraylist-contains-an-object-with-an-attribute
    // Implementation based on example in response given by AxelH
    // EFFECTS: Returns false if not a History, are dif sizes, or if any of entries are different
    //          Otherwise returns true
    @Override
    public boolean equals(Object o) {
        if (o instanceof History) {
            History history1 = (History) o;
            if (history.size() != history1.size()) {
                return false;
            }
            for (int entry = 0; entry < history.size(); entry++) {
                if ((!(history1.get(entry).getAcc().equals(history.get(entry).getAcc()))
                        || (history1.get(entry).getVal() != history.get(entry).getVal())
                        || (!history1.get(entry).getDate().equals(history.get(entry).getDate())))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: Allows this class to be iterated, going through each item in the history array
    @Override
    public Iterator<LogEntry> iterator() {
        return history.iterator();
    }
}
