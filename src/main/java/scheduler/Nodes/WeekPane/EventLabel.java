package scheduler.Nodes.WeekPane;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scheduler.appClasses.SchedulerApp;
import scheduler.organize.DaysHashMap;
import scheduler.organize.Event;

public class EventLabel extends Label {
    private Event event;
    private DayPane parent;
    private SimpleDoubleProperty height;
    private SimpleDoubleProperty width;

    public EventLabel(Event event, DayPane parent){
        super();
        this.parent = parent;
        this.event = event;
        this.height = new SimpleDoubleProperty(event.getDuration() * 2);
        this.width = new SimpleDoubleProperty(200);
        this.setAlignment(Pos.CENTER);
        this.setFont(new Font( "Helvetica Bold", 15));
        this.setText(event.getName());
        Color textColor = new Color(event.getTextColorValues()[0], event.getTextColorValues()[1], event.getTextColorValues()[2], 1.0);
        this.setTextFill(textColor);
        generateLabel();
        generateContextMenu();
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

    public void refresh(){
        height.set(event.getDuration() * 2);
        setText(event.getName());
        Color backgroundColor = new Color(event.getBackgroundColorValues()[0], event.getBackgroundColorValues()[1], event.getBackgroundColorValues()[2], Event.opacityValue);
        this.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(0, 7, 7, 0, false), null)));
    }

    public Event getEvent(){
        return event;
    }

    private void generateContextMenu(){
        ContextMenu contextMenu = new ContextMenu();

        MenuItem editEventItem = new MenuItem("Edit event");
        editEventItem.setOnAction(event -> {
            EventCreator eventCreator = new EventCreator(this);
            eventCreator.show(SchedulerApp.stage);
        });

        contextMenu.getItems().add(editEventItem);

        MenuItem deleteEventItem = new MenuItem("Delete event");
        deleteEventItem.setOnAction(event -> {
            int dayNumber = parent.getNumber();
            DaysHashMap.getDay(dayNumber).deleteEvent(this.event);
            parent.deleteEventLabel(this);
        });
        contextMenu.getItems().add(deleteEventItem);

        this.setOnContextMenuRequested(event -> {
            contextMenu.show(this, Side.RIGHT, 0, event.getY());
            event.consume();
        });
    }

}
