package scheduler.Nodes.WeekPane;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import scheduler.organize.DaysHashMap;

public class WeekBar extends ScrollPane {
    private int firstDayNumber;
    public WeekBar(int firstDayNumber){
        this.firstDayNumber = firstDayNumber;
        setUpScrollPane();
        generateWeekBarHBox();
    }
    
    private void generateWeekBarHBox(){
        int height = 150;
        
        HBox weekBarHBox = new HBox();
        Background background = new Background(new BackgroundFill(Color.WHITE, null, null));

        weekBarHBox.setBackground(background);
        weekBarHBox.setMinHeight(height);
        weekBarHBox.setMaxHeight(height);
        for(int i = 0; i < 7; i++){
            weekBarHBox.getChildren().add(new WeekDayBox(firstDayNumber + i));
        }

        this.setContent(weekBarHBox);
    }

    private void setUpScrollPane(){
        setPannable(false);
        setFitToHeight(true);
        setFitToWidth(true);
        setVbarPolicy(ScrollBarPolicy.NEVER);
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setMinHeight(100);
        setMaxHeight(100);
    }
}
