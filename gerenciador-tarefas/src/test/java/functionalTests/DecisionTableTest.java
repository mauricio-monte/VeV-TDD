package functionalTests;

import org.example.Task;
import org.example.TaskManager;
import org.example.TaskPriority;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static org.example.Task.dateFormatter;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecisionTableTest {

    static String defaultTestDate = "";
    static String distantDate = "";

    @BeforeAll
    static void initializeTestDate() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 5);

        Date currentDatePlusFiveDays = calendar.getTime();
        defaultTestDate = dateFormatter.format(currentDatePlusFiveDays);

        calendar.add(Calendar.DAY_OF_MONTH, 5);

        Date currentDatePlusTenDays = calendar.getTime();
        distantDate = dateFormatter.format(currentDatePlusTenDays);
    }

    @Test
    void createTaskWithFilledTitle() {
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskWithEmptyTitle() {
        TaskManager taskManager = new TaskManager();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.createTask("", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskWithFilledDescription() {
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskWithEmptyDescription() {
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskWithFilledValidExpirationDate() {
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskWithFilledInvalidExpirationDate() {
        TaskManager taskManager = new TaskManager();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.createTask("Title", "", "a", null)
        );
    }

    @Test
    void createTaskWithFilledEmptyExpirationDate() {
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "Description", "", TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskWithFilledPriority() {
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskWithEmptyPriority() {
        TaskManager taskManager = new TaskManager();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.createTask("Title", "", defaultTestDate, null)
        );
    }

    @Test
    void updateTaskWithFilledTitle() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Updated Title", "Description", defaultTestDate, TaskPriority.MEDIUM);
        Task updatedTask = taskManager.getTasks().get(0);

        assertEquals("Updated Title", updatedTask.getTitle());
    }

    @Test
    void updateTaskWithEmptyTitle() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.updateTask(taskId, "", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void updateTaskWithFilledDescription() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Task 1", "Updated Description", defaultTestDate, TaskPriority.MEDIUM);
        Task updatedTask = taskManager.getTasks().get(0);

        assertEquals("Updated Description", updatedTask.getDescription());
    }

    @Test
    void updateTaskWithEmptyDescription() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Task 1", "", defaultTestDate, TaskPriority.MEDIUM);
        Task updatedTask = taskManager.getTasks().get(0);

        assertEquals("", updatedTask.getDescription());
    }

    @Test
    void updateTaskWithFilledValidExpirationDate() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Task 1", "Description", distantDate, TaskPriority.MEDIUM);
        Task updatedTask = taskManager.getTasks().get(0);

        assertEquals(distantDate, dateFormatter.format(updatedTask.getExpirationDate()));
    }

    @Test
    void updateTaskWithFilledInvalidExpirationDate() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.updateTask(taskId, "Task 1", "Description", "a", TaskPriority.MEDIUM)
        );
    }

    @Test
    void updateTaskWithFilledEmptyExpirationDate() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Task 1", "Description", "", TaskPriority.MEDIUM);
        Task updatedTask = taskManager.getTasks().get(0);

        assertNull(updatedTask.getExpirationDate());
    }

    @Test
    void updateTaskWithFilledPriority() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Task 1", "Description", defaultTestDate, TaskPriority.LOW);
        Task updatedTask = taskManager.getTasks().get(0);

        assertEquals(TaskPriority.LOW, updatedTask.getPriority());
    }

    @Test
    void listTasksWithNoTasks() {
        TaskManager taskManager = new TaskManager();

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals(Collections.EMPTY_LIST, registeredTasks);
    }

    @Test
    void listTasksWithTask1ExpirationDateNearerThenTask2() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task Near Expiration High Priority", "Description", defaultTestDate, TaskPriority.HIGH);
        taskManager.createTask("Task Distant Expiration High Priority", "Description", distantDate, TaskPriority.HIGH);

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals("Task Near Expiration High Priority", registeredTasks.get(0).getTitle());
        assertEquals("Task Distant Expiration High Priority", registeredTasks.get(1).getTitle());
    }

    @Test
    void listTasksWithTask2ExpirationDateNearerThenTask2() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task Distant Expiration High Priority", "Description", distantDate, TaskPriority.HIGH);
        taskManager.createTask("Task Near Expiration High Priority", "Description", defaultTestDate, TaskPriority.HIGH);

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals("Task Near Expiration High Priority", registeredTasks.get(0).getTitle());
        assertEquals("Task Distant Expiration High Priority", registeredTasks.get(1).getTitle());
    }

    @Test
    void listTasksWithSameExpirationDateButTask1HasHigherPriorityThenTask2() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task Near Expiration High Priority", "Description", defaultTestDate, TaskPriority.HIGH);
        taskManager.createTask("Task Near Expiration Medium Priority", "Description", defaultTestDate, TaskPriority.MEDIUM);

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals("Task Near Expiration High Priority", registeredTasks.get(0).getTitle());
        assertEquals("Task Near Expiration Medium Priority", registeredTasks.get(1).getTitle());
    }

    @Test
    void listTasksWithSameExpirationDateButTask2HasHigherPriorityThenTask1() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task Near Expiration Medium Priority", "Description", defaultTestDate, TaskPriority.MEDIUM);
        taskManager.createTask("Task Near Expiration High Priority", "Description", defaultTestDate, TaskPriority.HIGH);

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals("Task Near Expiration High Priority", registeredTasks.get(0).getTitle());
        assertEquals("Task Near Expiration Medium Priority", registeredTasks.get(1).getTitle());
    }

    @Test
    void listTasksWithSameExpirationDateAndPriority() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.HIGH);
        taskManager.createTask("Task 2", "Description", defaultTestDate, TaskPriority.HIGH);

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals("Task 1", registeredTasks.get(0).getTitle());
        assertEquals("Task 2", registeredTasks.get(1).getTitle());
    }

    @Test
    void deleteTaskWithNoTasksCreated() {
        TaskManager taskManager = new TaskManager();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.deleteTask(null)
        );
    }

    @Test
    void deleteTaskWithTaskSelected() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.HIGH);

        ArrayList<Task> registeredTasks = taskManager.getTasks();
        Integer taskToDeleteId = registeredTasks.get(0).getId();

        taskManager.deleteTask(taskToDeleteId);

        assertEquals(Collections.EMPTY_LIST, taskManager.getTasks());
    }
}
