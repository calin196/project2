package com.calin.todo.application;

import com.calin.todo.domain.ExperimentSession;

import java.time.LocalDate;

public class ExperimentService {

    private final ExperimentSession session;

    public ExperimentService() {
        this.session = new ExperimentSession(LocalDate.now());
    }

    public boolean isExperimentFinished() {
        return session.isFinished();
    }

    public ExperimentSession getSession() {
        return session;
    }
}
