package scheduler.Nodes.MonthPane;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scheduler.appClasses.SchedulerApp;
import scheduler.organize.Date;
import scheduler.organize.Day;
import scheduler.organize.DaysHashMap;
import scheduler.organize.Event;

import java.util.ArrayList;

public class DayCell extends VBox {
    private final static Background activeBackground = new Background(new BackgroundFill(Color.WHITE, null, null));
    private Label numberLabel;

    public DayCell(){
        this.setMinSize(MonthPane.WIDTH / 7, MonthPane.HEIGHT / 6);
        this.setMaxSize(MonthPane.WIDTH / 7, MonthPane.HEIGHT / 6);
        prepareBorder();
    }
    public DayCell(int dayNumber){
        this.setMinSize(MonthPane.WIDTH / 7, MonthPane.HEIGHT / 6);
        this.setMaxSize(MonthPane.WIDTH / 7, MonthPane.HEIGHT / 6);
        this.setBackground(activeBackground);

        prepareBorder();


        Date date = new Date(dayNumber);

        numberLabel = new Label(String.valueOf(date.day));
        numberLabel.setFont(new Font(20));
        numberLabel.setTextFill(Color.DARKGRAY);
        numberLabel.setMaxHeight(30);
        this.getChildren().add(numberLabel);

        Day day = DaysHashMap.getDay(dayNumber);
        if(day != null) {
            int i = 0;
            ArrayList<Event> events = day.getEvents();
            for (Event event : events) {
                if(i == 5){
                    break;
                }
                Label label = new Label("•" + event.getName() + " ");
                double[] backgroundColorValues = event.getBackgroundColorValues();
                label.setTextFill(new Color(backgroundColorValues[0], backgroundColorValues[1], backgroundColorValues[2], 1));
                label.setFont(new Font(12));
                this.getChildren().add(label);
                i++;
            }
        }
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                moveToWeekPane(dayNumber);
            }
        });
    }

    private void prepareBorder(){
        Double[] borderWidths = new Double[4];
        borderWidths[0] = 0.0; borderWidths[1] = 1.0; borderWidths[2] = 1.0; borderWidths[3] = 1.0;

        this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(borderWidths[0], borderWidths[1], borderWidths[2], borderWidths[3]))));
    }

    private void moveToWeekPane(int number){
        SchedulerApp.focusedDate = new Date(number);
        SchedulerApp.setRoot("WeekPane");
    }
}