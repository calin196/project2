package com.calin.todo.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.InputStream;

public class MainView extends StackPane {

    private static final String[] OCEAN_CANDIDATES = {
            "/project2_ocean.jpg", "/project2_ocean.jpeg", "/project2_ocean.png",
            "/project2ocean.jpg",  "/project2ocean.jpeg",  "/project2ocean.png"
    };

    private static final String[] LOGO_CANDIDATES = {
            "/project2_logo.png", "/project2_logo.jpg", "/project2_logo.jpeg",
            "/project2 logo.png", "/project2 logo.jpg", "/project2 logo.jpeg"
    };

    public MainView() {

        // =========================
        // BACKGROUND (LOAD ONCE)
        // =========================
        ImageView bg = new ImageView(loadFirstExisting(OCEAN_CANDIDATES));
        bg.setPreserveRatio(false);
        bg.fitWidthProperty().bind(widthProperty());
        bg.fitHeightProperty().bind(heightProperty());

        // =========================
        // OVERLAY
        // =========================
        Region overlay = new Region();
        overlay.setBackground(new Background(new BackgroundFill(
                new LinearGradient(
                        0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                        new Stop(0.0, Color.color(0, 0, 0, 0.15)),
                        new Stop(0.55, Color.color(0, 0, 0, 0.40)),
                        new Stop(1.0, Color.color(0, 0, 0, 0.55))
                ),
                CornerRadii.EMPTY,
                Insets.EMPTY
        )));
        overlay.prefWidthProperty().bind(widthProperty());
        overlay.prefHeightProperty().bind(heightProperty());

        // =========================
        // CONTENT
        // =========================
        StackPane content = createHomeContent();

        // ORDER MATTERS
        getChildren().addAll(bg, overlay, content);

        // navigator controls ONLY content (index 2)
        ViewNavigator.init(this);
    }

    private StackPane createHomeContent() {

        HBox content = new HBox(50);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(40));

        // =========================
        // LOGO
        // =========================
        ImageView logo = new ImageView(loadFirstExisting(LOGO_CANDIDATES));
        logo.setPreserveRatio(true);
        logo.setFitWidth(580);

        ColorAdjust white = new ColorAdjust();
        white.setSaturation(-1);
        white.setBrightness(1);
        white.setContrast(0.2);
        logo.setEffect(white);
        logo.setSmooth(false);

        // =========================
        // TEXT
        // =========================
        VBox textBox = new VBox(18);
        textBox.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Today, in focus");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        title.setTextFill(Color.WHITE);

        Label subtitle = new Label(
                "One thoughtful question, every day.\n" +
                "No tasks. No pressure. Just clarity."
        );
        subtitle.setFont(Font.font("Arial", 20));
        subtitle.setTextFill(Color.color(1, 1, 1, 0.92));

        // =========================
        // BUTTON (FIXED STYLE)
        // =========================
        Button startBtn = new Button("Let's begin");
        startBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        startBtn.setMinWidth(260);
        startBtn.setFocusTraversable(false);

        String normal =
                "-fx-background-color: transparent;" +
                "-fx-border-color: white;" +
                "-fx-border-width: 2px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 14 46;" +
                "-fx-background-radius: 30px;" +
                "-fx-border-radius: 30px;" +
                "-fx-cursor: hand;";

        String hover =
                "-fx-background-color: rgba(255,255,255,0.18);" +
                "-fx-border-color: white;" +
                "-fx-border-width: 2px;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 14 46;" +
                "-fx-background-radius: 30px;" +
                "-fx-border-radius: 30px;" +
                "-fx-cursor: hand;";

        startBtn.setStyle(normal);
        startBtn.setOnMouseEntered(e -> startBtn.setStyle(hover));
        startBtn.setOnMouseExited(e -> startBtn.setStyle(normal));

        startBtn.setOnAction(e ->
                ViewNavigator.switchView(new SecondView())
        );

        textBox.getChildren().addAll(title, subtitle, startBtn);
        content.getChildren().addAll(logo, textBox);

        return new StackPane(content);
    }

    private Image loadFirstExisting(String[] candidates) {
        for (String path : candidates) {
            InputStream is = getClass().getResourceAsStream(path);
            if (is != null) return new Image(is);
        }
        throw new RuntimeException("Image not found");
    }
}
