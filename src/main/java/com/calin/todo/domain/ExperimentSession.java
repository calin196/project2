package com.calin.todo.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExperimentSession {

    private final LocalDate startDate;
    private final List<Task> tasks = new ArrayList<>();
    private final List<ReflectionAnswer> answers = new ArrayList<>();

    public ExperimentSession(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addAnswer(ReflectionAnswer answer) {
        answers.add(answer);
    }

    public boolean isFinished() {
        return startDate.plusDays(7).isBefore(LocalDate.now());
    }

    // getters
}
