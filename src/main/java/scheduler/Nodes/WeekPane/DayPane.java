package scheduler.Nodes.WeekPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import scheduler.appClasses.HelloApplication;
import scheduler.organize.Date;
import scheduler.organize.Day;
import scheduler.organize.DaysHashMap;
import scheduler.organize.Event;

public class DayPane extends StackPane {
    private final int number;
    private final static Border dayPaneBorder = new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 1, 0, 0  )));
    private Pane eventPane;
    public DayPane(int year, int month, int day){
        this.number = Day.computeNumber(day, month, year);
        setUp();
    }

    public DayPane(int number){
        this.number = number;
        setUp();
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event)->{
            System.out.println(number);
            Date date = new Date(number);
            System.out.println(date.month + " " + date.getMonthName());
        });
    }

    private void setUp(){
        this.setMinSize(250,2880);
        this.setMaxSize(250, 2880);
        generateLines();
        generateDayVbox();
        this.setBorder(dayPaneBorder);
        generateContextMenu();
    }

    private void generateDayVbox(){
        eventPane = new Pane();
        if(DaysHashMap.getDay(number) != null){
            for(Event event : DaysHashMap.getDay(number).getEvents()){
                addEventLabel(event);
            }
        }
        this.getChildren().add(eventPane);
    }

    public void addEventLabel(Event event){
        EventLabel eventLabel = new EventLabel(event);
        eventLabel.setLayoutY(2 * event.getStartTime());
        eventPane.getChildren().add(eventLabel);
    }

    public void generateLines(){
        Group lines = new Group();
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

        setOnMouseClicked(event -> {
            contextMenu.show(this, Side.RIGHT, 0, 0);
        });
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
        eventCreator.show(HelloApplication.stage);
        System.out.println("It should have been shown");
    }

    public int getNumber(){
        return number;
    }
}
