package scheduler.appClasses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scheduler.organize.Date;
import scheduler.organize.DaysHashMap;

import java.io.IOException;
import java.time.LocalDate;

public class SchedulerApp extends Application {
    public static Scene scene;
    public static Stage stage;
    public static Date focusedDate;
    @Override
    public void start(Stage stage) throws IOException {
        LocalDate today = LocalDate.now();
        focusedDate = new Date(today.getYear(), today.getMonthValue(), today.getDayOfMonth());


        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerApp.class.getResource("WeekPane.fxml"));
        SchedulerApp.stage = stage;
        scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Scheduler");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            DaysHashMap.save();
        });
    }

    public static void setRoot(String fxml){
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerApp.class.getResource(fxml + ".fxml"));
        try{
            scene.setRoot(fxmlLoader.load());
        } catch (IOException e){
            e.printStackTrace();
        }
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}