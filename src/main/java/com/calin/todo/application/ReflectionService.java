package com.calin.todo.application;

import com.calin.todo.domain.ReflectionAnswer;
import com.calin.todo.domain.ReflectionQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReflectionService {

    private final List<ReflectionQuestion> questions = new ArrayList<>();
    private final List<ReflectionAnswer> answers = new ArrayList<>();
    private final Random random = new Random();

    public ReflectionService() {
        questions.add(new ReflectionQuestion("Did you feel focused just now?"));
        questions.add(new ReflectionQuestion("What distracted you the most?"));
        questions.add(new ReflectionQuestion("Was this task meaningful?"));
    }

    public ReflectionQuestion getRandomQuestion() {
        return questions.get(random.nextInt(questions.size()));
    }

    public void recordAnswer(ReflectionQuestion question, int score) {
        answers.add(new ReflectionAnswer(question, score));
    }
}
