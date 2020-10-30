package persistence;
// based on CPSC 210 EdX JsonSerializationDemo

import model.Account;
import model.Currency;
import model.TestDefaults;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    // from CPSC 210 EdX JsonSerializationDemo
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void jsonWriterTestEmpty() {
        try {
            String dest = "./data/testWriterEmpty.json";
            List<Account> accountList = new ArrayList<>();

            JsonWriter jsonWriter = new JsonWriter(dest);
            jsonWriter.open();
            jsonWriter.write(accountList);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader(dest);
            List<Account> result = jsonReader.read();
            assertEquals(accountList,result);

        } catch (FileNotFoundException e){
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void jsonWriterTestGeneral() {
        try {
            String dest = "./data/testWriterGeneral.json";
            List<Account> accountList = new ArrayList<>();
            accountList.add(new Account("test1",10, TestDefaults.USD));
            accountList.add(new Account("test2",543, TestDefaults.USD));

            JsonWriter jsonWriter = new JsonWriter(dest);
            jsonWriter.open();
            jsonWriter.write(accountList);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader(dest);
            List<Account> result = jsonReader.read();
            JsonTest.checkAccount(accountList,result);

        } catch (FileNotFoundException e){
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }
}
