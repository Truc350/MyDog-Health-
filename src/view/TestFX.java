package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestFX extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX is working!");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

