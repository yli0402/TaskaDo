package test;

import model.InvalidInputException;
import model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionTest {

    @Test
    void testInvalidInputException() {
        int days1 = 12;
        int days2 = -2;
        try {
            Task t1 = new Task("","end",false, days1,"");
        } catch (InvalidInputException e) {
            fail("no exception be thrown");
        }

        try {
            //Add a new task that the days input is negative.
            Task t2 = new Task("","",false, days2,"");
            fail("no exception be thrown");
        } catch (InvalidInputException e) {
            return;
        }
    }
}