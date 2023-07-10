package scheduler.appClasses;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import scheduler.Nodes.WeekPane.TimeTable;
import scheduler.Nodes.WeekPane.WeekPane;
import scheduler.organize.*;
import scheduler.time.CurrentTime;

import java.net.URL;
import java.util.ResourceBundle;
public class MainController implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label time;
    @FXML
    private ScrollPane weekBarScrollPane;
    @FXML
    private VBox topVBox;
    private WeekPane weekPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        time.setText(CurrentTime.getCurrentTime());
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1000), event -> updateTime())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        Day sixthOfApril = new Day(6, 4, 2003);
        Day lastDayofMarch = new Day(31, 3, 2003);
        lastDayofMarch.addEvent(new Event("Stuff", 0, 600, 1.0, 0.5 , 0));
        System.out.println("Last day of march number " + lastDayofMarch.getNumber());
        System.out.println("Number " + sixthOfApril.getNumber());
        Event birth = new Event("I was born", 360, 370, 1.0, 0.5, 0);
        Event death = new Event("I died", 400, 430, 0.5, 1.0, 0.2);
        sixthOfApril.addEvent(birth);
        sixthOfApril.addEvent(death);

        Day fifthOfApril = new Day(5, 4, 2003);
        Event dinner = new Event("Dinner with Grandma", 0, 1200, 0, 0,0);
        fifthOfApril.addEvent(dinner);

        DaysHashMap.addDay(lastDayofMarch);
        DaysHashMap.addDay(sixthOfApril);
        DaysHashMap.addDay(fifthOfApril);
        weekPane = new WeekPane(fifthOfApril.getNumber());
        HBox mainHBox = new HBox();
        mainHBox.getChildren().add(weekPane);
        borderPane.setCenter(mainHBox);
    }

    private void updateTime(){
        time.setText(CurrentTime.getCurrentTime());
    }
}