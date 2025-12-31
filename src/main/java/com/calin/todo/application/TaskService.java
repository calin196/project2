package com.calin.todo.application;

import com.calin.todo.domain.Task;
import com.calin.todo.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    public Task createTask(String title, TaskCategory category) {
        Task task = new Task(UUID.randomUUID().toString(), title, category);
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
