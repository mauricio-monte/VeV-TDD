package junit5Tests.TDD;

import org.example.Task;
import org.example.TaskManager;
import org.example.TaskPriority;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.example.Task.dateFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TaskManagerTest {
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
    @Tag("essential")
    void getTasksEmptyList() {
        TaskManager taskManager = new TaskManager();
        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    @Tag("essential")
    void getTasks() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);
        taskManager.createTask("Task 2", "Description", defaultTestDate, TaskPriority.MEDIUM);

        assertEquals(2, taskManager.getTasks().size());
        assertEquals("Task 1", taskManager.getTasks().get(0).getTitle());
        assertEquals("Task 2", taskManager.getTasks().get(1).getTitle());
    }

    @Test
    @Tag("essential")
    @Tag("ordering")
    void listTasksByExpirationDateAndPriority() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Task Near Expiration High Priority", "Description", defaultTestDate, TaskPriority.HIGH);
        taskManager.createTask("Task Distant Expiration High Priority", "Description", distantDate, TaskPriority.HIGH);

        taskManager.createTask("Task Near Expiration Medium Priority", "Description", defaultTestDate, TaskPriority.MEDIUM);
        taskManager.createTask("Task Distant Expiration Medium Priority", "Description", distantDate, TaskPriority.MEDIUM);

        taskManager.createTask("Task Near Expiration Low Priority", "Description", defaultTestDate, TaskPriority.LOW);
        taskManager.createTask("Task Distant Expiration Low Priority", "Description", distantDate, TaskPriority.LOW);

        ArrayList<Task> registeredTasks = taskManager.listTasksByExpirationDateAndPriority();

        assertEquals("Task Near Expiration High Priority", registeredTasks.get(0).getTitle());
        assertEquals("Task Near Expiration Medium Priority", registeredTasks.get(1).getTitle());
        assertEquals("Task Near Expiration Low Priority", registeredTasks.get(2).getTitle());
        assertEquals("Task Distant Expiration High Priority", registeredTasks.get(3).getTitle());
        assertEquals("Task Distant Expiration Medium Priority", registeredTasks.get(4).getTitle());
        assertEquals("Task Distant Expiration Low Priority", registeredTasks.get(5).getTitle());
    }

    @Test
    @Tag("essential")
    void updateTask() {
        TaskManager taskManager = new TaskManager();

        taskManager.createTask("Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);
        taskManager.createTask("Task 2", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();

        taskManager.updateTask(taskId, "Updated Title", "Updated Description", distantDate, TaskPriority.LOW);

        Task updatedTask = taskManager.getTasks().get(0);

        assertEquals("Updated Title", updatedTask.getTitle());
        assertEquals("Updated Description", updatedTask.getDescription());
        assertEquals(distantDate, dateFormatter.format(updatedTask.getExpirationDate()));
        assertEquals(TaskPriority.LOW, updatedTask.getPriority());
    }

    @Test
    @Tag("essential")
    void deleteTask() {
        TaskManager taskManager = new TaskManager();
        taskManager.createTask("Título Task 1", "Description", defaultTestDate, TaskPriority.MEDIUM);
        taskManager.createTask("Título Task 2", "Description", defaultTestDate, TaskPriority.MEDIUM);

        Integer taskId = taskManager.getTasks().get(0).getId();

        taskManager.deleteTask(taskId);

        assertEquals(1, taskManager.getTasks().size());
        assertNotEquals(taskId, taskManager.getTasks().get(0).getId());
    }
}
