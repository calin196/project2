package com.calin.todo.domain;

import java.time.LocalDateTime;

public class Task {

    private final String id;
    private final String title;
    private final TaskCategory category;
    private boolean completed;
    private LocalDateTime completedAt;

    public Task(String id, String title, TaskCategory category) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.completed = false;
    }

    public void complete() {
        this.completed = true;
        this.completedAt = LocalDateTime.now();
    }

    // getters only
}
