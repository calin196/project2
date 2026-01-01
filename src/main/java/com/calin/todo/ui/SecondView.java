package com.calin.todo.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SecondView extends StackPane {

    public SecondView() {
        setAlignment(Pos.CENTER);

        Label label = new Label("Second view");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        label.setTextFill(Color.WHITE);

        getChildren().add(label);
    }
}
