package org.example;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    String title;
    String description;
    Date expirationDate;
    TaskPriority priority;
    public static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

}

public enum TaskPriority {
    LOW, MEDIUM, HIGH
}