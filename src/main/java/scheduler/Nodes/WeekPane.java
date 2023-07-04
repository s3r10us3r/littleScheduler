package scheduler.Nodes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import scheduler.organize.Day;
import scheduler.organize.DayOfTheWeek;
import scheduler.organize.DaysHashMap;

public class WeekPane extends VBox {
    private final int firstDayNumber;
    private ScrollPane mainScrollPane;
    private HBox eventHBox;
    public WeekPane(int dayNumber){
        eventHBox = new HBox();
        firstDayNumber = dayNumber - DayOfTheWeek.computeDayOfTheWeek(dayNumber).number + 1;
        prepareMainScrollPane();
       // generateWeekBar();
        generateDayPanes();
    }

    private void generateDayPanes(){
        for(int i = firstDayNumber; i < firstDayNumber + 7; i++){
            eventHBox.getChildren().add(new DayPane(i));
        }
        mainScrollPane.setContent(eventHBox);
        this.getChildren().add(mainScrollPane);
    }
    private void prepareMainScrollPane(){
        mainScrollPane = new ScrollPane();
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setFitToHeight(true);
        mainScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScrollPane.setPannable(true);
        System.out.println("MAIN SCROLL PANE: " + this.mainScrollPane.getWidth());
    }

    public ScrollPane generateWeekBar(){
        HBox weekBar = new HBox();
        ScrollPane weekBarScrollPane = new ScrollPane();
        int width = 250 * 7;
        int height = 150;
        Background background = new Background(new BackgroundFill(Color.WHITE, null, null));
        weekBar.setBackground(background);
        weekBar.setMinSize(width, height);
        weekBar.setMaxSize(width, height);
        this.setAlignment(Pos.TOP_CENTER);
        for(int i = 0; i < 7; i++){
            weekBar.getChildren().add(generateWeekdayVBox(DaysHashMap.getDay(firstDayNumber + i)));
        }

        weekBarScrollPane.setPannable(false);
        weekBarScrollPane.setContent(weekBar);
        weekBarScrollPane.setFitToHeight(true);
        weekBarScrollPane.setFitToWidth(true);
        weekBarScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        weekBarScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        weekBarScrollPane.hvalueProperty().bind(mainScrollPane.hvalueProperty());
        weekBarScrollPane.addEventFilter(ScrollEvent.ANY, ScrollEvent::consume);
        weekBarScrollPane.setMinHeight(100);
        weekBarScrollPane.setMaxHeight(100);
        return weekBarScrollPane;
    }

    private VBox generateWeekdayVBox(Day day){
        VBox weekDayVBox = new VBox();
        weekDayVBox.setMinSize(250, 100);
        weekDayVBox.setMaxSize(250, 100);
        weekDayVBox.setAlignment(Pos.CENTER);

        Label numberInMonth = new Label();
    }

    public ScrollPane getMainScrollPane() {
        return mainScrollPane;
    }

    public void refresh(){
        generateDayPanes();
    }
}
