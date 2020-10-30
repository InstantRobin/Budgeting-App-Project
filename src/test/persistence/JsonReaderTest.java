package persistence;
// based on CPSC 210 EdX JsonSerializationDemo

import model.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderInvalidFile() {
        try {
            JsonReader reader = new JsonReader("./data/FileNotFound.json");
            reader.read();
            fail("FileNotFoundException was expected");
        } catch (IOException e){
            // pass
        }
    }

    @Test
    public void jsonReaderTestEmpty() {
        try {
            String dest = "./data/testReaderEmpty.json";
            List<Account> accountList = new ArrayList<>();

            JsonReader jsonReader = new JsonReader(dest);
            List<Account> result = jsonReader.read();
            assertEquals(accountList,result);

        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void jsonReaderTestGeneral() {
        try {
            setAccounts();
            String dest = "./data/testReaderGeneral.json";
            List<Account> accountList = new ArrayList<>();

            accountList.add(account2);
            accountList.add(account1);

            JsonReader jsonReader = new JsonReader(dest);
            List<Account> result = jsonReader.read();
            checkAccount(accountList,result);

        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

}
