package scheduler.Nodes.WeekPane;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import scheduler.appClasses.SchedulerApp;
import scheduler.appClasses.WeekPaneController;
import scheduler.organize.Day;
import scheduler.organize.DaysHashMap;
import scheduler.organize.Event;
import scheduler.time.CurrentTime;

import java.util.HashMap;

public class DayPane extends StackPane {
    private final int number;
    private final static Border dayPaneBorder = new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 1, 0, 0  )));
    private Group lines;
    private Pane eventPane;
    private Line timeLine;
    private Timeline timereset;
    private SimpleDoubleProperty timeLinePos;
    public DayPane(int year, int month, int day){
        this.number = Day.computeNumber(day, month, year);
        setUp(number == WeekPaneController.TODAYSNUMBER);
    }

    public DayPane(int number){
        this.number = number;
        setUp(number == WeekPaneController.TODAYSNUMBER);
    }

    private void setUp(boolean isToday){
        System.out.println("SETTING UP ANOTHER ONE: " + this.getNumber());
        this.setMinSize(250,2880);
        this.setMaxSize(250, 2880);
        generateLines();
        generateDayVbox();
        this.setBorder(dayPaneBorder);
        generateContextMenu();

        if(isToday){
            generateChangesForToday();
        }
    }

    private void generateDayVbox(){
        eventPane = new Pane();
        eventPane.setMinSize(250, 2880);
        eventPane.setMaxSize(250, 2880);
        if(DaysHashMap.getDay(number) != null){
            for(Event event : DaysHashMap.getDay(number).getEvents()){
                addEventLabel(event);
            }
        }
        this.getChildren().add(eventPane);
    }

    public void addEventLabel(Event event){
        EventLabel eventLabel = new EventLabel(event, this);
        eventLabel.setLayoutY(2 * event.getStartTime());
        eventPane.getChildren().add(eventLabel);
    }

    public void deleteEventLabel(EventLabel eventLabel){
        eventPane.getChildren().remove(eventLabel);
    }

    public void generateLines(){
        lines = new Group();
        for(int i = 120; i < 60 * 2 * 24; i+= 120){
            Line line = new Line(this.getLayoutX(), i, this.getLayoutX()+250, i);
            line.setStroke(Color.LIGHTGRAY);
            lines.getChildren().add(line);
        }
        this.getChildren().add(lines);
    }

    private void generateContextMenu(){
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(addEventToThisDayMenuItem());

        setOnContextMenuRequested(event -> contextMenu.show(this, Side.RIGHT, 0, 0));
    }

    private MenuItem addEventToThisDayMenuItem(){
        MenuItem menuItem = new MenuItem("Add event");
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addNewEvent();
            }
        });
        return menuItem;
    }

    public void addNewEvent(){
        Day day = DaysHashMap.getDay(number);
        if(day == null){
            day = new Day(number);
            DaysHashMap.addDay(day);
        }
        EventCreator eventCreator = new EventCreator(this);
        eventCreator.show(SchedulerApp.stage);
    }

    public int getNumber(){
        return number;
    }

    private void generateChangesForToday(){
        timeLine = new Line();
        timeLine.setStroke(Color.BLUE);

        timeLine.setStartX(eventPane.getLayoutX());
        timeLine.setEndX(eventPane.getLayoutX() + 250);
        timeLinePos = new SimpleDoubleProperty();

        timeLine.startYProperty().bind(timeLinePos);
        timeLine.endYProperty().bind(timeLinePos);

        eventPane.getChildren().add(0, timeLine);


        timereset = new Timeline(
                new KeyFrame(Duration.millis(1000), event -> {
                    if (timeLinePos.get() / 2 == 23 * 60 + 59 && CurrentTime.getCurrentTimeAsNumber() == 0) {
                        stopBeingHighlightedAsToday();
                    } else {
                        timeLinePos.set(CurrentTime.getCurrentTimeAsNumber() * 2);
                    }
                })
        );

        timereset.setCycleCount(Timeline.INDEFINITE);
        timereset.play();
    }

    public void setUpAsToday(){
        System.out.println("I was fired on: " + this.number);
        this.getChildren().clear();
        setUp(true);
    }

    public void stopBeingHighlightedAsToday(){
        timereset.stop();
        eventPane.getChildren().remove(timeLine);
        HashMap<Integer, DayPane> hashMap = WeekPaneController.weekPane.getDayPaneHashMap();
        hashMap.get(this.number + 1).setUpAsToday();
        WeekPaneController.weekPane.getWeekBar().getWeekDayBoxArrayList().get(number - WeekPaneController.weekPane.getFirstDayNumber() + WeekPane.WIDTH + 1).setUpAsToday();
        WeekPaneController.weekPane.getWeekBar().getWeekDayBoxArrayList().get(number - WeekPaneController.weekPane.getFirstDayNumber() + WeekPane.WIDTH).stopBeingToday();
    }
}