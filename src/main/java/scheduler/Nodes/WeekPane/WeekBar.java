package scheduler.Nodes.WeekPane;

import javafx.application.Platform;
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
    private int forwardOffset = 0;
    private int backwardOffset = 0;
    private HBox weekBarHBox;
    public WeekBar(int firstDayNumber){
        this.firstDayNumber = firstDayNumber;
        setUpScrollPane();
        generateWeekBarHBox();
    }
    
    private void generateWeekBarHBox(){
        int height = 150;
        
        weekBarHBox = new HBox();
        Background background = new Background(new BackgroundFill(Color.WHITE, null, null));

        weekBarHBox.setBackground(background);
        weekBarHBox.setMinHeight(height);
        weekBarHBox.setMaxHeight(height);
        for(int i = 0; i < 7; i++){
            weekBarHBox.getChildren().add(new WeekDayBox(firstDayNumber + i));
        }

        this.setContent(weekBarHBox);
    }

    public void generateWeekForward(){
        for(int i = firstDayNumber + forwardOffset; i < firstDayNumber + forwardOffset + 7; i++){
            weekBarHBox.getChildren().add(new WeekDayBox(i));
        }

        forwardOffset += 7;
    }

    public void generateWeekBackwards(){
        for(int i  = firstDayNumber - backwardOffset - 1; i > firstDayNumber - 8 - backwardOffset; i--){
            weekBarHBox.getChildren().add(0, new WeekDayBox(i));
        }

        backwardOffset += 7;
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
