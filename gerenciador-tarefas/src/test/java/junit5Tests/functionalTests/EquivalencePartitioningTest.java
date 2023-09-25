package junit5Tests.functionalTests;

import junit5Tests.CustomAnnotations.EmptyStringTest;
import org.example.TaskManager;
import org.example.TaskPriority;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.example.Task.dateFormatter;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EquivalencePartitioningTest {

    static String defaultTestDate = "";
    static String dateThatAlreadyHasPassed = "";

    @BeforeAll
    static void initializeTestDate() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 5);

        Date currentDatePlusFiveDays = calendar.getTime();
        defaultTestDate = dateFormatter.format(currentDatePlusFiveDays);

        calendar.add(Calendar.DAY_OF_MONTH, -6);

        Date yesterday = calendar.getTime();
        dateThatAlreadyHasPassed = dateFormatter.format(yesterday);
    }

    @Test
    @Tag("essential")
    void createTaskWithValidExpirationDate(){
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }


    @EmptyStringTest
    @Tag("essential")
    void createTaskWithEmptyExpirationDate(String expirationDate){
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "Description", expirationDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskWithInvalidFormatExpirationDate(){
        TaskManager taskManager = new TaskManager();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.createTask("Title", "Description", "a", TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskWithInvalidDateExpirationDate(){
        TaskManager taskManager = new TaskManager();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.createTask("Title", "Description", "50/30/2023", TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskWithPastExpirationDate(){
        TaskManager taskManager = new TaskManager();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.createTask("Title", "Description", dateThatAlreadyHasPassed, TaskPriority.MEDIUM)
        );
    }

    @Test
    @Tag("essential")
    void updateTaskWithValidExpirationDate(){
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        assertDoesNotThrow(
                () -> taskManager.updateTask(taskId, "Task 1", "", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @EmptyStringTest
    @Tag("essential")
    void updateTaskWithEmptyExpirationDate(String emptyExpirationDate){
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        assertDoesNotThrow(
                () -> taskManager.updateTask(taskId, "Task 1", "Description", emptyExpirationDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void updateTaskWithInvalidFormatExpirationDate(){
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.updateTask(taskId, "Task 1", "Description", "a", TaskPriority.MEDIUM)
        );
    }

    @Test
    void updateTaskWithInvalidDateExpirationDate(){
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.updateTask(taskId, "Task 1", "Description", "50/30/2023", TaskPriority.MEDIUM)
        );
    }

    @Test
    void updateTaskWithPastExpirationDate(){
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.updateTask(taskId, "Task 1", "Description", dateThatAlreadyHasPassed, TaskPriority.MEDIUM)
        );
    }
}
