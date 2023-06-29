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
import scheduler.Nodes.WeekHBox;
import scheduler.organize.*;
import scheduler.time.CurrentTime;

import java.net.URL;
import java.util.ResourceBundle;


//TODO: MAKE TIME TABLE ON THE LEFT BETTER
public class MainController implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label time;
    @FXML
    private ScrollPane mainScrollPane;
    @FXML
    private VBox timeTable;
    @FXML
    private ScrollPane timeScrollPane;
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


        Day monday  = new Day(31, 3, 2003);
        DaysHashMap.addDay(monday);
        DaysHashMap.addDay(sixthOfApril);
        DaysHashMap.addDay(fifthOfApril);
        WeekHBox week = new WeekHBox(fifthOfApril.getNumber());
        mainScrollPane.setContent(week);
        prepareTimeTable();
    }

    private void updateTime(){
        time.setText(CurrentTime.getCurrentTime());
    }

    private void prepareTimeTable(){
        Background background = new Background(new BackgroundFill(Color.WHITE, null, null));

        timeTable.setBackground(background);
        for(int i = 1; i < 24; i ++){
            Label label = new Label(i + ":" + "00");
            label.setMinWidth(50);
            label.setMaxWidth(50);
            label.setMinHeight(120);
            label.setMaxHeight(120);
            label.setTranslateY(60);
            System.out.println(label.getHeight());
            timeTable.getChildren().add(label);
        }

        timeScrollPane.vvalueProperty().bind(mainScrollPane.vvalueProperty());
        timeScrollPane.addEventFilter(ScrollEvent.ANY, ScrollEvent::consume);
    }
}