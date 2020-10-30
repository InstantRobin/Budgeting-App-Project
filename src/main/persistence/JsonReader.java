package persistence;

import model.Account;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.Currency;
import org.json.*;

// Represents a reader that reads a list of Accounts from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Account List from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Account> read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseAccountList(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: Parses Account List and returns it
    private List<Account> parseAccountList(JSONArray jsonArray) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            addAccount(accounts, (JSONObject) jsonArray.get(i));
        }
        return accounts;
    }

    // EFFECTS: Parses Account, adds it to given list
    private void addAccount(List<Account> accounts, JSONObject object) {
        String name = object.getString("name");
        int balance = object.getInt("balance");
        Currency currency = getCurrency(object.getJSONObject("currency"));
        Account acc = new Account(name,balance,currency);
        getHistory(acc,object.getJSONObject("history").getJSONArray("array"));

        accounts.add(acc);
    }

    // EFFECTS: Parses Currency, returns it
    private Currency getCurrency(JSONObject object) {
        String name = object.getString("name");
        String symbol = object.getString("symbol");
        double exchangeRateUSD = object.getDouble("exchangeRateUSD");
        return new Currency(name,symbol,exchangeRateUSD);
    }

    // EFFECTS: Parses History, adds to given account
    private void getHistory(Account acc,JSONArray array) {
        // https://mkyong.com/java8/java-8-how-to-convert-string-to-localdate/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Object object : array) {
            JSONObject obj = (JSONObject) object;
            int val = obj.getInt("val");
            LocalDate date = LocalDate.parse(obj.getString("date"),formatter);

            acc.logEvent(val,date);
        }
    }
}
