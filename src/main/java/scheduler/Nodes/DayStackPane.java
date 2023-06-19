package scheduler.Nodes;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import scheduler.organize.Event;
import scheduler.organize.YearsSingleton;

public class DayStackPane extends StackPane {
    private int year;
    private int month;
    private int day;
    public DayStackPane(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private void generateStackPane(){
        this.minHeight(700);
        this.maxHeight(700);
        this.minWidth(200);
        this.maxWidth(200);
    }

    private void generateDayVbox(){
        if(YearsSingleton.getYear(year) != null && YearsSingleton.getYear(year).getMonth(month) != null && YearsSingleton.getYear(year).getMonth(month).getDay(day) != null){
            VBox dayVbox = new VBox();
            dayVbox.setMinSize(200, 700);
            dayVbox.setMaxSize(200, 700);
            for(Event event : YearsSingleton.getYear(year).getMonth(month).getDay(day).getEvents()){
                this.getChildren().add(new EventLabel(event, 200));
            }

        }
    }
}
