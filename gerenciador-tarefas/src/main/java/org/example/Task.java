package org.example;


import java.text.ParseException;
import java.util.Date;


public class Task {
    Integer id;
    String title;
    String description;
    Date expirationDate;
    TaskPriority priority;
    private static Integer idCounter = 0;
    public static DateFormatter dateFormatter = new DateFormatter();

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title can't be null/empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = "";
        } else {
            this.description = description;
        }
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) throws IllegalArgumentException {
        if (expirationDate == null || expirationDate.isEmpty()) {
            this.expirationDate = null;
        } else {
            try {
                Date newExpirationDate = dateFormatter.parse(expirationDate);
                Date now = new Date();

                if (newExpirationDate.toInstant().isBefore(now.toInstant())) {
                    throw new IllegalArgumentException("Expiration date needs to be in the future");
                }

                this.expirationDate = newExpirationDate;
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority newPriority) {
        if (newPriority == null) {
            throw new IllegalArgumentException("Priority can't be null");
        }
        this.priority = newPriority;
    }

    public Task(String title, String description, String expirationDate, TaskPriority priority) {
        validateConstructorParameters(title, expirationDate, priority);
        this.id = idCounter++;
        this.title = title;

        if (description == null || description.isEmpty()) {
            this.description = "";
        } else {
            this.description = description;
        }

        if (expirationDate == null || expirationDate.isEmpty()) {
            this.expirationDate = null;
        } else {
            try {
                this.expirationDate = dateFormatter.parse(expirationDate);
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }

        this.priority = priority;
    }

    private void validateConstructorParameters(String title, String expirationDate, TaskPriority priority) throws IllegalArgumentException {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title can't be null/empty");
        }

        if (expirationDate != null && !expirationDate.isEmpty()) {
            try {
                Date date = dateFormatter.parse(expirationDate);

                if (date.toInstant().isBefore(new Date().toInstant())) {
                    throw new IllegalArgumentException("Expiration date needs to be in the future");
                }

            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }

        if (priority == null) {
            throw new IllegalArgumentException("Priority can't be null");
        }
    }
}
