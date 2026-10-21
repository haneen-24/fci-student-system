package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new Label("JavaFX works!"), 400, 300)); // شاشة فيها كلمة بس
        stage.setTitle("FCI Student System");                            // عنوان الشاشة
        stage.show();                                                    // اعرض الشاشة
    }

    public static void main(String[] args) {
        launch(args);                                                    // شغّل JavaFX
    }
}