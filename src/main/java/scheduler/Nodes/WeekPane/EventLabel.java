package scheduler.Nodes.WeekPane;

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
        this.height = new SimpleDoubleProperty(event.getDuration() * 2);
        this.width = new SimpleDoubleProperty(width);
        this.setAlignment(Pos.CENTER);
        this.setFont(new Font( "Helvetica Bold", 10));
        this.setText(event.getName());
        Color textColor = new Color(event.getTextColorValues()[0], event.getTextColorValues()[1], event.getTextColorValues()[2], 1.0);
        this.setTextFill(textColor);
        generateLabel();
    }


    //TODO: MAKE BULLET LABEL GENERATION
    private void generateLabel(){
        this.maxHeightProperty().bind(this.height);
        this.minHeightProperty().bind(this.height);
        this.minWidthProperty().bind(this.width);
        this.maxWidthProperty().bind(this.width);
        Color backgroundColor = new Color(event.getBackgroundColorValues()[0], event.getBackgroundColorValues()[1], event.getBackgroundColorValues()[2], Event.opacityValue);
        this.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(0, 7, 7, 0, false), null)));
    }
}
