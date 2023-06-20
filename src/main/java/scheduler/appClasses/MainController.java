package scheduler.appClasses;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import scheduler.Nodes.DayPane;
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

        Year year2003 = new Year(2003);
        year2003.createMonth(4);
        Day fourthOfApril = new Day(6, DayOfTheWeek.SUNDAY);
        Event birth = new Event("I was born", 360, 370, 1.0, 0.5, 0);
        Event death = new Event("I died", 400, 430, 0.5, 1.0, 0.2);
        fourthOfApril.addEvent(birth);
        fourthOfApril.addEvent(death);
        year2003.getMonth(4).addDay(fourthOfApril);
        YearsSingleton.addInitializedYear(year2003);
        DayPane dayStackPane = new DayPane(2003, 4, 6);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(dayStackPane);
        scrollPane.setMinSize(220, 700);
        scrollPane.setMaxSize(220, 700);
        testView.getChildren().add(scrollPane);
    }

    private void updateTime(){
        time.setText(CurrentTime.getCurrentTime());
    }

}
