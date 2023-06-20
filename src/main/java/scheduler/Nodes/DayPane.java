package scheduler.Nodes;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import scheduler.organize.Event;
import scheduler.organize.YearsSingleton;

public class DayPane extends Pane {
    private int year;
    private int month;
    private int day;
    public DayPane(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
        this.setMinSize(200,2880);
        this.setMaxSize(200, 2880);
        generateDayVbox();
    }

    private void generateDayVbox(){
        if(YearsSingleton.getYear(year) != null && YearsSingleton.getYear(year).getMonth(month) != null && YearsSingleton.getYear(year).getMonth(month).getDay(day) != null){
            for(Event event : YearsSingleton.getYear(year).getMonth(month).getDay(day).getEvents()){
                EventLabel eventLabel = new EventLabel(event, 200);
                eventLabel.setLayoutY(2 * event.getStartTime());
                this.getChildren().add(eventLabel);
                System.out.println(event.getName());
            }
        }
    }
}
