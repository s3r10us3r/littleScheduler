package scheduler.Nodes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scheduler.organize.Event;

public class EventLabel extends Label {
    private Event event;
    private SimpleDoubleProperty height;
    private SimpleDoubleProperty width;

    public EventLabel(Event event, int width){
        super();
        this.event = event;
        this.height = new SimpleDoubleProperty(((double)event.getFinishTime() - (double)event.getStartTime())/1440 * 700);
        this.width = new SimpleDoubleProperty(width);
        this.maxHeightProperty().bind(this.height);
        this.minHeightProperty().bind(this.height);
        this.minWidthProperty().bind(this.width);
        this.maxWidthProperty().bind(this.width);
        this.setAlignment(Pos.CENTER);
        this.setFont(new Font( "Helvetica Bold", 20));
        generateLabel();
    }

    private void generateLabel(){
        if(this.getHeight() > 25) {
            this.setText(event.getName());
        }
        Color backgroundColor = new Color(event.getBackgroundColorValues()[0], event.getBackgroundColorValues()[1], event.getBackgroundColorValues()[2], Event.opacityValue);
        Color textColor = new Color(event.getTextColorValues()[0], event.getTextColorValues()[1], event.getTextColorValues()[2], 1.0);
        int[] borderColor = {(int)(event.getBackgroundColorValues()[0] * 0.75 * 255), (int)(event.getBackgroundColorValues()[1] * 0.75 * 255), (int)(event.getBackgroundColorValues()[2] * 0.75 * 255)};
        this.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
        this.setStyle(String.format("-fx-border-color: rgb(%d, %d, %d); -fx-border-width: %dpx; -fx-border-style: solid;", borderColor[0], borderColor[1], borderColor[2], (int)(Math.min(this.height.get() * 0.1, this.width.get() * 0.1))));
        System.out.println(this.getStyle());
        this.setTextFill(textColor);
    }
}
