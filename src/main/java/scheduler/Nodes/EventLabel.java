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
        this.height = new SimpleDoubleProperty(((double)event.getFinishTime() - (double)event.getStartTime()) * 2);
        this.width = new SimpleDoubleProperty(width);
        this.maxHeightProperty().bind(this.height);
        this.minHeightProperty().bind(this.height);
        this.minWidthProperty().bind(this.width);
        this.maxWidthProperty().bind(this.width);
        this.setAlignment(Pos.CENTER);
        this.setFont(new Font( "Helvetica Bold", 10));
        generateLabel();
    }

    private void generateLabel(){
        this.setText(event.getName());
        Color backgroundColor = new Color(event.getBackgroundColorValues()[0], event.getBackgroundColorValues()[1], event.getBackgroundColorValues()[2], Event.opacityValue);
        Color textColor = new Color(event.getTextColorValues()[0], event.getTextColorValues()[1], event.getTextColorValues()[2], 1.0);
        this.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(0, 7, 7, 0, false), null)));
        System.out.println(this.getStyle());
        this.setTextFill(textColor);
    }
}
