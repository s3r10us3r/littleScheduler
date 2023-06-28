package scheduler.Nodes;

import javafx.scene.layout.HBox;
import scheduler.organize.DayOfTheWeek;

public class WeekHBox extends HBox {
    private final int firstDayNumber;
    public WeekHBox(int dayNumber){
        firstDayNumber = dayNumber - DayOfTheWeek.computeDayOfTheWeek(dayNumber).number + 1;
        generateDayPanes();
    }

    private void generateDayPanes(){
        for(int i = firstDayNumber; i < firstDayNumber + 9; i++){
            getChildren().add(new DayPane(i));
        }
    }

    public void refresh(){
        generateDayPanes();
    }
}
