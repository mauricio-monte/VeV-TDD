package junit5Tests.TDD;

import junit5Tests.CustomAnnotations.EmptyStringTest;
import org.example.Task;
import org.example.TaskPriority;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

import static org.example.Task.dateFormatter;
import static org.junit.jupiter.api.Assertions.*;

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
    @Tag("essential")
    void createTask() {
        assertDoesNotThrow(
                () -> new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @EmptyStringTest
    void createTaskInvalidTitle(String title) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Task(title, "Description", defaultTestDate, TaskPriority.MEDIUM)
        );
    }

    @EmptyStringTest
    @Tag("essential")
    void createTaskNoDescription(String description) {
        assertDoesNotThrow(
                () -> new Task("Title", description, defaultTestDate, TaskPriority.MEDIUM)

        );
    }
    @EmptyStringTest
    @Tag("essential")
    void createTaskEmptyExpirationDate(String expirationDate) {
        assertDoesNotThrow(
                () -> new Task("Title", "Description", expirationDate, TaskPriority.MEDIUM)
        );
    }


    @ParameterizedTest
    @ValueSource(strings = {"23/40/2023", "a"})
    void createTaskInvalidExpirationDate(String expirationDate) {
        assertThrows(IllegalArgumentException.class,
                () -> new Task("Title", "Description", expirationDate, TaskPriority.MEDIUM)
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
    @Tag("essential")
    void setTaskTitle() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);
        task.setTitle("Updated Title");
        assertEquals("Updated Title", task.getTitle());
    }


    @EmptyStringTest
    void setTaskTitleIsEmpty(String newTitle) {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        assertThrows(
                IllegalArgumentException.class,
                () -> task.setTitle(newTitle)
        );
    }

    @Test
    @Tag("essential")
    void setTaskDescription() {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);
        task.setDescription("Updated Description");
        assertEquals("Updated Description", task.getDescription());
    }

    @EmptyStringTest
    @Tag("essential")
    void setTaskDescriptionIsEmpty(String newDescription) {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        assertDoesNotThrow(
                () -> task.setDescription(newDescription)
        );
        assertEquals("", task.getDescription());
    }

    @EmptyStringTest
    @Tag("essential")
    void setTaskExpirationDateIsEmpty(String newExpirationDate) {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);
        task.setExpirationDate(newExpirationDate);
        assertNull(task.getExpirationDate());
    }


    @ParameterizedTest@Tag("updateTask")
    @ValueSource(strings = {"23/40/2023", "a"})
    void setTaskExpirationDateIsInvalid(String newExpirationDate) {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        assertThrows(
                IllegalArgumentException.class,
                () -> task.setExpirationDate(newExpirationDate)
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

    @MethodSource
    static Stream<TaskPriority> setTaskPriorityMethodSource(){
        return Stream.of(TaskPriority.LOW, TaskPriority.MEDIUM, TaskPriority.HIGH);
    }


    @ParameterizedTest@Tag("updateTask")
    @MethodSource("setTaskPriorityMethodSource")
    @Tag("essential")
    void setTaskPriority(TaskPriority newTaskPriority) {
        Task task = new Task("Title", "Description", defaultTestDate, TaskPriority.MEDIUM);

        task.setPriority(newTaskPriority);
        assertEquals(newTaskPriority, task.getPriority());
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
