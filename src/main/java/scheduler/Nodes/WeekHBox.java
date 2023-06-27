package scheduler.Nodes;

import javafx.scene.layout.HBox;
import scheduler.organize.Day;
import scheduler.organize.DayOfTheWeek;
import scheduler.organize.DaysHashMap;

public class WeekHBox extends HBox {
    private int firstDayNumber;
    public WeekHBox(int dayNumber){
        firstDayNumber = dayNumber - DayOfTheWeek.computeDayOfTheWeek(dayNumber).number + 1;
        generateDayPanes();
        this.setSpacing(2);
    }

    public void generateDayPanes(){
        for(int i = firstDayNumber; i < firstDayNumber + 7; i ++){
            getChildren().add(new DayPane(i));
        }
    }
}
