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
    private VBox timeTable;
    @FXML
    private ScrollPane timeScrollPane;
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
        System.out.println("Number " + sixthOfApril.getNumber());
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
        weekPane = new WeekPane(fifthOfApril.getNumber());
        borderPane.setCenter(weekPane);
        prepareTimeTable();
    }

    private void updateTime(){
        time.setText(CurrentTime.getCurrentTime());
    }

    private void prepareTimeTable() {
        Background background = new Background(new BackgroundFill(Color.WHITE, null, null));
        timeScrollPane.translateYProperty().set(100);
        timeTable.setBackground(background);
        timeTable.setMinHeight(2880);
        timeTable.setMaxHeight(2880);


        Label emptyLabel1 = new Label();
        emptyLabel1.setMinHeight(60);
        emptyLabel1.setMaxHeight(60);
        timeTable.getChildren().add(emptyLabel1);

        for (int i = 1; i < 24; i++) {
            Label label = new Label(String.format("%02d:00", i));
            label.setMinWidth(50);
            label.setMaxWidth(50);
            label.setMinHeight(120);
            label.setMaxHeight(120);
            timeTable.getChildren().add(label);
        }


        Label emptyLabel2 = new Label();
        emptyLabel2.setMinHeight(60);
        emptyLabel2.setMaxHeight(60);
        timeTable.getChildren().add(emptyLabel2);

        System.out.println("Heights: " + timeTable.getHeight() + " " + weekPane.getHeight());

        timeScrollPane.vvalueProperty().bindBidirectional(weekPane.getMainScrollPane().vvalueProperty());
        timeScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        timeScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        timeScrollPane.setContent(timeTable);
        timeScrollPane.setFitToHeight(true);
        timeScrollPane.setFitToWidth(true);
        timeScrollPane.addEventFilter(ScrollEvent.ANY, (ScrollEvent event) -> {
            System.out.println(timeScrollPane.getVvalue() + " " + weekPane.getMainScrollPane().getVvalue());
            event.consume();
        });
    }
}