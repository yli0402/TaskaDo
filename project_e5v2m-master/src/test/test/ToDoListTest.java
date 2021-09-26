package test;

import model.InvalidInputException;
import model.ToDoList;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    private ToDoList toDoList1;
    private Task task1;
    private Task task2;

    @BeforeEach
    void runBefore() throws InvalidInputException {
        toDoList1 = new ToDoList("My TaskaDoList");
        task1 = new Task("a","aa", false, 10, "aaa");
        task2 = new Task("b","bb", true, 100, "bbb");
    }

    @Test
    void testAdd() {
        toDoList1.add(task1);
        assertEquals(1, toDoList1.length());
        toDoList1.add(task2);
        assertEquals(2, toDoList1.length());
    }

    @Test
    void testDelete() {
        toDoList1.add(task1);
        toDoList1.add(task2);
        assertEquals(2, toDoList1.length());
        toDoList1.delete(1);
        assertEquals(1, toDoList1.length());
        toDoList1.delete(0);
        assertEquals(0, toDoList1.length());
    }

    @Test
    void viewAll() {
        toDoList1.add(task1);
        toDoList1.add(task2);
        assertTrue(toDoList1.viewAll());
        toDoList1.delete(1);
        assertTrue(toDoList1.viewAll());
        toDoList1.delete(0);
        assertFalse(toDoList1.viewAll());
    }

    @Test
    void testName(){
        assertEquals("My TaskaDoList", toDoList1.getName());
    }

    @Test
    void testGetTasks() {
        toDoList1.add(task1);
        assertEquals (task1, toDoList1.getTasks().get(0));
    }
}