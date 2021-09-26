package persistence;

import model.InvalidInputException;
import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//partially copied from jsonReaderTest(class)->Persistence(package)->test->JsonSerializationDemo(project)
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNoExistentFile() {
        JsonReader reader = new JsonReader("./data/noExistentFile.json");
        try {
            ToDoList l = reader.read();
            fail("IOException expected");
        } catch (IOException | InvalidInputException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.json");
        try {
            ToDoList l = reader.read();
            assertEquals("My TaskaDoList", l.getName());
            assertEquals(0, l.length());
        } catch (IOException | InvalidInputException e) {
            //pass
        }
    }

    @Test
    void testReaderGeneralToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDoList.json");
        try {
            ToDoList l = reader.read();
            List<Task> myList = l.getTasks();
            assertEquals(2, myList.size());
            testTask("jogging","do jogging at 7am", true,
                    1, "30minutes", myList.get(0));
            testTask("yoga","do yoga within 5 days", false,
                    5, "1hour", myList.get(1));
        } catch (IOException | InvalidInputException e) {
            //pass
        }
    }
}