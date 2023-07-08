package scheduler.Nodes.WeekPane;

import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
    }

    private void setUp(){
        this.setMinSize(250,2880);
        this.setMaxSize(250, 2880);
        generateLines();
        generateDayVbox();
        this.setBorder(dayPaneBorder);
    }

    private void generateDayVbox(){
        eventPane = new Pane();
        if(DaysHashMap.getDay(number) != null){
            for(Event event : DaysHashMap.getDay(number).getEvents()){
                EventLabel eventLabel = new EventLabel(event, 200);
                eventLabel.setLayoutY(2 * event.getStartTime());
                eventPane.getChildren().add(eventLabel);
                System.out.println(event.getName());
            }
        }
        this.getChildren().add(eventPane);
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

    public int getNumber(){
        return number;
    }
}
