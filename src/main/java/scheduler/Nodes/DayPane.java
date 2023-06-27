package scheduler.Nodes;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import scheduler.organize.Day;
import scheduler.organize.DaysHashMap;
import scheduler.organize.Event;

public class DayPane extends Pane {
    private int number;
    public DayPane(int year, int month, int day){
        this.number = Day.computeNumber(day, month, year);
        this.setMinSize(200,2880);
        this.setMaxSize(200, 2880);
        generateDayVbox();
    }

    public DayPane(int number){
        this.number = number;
        this.setMinSize(204,2880);
        this.setMaxSize(204, 2880);
        generateDayVbox();
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
    }

    private void generateDayVbox(){
        if(DaysHashMap.getDay(number) != null){
            for(Event event : DaysHashMap.getDay(number).getEvents()){
                EventLabel eventLabel = new EventLabel(event, 200);
                eventLabel.setLayoutY(2 * event.getStartTime());
                this.getChildren().add(eventLabel);
                System.out.println(event.getName());
            }
        }
    }
}
