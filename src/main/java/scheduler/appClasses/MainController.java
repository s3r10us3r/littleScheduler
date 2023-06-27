package scheduler.appClasses;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import scheduler.Nodes.WeekHBox;
import scheduler.organize.*;
import scheduler.time.CurrentTime;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private VBox testView;
    @FXML
    private Label time;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        time.setText(CurrentTime.getCurrentTime());
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1000), event -> updateTime())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Day sixthOfApril = new Day(6, 4, 2003);
        Event birth = new Event("I was born", 360, 370, 1.0, 0.5, 0);
        Event death = new Event("I died", 400, 430, 0.5, 1.0, 0.2);
        sixthOfApril.addEvent(birth);
        sixthOfApril.addEvent(death);

        Day fifthOfApril = new Day(5, 4, 2003);
        Event dinner = new Event("Dinner with Grandma", 360, 400, 0, 0,0);
        fifthOfApril.addEvent(dinner);

        DaysHashMap.addDay(sixthOfApril);
        DaysHashMap.addDay(fifthOfApril);
        WeekHBox week = new WeekHBox(fifthOfApril.getNumber());
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(week);
        scrollPane.setMinSize(1000, 700);
        scrollPane.setMaxSize(1000, 700);
        testView.getChildren().add(scrollPane);
    }

    private void updateTime(){
        time.setText(CurrentTime.getCurrentTime());
    }

}
