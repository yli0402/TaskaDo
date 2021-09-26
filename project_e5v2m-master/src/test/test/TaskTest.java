package test;

import model.InvalidInputException;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task1;
    private Task task2;
    private Task task3;


    @BeforeEach
    void runBefore() throws InvalidInputException {
        task1 = new Task("a","aa", false, 10, "aaa");
        task2 = new Task("b","bb", true, 100, "bbb");
        try{
            task3 = new Task("c","cc", false, -2, "ccc");
            fail();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testAllPropertiesNonThrowsInvalidInputException() {
        assertEquals("a", task1.getName());
        assertEquals("b", task2.getName());
        task1.setName("A");
        task2.setName("B");
        assertEquals("A", task1.getName());
        assertEquals("B", task2.getName());

        assertEquals("aa", task1.getDescription());
        assertEquals("bb", task2.getDescription());
        task1.setDescription("AAA");
        task2.setDescription("BBB");
        assertEquals("AAA", task1.getDescription());
        assertEquals("BBB", task2.getDescription());

        try{
            task1.getDueInDays();
            task2.getDueInDays();
            assertEquals(10, task1.getDueInDays());
            assertEquals(100, task2.getDueInDays());
            task1.setDueInDays(100);
            task2.setDueInDays(1000);
            assertEquals(100, task1.getDueInDays());
            assertEquals(1000, task2.getDueInDays());
        }catch(InvalidInputException e){
            fail("Exception haven't been thrown");
        }

        try{
            task1.getDueInDays();
            task1.setDueInDays(-1);
            fail();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }

        assertFalse(task1.getIsUrgent());
        assertTrue(task2.getIsUrgent());
        task1.setIsUrgent(true);
        task2.setIsUrgent(false);
        assertTrue(task1.getIsUrgent());
        assertFalse(task2.getIsUrgent());


        assertEquals("aaa", task1.getTags());
        assertEquals("bbb", task2.getTags());
        task1.setTags("AAA");
        task2.setTags("BBB");
        assertEquals("AAA", task1.getTags());
        assertEquals("BBB", task2.getTags());
    }



    @Test
    void testToString() {
        String testTS1 = "Name: " + "a"
                + "\nDescription: " + "aa"
                + "\nDue in: " + "10" + " day(s) "
                + "\nImportant Level:" + "false"
                + "\nTag:" + "aaa";
        assertEquals(testTS1, task1.toString());

        String testTS2 = "Name: " + "b"
                + "\nDescription: " + "bb"
                + "\nDue in: " + "100" + " day(s) "
                + "\nImportant Level:" + "true"
                + "\nTag:" + "bbb";
        assertEquals(testTS2, task2.toString());
    }


}
