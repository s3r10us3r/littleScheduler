package scheduler.appClasses;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.util.Duration;
import scheduler.Nodes.WeekPane.WeekBar;
import scheduler.Nodes.WeekPane.WeekPane;
import scheduler.organize.*;
import scheduler.time.CurrentTime;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
public class WeekPaneController implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label time;
    @FXML
    private ScrollPane weekBarScrollPane;
    @FXML
    private VBox topVBox;
    @FXML
    private Label monthAndYearLabel;
    public static WeekPane weekPane;
    public static WeekBar weekBar;
    public static int TODAYSNUMBER;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        time.setText(CurrentTime.getCurrentTime());
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1000), event -> updateTime())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        LocalDate today = LocalDate.now();
        Date date = new Date(today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        TODAYSNUMBER = date.computeNumber();

        DaysHashMap.load();

        weekPane = new WeekPane(TODAYSNUMBER);
        weekPane.setUpMonthAndDayLabel(monthAndYearLabel);
        HBox mainHBox = new HBox();
        mainHBox.getChildren().add(weekPane);
        borderPane.setCenter(mainHBox);
    }

    private void updateTime(){
        time.setText(CurrentTime.getCurrentTime());
    }
}