package scheduler.Nodes.WeekPane;

import javafx.geometry.Bounds;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import scheduler.organize.Date;
import scheduler.organize.DayOfTheWeek;

import java.util.ArrayList;

public class WeekPane extends BorderPane {
    private final int firstDayNumber;
    private ScrollPane mainScrollPane;
    private HBox eventHBox;
    private WeekBar weekBar;
    private final int WIDTH = 100;
    public WeekPane(int dayNumber){
        eventHBox = new HBox();
        firstDayNumber = dayNumber - DayOfTheWeek.computeDayOfTheWeek(dayNumber).number + 1;
        prepareMainScrollPane();
        generateDayPanes();
        generateWeekBar();
        generateTimeTable();
        mainScrollPane.setContent(eventHBox);
        this.setCenter(mainScrollPane);
        this.center();
        System.out.println("FirstDayNumber: " + firstDayNumber);
    }

    private void prepareMainScrollPane(){
        mainScrollPane = new ScrollPane();
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setFitToHeight(true);
        mainScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScrollPane.setPannable(true);
    }

    private void generateWeekBar(){
        weekBar = new WeekBar(firstDayNumber, WIDTH);
        weekBar.hvalueProperty().bind(mainScrollPane.hvalueProperty());
        HBox hbox = new HBox();
        Pane emptyPane = new Pane();
        emptyPane.setMinWidth(52);
        emptyPane.setMaxWidth(52);
        hbox.getChildren().addAll(emptyPane, weekBar);
        this.setTop(hbox);
    }

    private void generateTimeTable(){
        TimeTable timeTable = new TimeTable();
        timeTable.vvalueProperty().bindBidirectional(mainScrollPane.vvalueProperty());
        this.setLeft(timeTable);
    }

    private void generateDayPanes(){
        for(int i = Math.max(firstDayNumber - WIDTH, 0); i < firstDayNumber + WIDTH + 7; i++){
            eventHBox.getChildren().add(new DayPane(i));
        }
    }

    private void center(){
        mainScrollPane.setHvalue(0.5);
    }

    public void setUpMonthAndDayLabel(Label label){
        Date todaysDate = new Date(firstDayNumber);
        label.setText(todaysDate.getMonthName() + ", " + todaysDate.year);

        mainScrollPane.hvalueProperty().addListener((observable, oldValue, newValue) ->{
            ArrayList <Integer> dayNumbers = new ArrayList<>();
            for(int i = 0; i < eventHBox.getChildren().size(); i++){
                DayPane dayPane = (DayPane) eventHBox.getChildren().get(i);
                Bounds nodeBounds = dayPane.localToScene(dayPane.getBoundsInLocal(), true);
                if( mainScrollPane.getBoundsInLocal().intersects(nodeBounds)){
                    dayNumbers.add(dayPane.getNumber());
                }
            }

            for(int number : dayNumbers){
                Date date = new Date(number);
                if(date.getMonthNumberOfDays() - date.day == 4 || date.day == 3){
                    label.setText(date.getMonthName() + ", " + date.year);
                }
            }
        });
    }

    public ScrollPane getMainScrollPane() {
        return mainScrollPane;
    }
}
