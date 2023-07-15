package scheduler.Nodes.WeekPane;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class WeekBar extends ScrollPane {
    private int firstDayNumber;
    private HBox weekBarHBox;
    private final int WIDTH;
    public WeekBar(int firstDayNumber, int width){
        this.firstDayNumber = firstDayNumber;
        this.WIDTH = width;
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
        for(int i = Math.max(firstDayNumber - WIDTH, 0); i < firstDayNumber + WIDTH + 7; i++){
            weekBarHBox.getChildren().add(new WeekDayBox(i));
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
