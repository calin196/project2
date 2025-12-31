package com.calin.todo.domain;

import java.time.LocalDateTime;

public class ReflectionAnswer {

    private final ReflectionQuestion question;
    private final int score; // e.g. 1–5
    private final LocalDateTime answeredAt;

    public ReflectionAnswer(ReflectionQuestion question, int score) {
        this.question = question;
        this.score = score;
        this.answeredAt = LocalDateTime.now();
    }

    // getters
}
