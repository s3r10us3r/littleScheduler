package scheduler.appClasses;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import scheduler.Nodes.MonthPane.MonthPane;
import scheduler.organize.Date;
import scheduler.time.CurrentTime;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MonthPaneController implements Initializable {
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Label time;
    @FXML
    private Label monthAndYearLabel;
    @FXML
    private VBox centralVBox;
    private MonthPane monthPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CurrentTime.setUpTimeLabel(time);
        updateMonthAndYearPane();
        monthPane = new MonthPane();
        centralVBox.getChildren().add(monthPane);
    }

    private void updateTime(){
        time.setText(CurrentTime.getCurrentTime());
    }

    private void updateMonthAndYearPane(){
        monthAndYearLabel.setText(SchedulerApp.focusedDate.getMonthName() + " " + SchedulerApp.focusedDate.year);
    }

    @FXML
    private void goLeft(){
        if(SchedulerApp.focusedDate.month == 1){
            SchedulerApp.focusedDate.month = 12;
            SchedulerApp.focusedDate.year--;
            SchedulerApp.focusedDate.day = 1;
        }
        else{
            SchedulerApp.focusedDate.month--;
        }
        Platform.runLater(()->{
            updateMonthAndYearPane();
            centralVBox.getChildren().remove(monthPane);
            monthPane = new MonthPane();
            centralVBox.getChildren().add(monthPane);
        });
    }

    @FXML
    private void goRight(){
        if(SchedulerApp.focusedDate.month == 12){
            SchedulerApp.focusedDate.month = 1;
            SchedulerApp.focusedDate.year++;
            SchedulerApp.focusedDate.day = 1;
        }
        else{
            SchedulerApp.focusedDate.month++;
        }
        Platform.runLater(()->{
            updateMonthAndYearPane();
            centralVBox.getChildren().remove(monthPane);
            monthPane = new MonthPane();
            centralVBox.getChildren().add(monthPane);
        });
    }
}
