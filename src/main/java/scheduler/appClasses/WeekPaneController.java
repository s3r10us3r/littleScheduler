package scheduler.appClasses;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
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
        CurrentTime.setUpTimeLabel(time);

        LocalDate today = LocalDate.now();
        Date date = new Date(today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        TODAYSNUMBER = date.computeNumber();

        DaysHashMap.load();

        weekPane = new WeekPane(SchedulerApp.focusedDate.computeNumber());
        weekPane.setUpMonthAndDayLabel(monthAndYearLabel);
        HBox mainHBox = new HBox();
        mainHBox.getChildren().add(weekPane);
        borderPane.setCenter(mainHBox);
    }

    @FXML
    protected void changePane(){
        SchedulerApp.setRoot("MonthPane");
    }
}