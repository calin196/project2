package com.calin.todo;

import com.calin.todo.ui.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("ASU Analytics");

        var root = new MainView();
        var scene = new Scene(root, 1100, 700);

        // ✅ Window icon
        stage.getIcons().add(
                new Image(getClass().getResourceAsStream("/app_icon.png"))
        );

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
