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
        this.width = new SimpleDoubleProperty(200);
        this.setFont(new Font( "Helvetica Bold", 15));
        if(event.getDuration() < 10){
            generateBulletLabel();
        }
        else {
            generateLabel();
        }
        generateContextMenu();
    }

    private void generateBulletLabel(){
        this.setText("• " + event.getName());
        this.setAlignment(Pos.CENTER_LEFT);
        Color textColor = new Color(event.getBackgroundColorValues()[0], event.getBackgroundColorValues()[1], event.getBackgroundColorValues()[2], 1);
        this.setTextFill(textColor);
        this.setTranslateY(-11);
    }

    private void generateLabel(){
        this.height = new SimpleDoubleProperty(event.getDuration() * 2);
        this.setText(event.getName());
        this.setAlignment(Pos.CENTER);
        this.maxHeightProperty().bind(this.height);
        this.minHeightProperty().bind(this.height);
        this.minWidthProperty().bind(this.width);
        this.maxWidthProperty().bind(this.width);
        Color backgroundColor = new Color(event.getBackgroundColorValues()[0], event.getBackgroundColorValues()[1], event.getBackgroundColorValues()[2], Event.opacityValue);
        this.setTextFill(Color.WHITE);
        this.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(0, 7, 7, 0, false), null)));
    }

    public void refresh(){
        this.maxHeightProperty().unbind();
        this.maxWidthProperty().unbind();
        this.minWidthProperty().unbind();
        this.minHeightProperty().unbind();
        this.height = new SimpleDoubleProperty(event.getDuration() * 2);
        this.width = new SimpleDoubleProperty(200);
        this.backgroundProperty().set(null);
        if(event.getDuration() < 10){
            generateBulletLabel();
        }
        else {
            generateLabel();
        }
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