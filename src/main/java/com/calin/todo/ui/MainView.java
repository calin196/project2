package com.calin.todo.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
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

    // ---- CHANGE NOTHING HERE (we will try multiple extensions) ----
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
        // Background image (FULL FIT — no white gaps)
        // =========================
        Image bgImg = loadFirstExisting(OCEAN_CANDIDATES);
        ImageView bg = new ImageView(bgImg);
        bg.setPreserveRatio(false); // IMPORTANT: fills whole window
        bg.setSmooth(true);
        bg.fitWidthProperty().bind(widthProperty());
        bg.fitHeightProperty().bind(heightProperty());

        // =========================
        // A subtle dark overlay (makes text readable)
        // (NOT a box. Just a transparent layer)
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
        // Main content layout (logo left, text+button right)
        // =========================
        HBox content = new HBox(70);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(40));

        // ---- Logo (bigger + glow) ----
        Image logoImg = loadFirstExisting(LOGO_CANDIDATES);
        ImageView logo = new ImageView(logoImg);
        logo.setPreserveRatio(true);
        logo.setFitWidth(520); // bigger
        logo.setSmooth(true);

        // Neon/glow for logo
        DropShadow logoGlow = new DropShadow();
        logoGlow.setColor(Color.web("#5ffcff"));
        logoGlow.setRadius(55);
        logoGlow.setSpread(0.25);
        logo.setEffect(logoGlow);
        logo.setBlendMode(BlendMode.SCREEN);

        // ---- Right side text ----
        VBox textBox = new VBox(18);
        textBox.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Today, in focus");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 54));
        title.setTextFill(Color.WHITE);

        Label subtitle = new Label("One thoughtful question, every day.\n" + //
                        "No tasks. No pressure. Just clarity.   ");
        subtitle.setFont(Font.font("Arial", 20));
        subtitle.setTextFill(Color.color(1, 1, 1, 0.92));

        // Neon glow for text (stronger, readable)
        DropShadow textGlow = new DropShadow();
        textGlow.setColor(Color.web("#5ffcff"));
        textGlow.setRadius(40);
        textGlow.setSpread(0.18);
        title.setEffect(textGlow);

        DropShadow subtitleGlow = new DropShadow();
        subtitleGlow.setColor(Color.web("#5ffcff"));
        subtitleGlow.setRadius(22);
        subtitleGlow.setSpread(0.10);
        subtitle.setEffect(subtitleGlow);

        // ---- Button (hover changes color ONLY, no movement) ----
        Button startBtn = new Button("Let's begin");
        startBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        startBtn.setMinWidth(260);

        final String normalStyle =
                "-fx-background-color: transparent;" +
                "-fx-border-color: #5ffcff;" +
                "-fx-border-width: 2;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 14 46;" +
                "-fx-background-radius: 30;" +
                "-fx-border-radius: 30;";

        final String hoverStyle =
                "-fx-background-color: rgba(95,252,255,0.22);" +   // ONLY color changes
                "-fx-border-color: #5ffcff;" +
                "-fx-border-width: 2;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 14 46;" +
                "-fx-background-radius: 30;" +
                "-fx-border-radius: 30;";

        startBtn.setStyle(normalStyle);

        startBtn.setOnMouseEntered(e -> startBtn.setStyle(hoverStyle));
        startBtn.setOnMouseExited(e -> startBtn.setStyle(normalStyle));

        startBtn.setOnAction(e -> System.out.println("START CLICKED -> next view"));

        textBox.getChildren().addAll(title, subtitle, startBtn);

        content.getChildren().addAll(logo, textBox);

        // Put everything together
        getChildren().addAll(bg, overlay, content);
    }

    // =========================
    // Resource loader that DOES NOT crash when file extension differs
    // =========================
    private Image loadFirstExisting(String[] candidates) {
        for (String path : candidates) {
            InputStream is = getClass().getResourceAsStream(path);
            if (is != null) {
                return new Image(is);
            }
        }
        throw new RuntimeException(
                "Could not find resource image. Tried: " + String.join(", ", candidates) +
                "\nPut the files in: src/main/resources/"
        );
    }
}


// check check tot norm 
