package persistence;

import model.InvalidInputException;
import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonListTest {
    private JsonList jl;
    private JsonList jl2;
    private ToDoList toDoList;

    @BeforeEach
    void runBefore () {
        jl = new JsonList();
        jl2 = new JsonList();
        toDoList = new ToDoList("Jtest");
    }
    @Test
    void testSaveAndLoadData() throws IOException{
        Task task1 = null;
        try {
            task1 = new Task("yoga","do yoga",true,1,"sport");
            toDoList.add(task1);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        jl.setTodoLists(toDoList);
        jl.saveData();
        jl.loadData();
        toDoList = jl.getTodoList();
        assertEquals(1, toDoList.getTasks().size());
        assertEquals("yoga", toDoList.getTasks().get(0).getName());
        toDoList.delete(0);
        assertEquals(0, toDoList.getTasks().size());
        toDoList.setTasksInList(new ArrayList<>());
        assertEquals(0, toDoList.getTasks().size());
    }

    @Test
    void testSaveAndLoadDataThrowInvalidInputExp() throws IOException{
        Task task1 = null;
        Task task2 = null;
        try {
            task1 = new Task("yoga","do yoga",true,1,"sport");
            toDoList.add(task1);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        try {
            task2 = new Task("swimming","do swimming",true,-1,"sport");
            toDoList.add(task2);
            jl2.setTodoLists(toDoList);
            jl2.saveData();
            jl2.loadData();
            fail();
        } catch (InvalidInputException exception) {
            System.out.println("aa");
        }
        }
    }