import org.example.Task;
import org.example.TaskPriority;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.example.Task.dateFormatter;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TaskTest {
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
    void createTask() {
        assertDoesNotThrow(
                () -> new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskTitleIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Task(null, "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskTitleIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Task("", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskDescriptionIsNull() {
        assertDoesNotThrow(
                () -> new Task("Title", null, defaultTestDate, TaskPriority.MEDIUM)

        );
    }

    @Test
    void createTaskDescriptionIsEmpty() {
        assertDoesNotThrow(
                () -> new Task("Title", "", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskExpirationDateIsNull() {
        assertDoesNotThrow(
                () -> new Task("Title", "Description", null, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskExpirationDateIsEmpty() {
        assertDoesNotThrow(
                () -> new Task("Title", "Description", "", TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskInvalidExpirationDate() {
        assertThrows(IllegalArgumentException.class,
                () -> new Task("Title", "Description", "23/40/2023", TaskPriority.MEDIUM)
        );

        assertThrows(IllegalArgumentException.class,
                () -> new Task("Title", "Description", "a", TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskExpirationDateAlreadyHasPassed() {
        assertThrows(IllegalArgumentException.class,
                () -> new Task("Title", "Description", dateThatAlreadyHasPassed, TaskPriority.MEDIUM)
        );
    }

    @Test
    void createTaskPriorityIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Task("Title", "Description", defaultTestDate, null)
        );
    }

    @Test
    void setTaskTitle() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);
        task.setTitle("Updated Title");
        assertEquals("Updated Title", task.getTitle());
    }

    @Test
    void setTaskTitleIsEmpty() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        assertThrows(
                IllegalArgumentException.class,
                () -> task.setTitle("")
        );
    }

    @Test
    void setTaskTitleIsNull() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        assertThrows(
                IllegalArgumentException.class,
                () -> task.setTitle(null)
        );
    }

    @Test
    void setTaskDescription() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);
        task.setDescription("Updated Description");
        assertEquals("Updated Description", task.getDescription());
    }

    @Test
    void setTaskDescriptionIsEmpty() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        assertDoesNotThrow(
                () -> task.setDescription("")
        );
        assertEquals("", task.getDescription());
    }

    @Test
    void setTaskDescriptionIsNull() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);
        task.setDescription(null);
        assertEquals("", task.getDescription());
    }

    @Test
    void setTaskExpirationDate() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);
        task.setExpirationDate("");
        assertNull(task.getExpirationDate());
    }

    @Test
    void setTaskExpirationDateIsEmpty() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);
        task.setExpirationDate("");
        assertNull(task.getExpirationDate());
    }

    @Test
    void setTaskExpirationDateIsNull() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);
        task.setExpirationDate(null);
        assertNull(task.getExpirationDate());
    }

    @Test
    void setTaskExpirationDateIsInvalid() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        assertThrows(
                IllegalArgumentException.class,
                () -> task.setExpirationDate("23/40/2023")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> task.setExpirationDate("a")
        );
    }

    @Test
    void setTaskExpirationDateHasAlreadyPassed() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        assertThrows(
                IllegalArgumentException.class,
                () -> task.setExpirationDate(dateFormatter.format(dateThatAlreadyHasPassed))
        );
    }

    @Test
    void setTaskPriority() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        task.setPriority(TaskPriority.LOW);
        assertEquals(TaskPriority.LOW, task.getPriority());

        task.setPriority(TaskPriority.HIGH);
        assertEquals(TaskPriority.HIGH, task.getPriority());

        task.setPriority(TaskPriority.MEDIUM);
        assertEquals(TaskPriority.MEDIUM, task.getPriority());
    }

    @Test
    void setTaskPriorityIsNull() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        assertThrows(
                IllegalArgumentException.class,
                () -> task.setPriority(null)
        );
    }
}
