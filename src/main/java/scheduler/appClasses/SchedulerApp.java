package scheduler.appClasses;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scheduler.organize.DaysHashMap;

import java.io.IOException;

public class SchedulerApp extends Application {
    public static Scene scene;
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerApp.class.getResource("WeekPane.fxml"));
        this.stage = stage;
        scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Scheduler");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            DaysHashMap.save();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}