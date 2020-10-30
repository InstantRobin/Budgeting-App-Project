package persistence;
// from CPSC 210 EdX JsonSerializationDemo

import model.Account;
import org.json.JSONArray;

import java.io.*;
import java.util.List;

// Represents a writer that writes JSON representation of a List of Accounts to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Account List to file
    public void write(List<Account> accounts) {
        JSONArray jsonArray = new JSONArray();
        for (Account acc : accounts) {
            jsonArray.put(acc.toJson());
            System.out.println("Saved " + acc.getName());
        }
        saveToFile(jsonArray.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
