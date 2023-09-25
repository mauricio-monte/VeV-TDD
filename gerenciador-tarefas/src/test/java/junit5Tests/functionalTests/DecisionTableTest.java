package junit5Tests.functionalTests;

import org.example.Task;
import org.example.TaskManager;
import org.example.TaskPriority;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static org.example.Task.dateFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class DecisionTableTest {

    static String defaultTestDate = "";
    static String distantDate = "";
    static String dateThatAlreadyHasPassed = "";

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

        calendar.add(Calendar.DAY_OF_MONTH, -11);
        Date yesterday = calendar.getTime();
        dateThatAlreadyHasPassed = dateFormatter.format(yesterday);
    }

    @Test
    @Tag("essential")
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
    @Tag("essential")
    void createTaskWithFilledDescription() {
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    @Tag("essential")
    void createTaskWithEmptyDescription() {
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    @Tag("essential")
    void createTaskWithFilledValidExpirationDate() {
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"23/40/2023", "a"})
    void createTaskWithFilledInvalidExpirationDate(String expirationDate) {
        TaskManager taskManager = new TaskManager();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskManager.createTask("Title", "", expirationDate, null)
        );
    }

    @Test
    @Tag("essential")
    void createTaskWithFilledEmptyExpirationDate() {
        TaskManager taskManager = new TaskManager();

        assertDoesNotThrow(
                () -> taskManager.createTask("Title", "Description", "", TaskPriority.MEDIUM)
        );
    }

    @Test
    @Tag("essential")
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
    @Tag("essential")
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
    @Tag("essential")
    void updateTaskWithFilledDescription() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Task 1", "Updated Description", defaultTestDate, TaskPriority.MEDIUM);
        Task updatedTask = taskManager.getTasks().get(0);

        assertEquals("Updated Description", updatedTask.getDescription());
    }

    @Test
    @Tag("essential")
    void updateTaskWithEmptyDescription() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Task 1", "", defaultTestDate, TaskPriority.MEDIUM);
        Task updatedTask = taskManager.getTasks().get(0);

        assertEquals("", updatedTask.getDescription());
    }

    @Test
    @Tag("essential")
    void updateTaskWithFilledValidExpirationDate() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Task 1", "Description", distantDate, TaskPriority.MEDIUM);
        Task updatedTask = taskManager.getTasks().get(0);

        assertEquals(distantDate, dateFormatter.format(updatedTask.getExpirationDate()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"23/40/2023", "a"})
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
    @Tag("essential")
    void updateTaskWithEmptyExpirationDate() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Task 1", "Description", "", TaskPriority.MEDIUM);
        Task updatedTask = taskManager.getTasks().get(0);

        assertNull(updatedTask.getExpirationDate());
    }

    @Test
    @Tag("essential")
    void updateTaskWithFilledPriority() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();
        taskManager.updateTask(taskId, "Task 1", "Description", defaultTestDate, TaskPriority.LOW);
        Task updatedTask = taskManager.getTasks().get(0);

        assertEquals(TaskPriority.LOW, updatedTask.getPriority());
    }

    @Test
    @Tag("essential")
    void listTasksWithNoTasks() {
        TaskManager taskManager = new TaskManager();

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals(Collections.EMPTY_LIST, registeredTasks);
    }

    @Test
    @Tag("essential")
    @Tag("ordering")
    void listTasksWithTask1ExpirationDateNearerThenTask2() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task Near Expiration High Priority", "Description", defaultTestDate, TaskPriority.HIGH);
        taskManager.createTask("Task Distant Expiration High Priority", "Description", distantDate, TaskPriority.HIGH);

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals("Task Near Expiration High Priority", registeredTasks.get(0).getTitle());
        assertEquals("Task Distant Expiration High Priority", registeredTasks.get(1).getTitle());
    }

    @Test
    @Tag("essential")
    @Tag("ordering")
    void listTasksWithTask2ExpirationDateNearerThenTask2() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task Distant Expiration High Priority", "Description", distantDate, TaskPriority.HIGH);
        taskManager.createTask("Task Near Expiration High Priority", "Description", defaultTestDate, TaskPriority.HIGH);

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals("Task Near Expiration High Priority", registeredTasks.get(0).getTitle());
        assertEquals("Task Distant Expiration High Priority", registeredTasks.get(1).getTitle());
    }

    @Test
    @Tag("essential")
    @Tag("ordering")
    void listTasksWithSameExpirationDateButTask1HasHigherPriorityThenTask2() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task Near Expiration High Priority", "Description", defaultTestDate, TaskPriority.HIGH);
        taskManager.createTask("Task Near Expiration Medium Priority", "Description", defaultTestDate, TaskPriority.MEDIUM);

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals("Task Near Expiration High Priority", registeredTasks.get(0).getTitle());
        assertEquals("Task Near Expiration Medium Priority", registeredTasks.get(1).getTitle());
    }

    @Test
    @Tag("essential")
    @Tag("ordering")
    void listTasksWithSameExpirationDateButTask2HasHigherPriorityThenTask1() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task Near Expiration Medium Priority", "Description", defaultTestDate, TaskPriority.MEDIUM);
        taskManager.createTask("Task Near Expiration High Priority", "Description", defaultTestDate, TaskPriority.HIGH);

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals("Task Near Expiration High Priority", registeredTasks.get(0).getTitle());
        assertEquals("Task Near Expiration Medium Priority", registeredTasks.get(1).getTitle());
    }

    @Test
    @Tag("essential")
    @Tag("ordering")
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
    @Tag("essential")
    void deleteTaskWithTaskSelected() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.HIGH);

        ArrayList<Task> registeredTasks = taskManager.getTasks();
        Integer taskToDeleteId = registeredTasks.get(0).getId();

        taskManager.deleteTask(taskToDeleteId);

        assertEquals(Collections.EMPTY_LIST, taskManager.getTasks());
    }
}
