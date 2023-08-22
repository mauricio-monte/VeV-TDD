package org.example;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskManager {
    ArrayList<Task> tasks = new ArrayList<>();

    public void createTask(String title, String description, String expirationDate, TaskPriority priority) {
        tasks.add(new Task(title, description, expirationDate, priority));
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void updateTask(Integer taskId, String newTitle, String newDescription, String newExpirationDate, TaskPriority newPriority) {
        for (Task task: tasks) {
            if(task.getId().equals(taskId)) {
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                task.setExpirationDate(newExpirationDate);
                task.setPriority(newPriority);
            }
        }
    }

    public void deleteTask(Integer taskId) {
        int indexOfTaskToDelete = 0;

        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getId().equals(taskId)) {
                indexOfTaskToDelete = i;
            }
        }

        tasks.remove(indexOfTaskToDelete);
    }

    public ArrayList<Task> listTasksByExpirationDateAndPriority() {
        return (ArrayList<Task>) tasks.stream().sorted((t1, t2) -> {
            int expirationDateComparison =  t1.getExpirationDate().compareTo(t2.getExpirationDate());

            if (expirationDateComparison == 0) {
                return t1.getPriority().compareTo(t2.getPriority());
            } else {
                return expirationDateComparison;
            }
        }).collect(Collectors.toList());
    }
}
