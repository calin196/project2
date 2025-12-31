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

    // ---- CHANGE NOTHING HERE ----
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
        // Background
        // =========================
        ImageView bg = new ImageView(loadFirstExisting(OCEAN_CANDIDATES));
        bg.setPreserveRatio(false);
        bg.fitWidthProperty().bind(widthProperty());
        bg.fitHeightProperty().bind(heightProperty());

        // =========================
        // Overlay
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
        // Layout
        // =========================
        HBox content = new HBox(50);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(40));

        // =========================
        // LOGO — PURE WHITE, NO GLOW
        // =========================
        Image logoImage = loadFirstExisting(LOGO_CANDIDATES);
        ImageView logo = new ImageView(logoImage);
        logo.setPreserveRatio(true);
        logo.setFitWidth(580);

        // FORCE LOGO TO WHITE
        ColorAdjust forceWhite = new ColorAdjust();
        forceWhite.setSaturation(-1.0); // remove all color
        forceWhite.setBrightness(1.0);  // push to white
        forceWhite.setContrast(0.2);

        logo.setEffect(forceWhite);
        logo.setSmooth(false); // prevents blue bleed

        // =========================
        // Text
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
        // Button
        // =========================
        Button startBtn = new Button("Let's begin");
        startBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        startBtn.setMinWidth(260);

        final String normalStyle =
                "-fx-background-color: transparent;" +
                "-fx-border-color: white;" +
                "-fx-border-width: 2;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 14 46;" +
                "-fx-background-radius: 30;" +
                "-fx-border-radius: 30;";

        final String hoverStyle =
                "-fx-background-color: rgba(255,255,255,0.18);" +
                "-fx-border-color: white;" +
                "-fx-border-width: 2;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 14 46;" +
                "-fx-background-radius: 30;" +
                "-fx-border-radius: 30;";

        startBtn.setStyle(normalStyle);
        startBtn.setOnMouseEntered(e -> startBtn.setStyle(hoverStyle));
        startBtn.setOnMouseExited(e -> startBtn.setStyle(normalStyle));

        textBox.getChildren().addAll(title, subtitle, startBtn);
        content.getChildren().addAll(logo, textBox);

        getChildren().addAll(bg, overlay, content);
    }

    // =========================
    // Resource loader
    // =========================
    private Image loadFirstExisting(String[] candidates) {
        for (String path : candidates) {
            InputStream is = getClass().getResourceAsStream(path);
            if (is != null) {
                return new Image(is);
            }
        }
        throw new RuntimeException(
                "Could not find resource image. Tried: " + String.join(", ", candidates)
        );
    }
}
