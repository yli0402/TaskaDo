package persistence;

import model.InvalidInputException;
import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//partially copied from jsonWriterTest(class)->Persistence(package)->test->JsonSerializationDemo(project)
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterIllegalFile() {
        try {
            ToDoList l = new ToDoList(" ");
            JsonWriter jWriter = new JsonWriter("./data/my\0illegal:fileName.json");
            jWriter.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyToDolist() {
        try {
            ToDoList l = new ToDoList("My TaskaDoList");
            JsonWriter jWriter = new JsonWriter("./data/testWriterEmptyToDoList.json");
            jWriter.open();
            jWriter.write(l);
            jWriter.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
            l = reader.read();
            assertEquals("My TaskaDoList", l.getName());
            assertEquals(0, l.length());
        } catch (IOException | InvalidInputException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralToDolist() {
        try {
            ToDoList l = new ToDoList("My TaskaDoList");
            l.add(new Task("jogging","do jogging at 7am", true,
                    1, "30minutes"));
            l.add(new Task("yoga","do yoga within 5 days", false,
                    5, "1hour"));
            JsonWriter jWriter = new JsonWriter("./data/testWriterGeneralToDoList.json");
            jWriter.open();
            jWriter.write(l);
            jWriter.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.json");
            l = reader.read();
            List<Task> thingies = l.getTasks();
            assertEquals(2, l.length());
            testTask("jogging","do jogging at 7am", true,
                    1, "30minutes", l.getTasks().get(0));
            testTask("yoga","do yoga within 5 days", false,
                    5, "1hour",  l.getTasks().get(1));

        } catch (IOException | InvalidInputException e) {
            fail("Exception should not have been thrown");
        }
    }
}