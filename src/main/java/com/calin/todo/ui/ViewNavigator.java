package com.calin.todo.ui;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ViewNavigator {

    private static StackPane root;

    private ViewNavigator() {}

    public static void init(StackPane rootPane) {
        root = rootPane;
    }

    public static void switchView(Node nextView) {

        if (root == null) {
            throw new IllegalStateException("ViewNavigator not initialized");
        }

        // index 0 = background
        // index 1 = overlay
        // index 2 = current content
        Node current = root.getChildren().size() > 2
                ? root.getChildren().get(2)
                : null;

        if (current == null) {
            root.getChildren().add(nextView);
            return;
        }

        nextView.setTranslateX(root.getWidth());
        root.getChildren().add(nextView);

        TranslateTransition slideOut =
                new TranslateTransition(Duration.millis(600), current);
        slideOut.setToX(-root.getWidth());

        TranslateTransition slideIn =
                new TranslateTransition(Duration.millis(600), nextView);
        slideIn.setToX(0);

        FadeTransition fadeOut =
                new FadeTransition(Duration.millis(600), current);
        fadeOut.setToValue(0);

        ParallelTransition transition =
                new ParallelTransition(slideOut, slideIn, fadeOut);

        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.setOnFinished(e -> root.getChildren().remove(current));
        transition.play();
    }
}
