package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

//partially copied from jsonTest(class)->Persistence(package)->test->JsonSerializationDemo(project)
public class JsonTest {
    protected void testTask(String taskName, String taskDescription, boolean taskIsUrgent,
                            int taskDueInDays,  String taskTags,Task task) {
        assertEquals(taskName, task.getName());
        assertEquals(taskDescription, task.getDescription());
        assertEquals(taskIsUrgent, task.getIsUrgent());
        assertEquals(taskDueInDays, task.getDueInDays());
        assertEquals(taskTags, task.getTags());
        assertEquals(taskDescription, task.getDescription());

    }
}
