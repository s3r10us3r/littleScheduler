package scheduler.Nodes.WeekPane;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import scheduler.organize.DayOfTheWeek;

import java.util.SortedSet;

public class WeekPane extends BorderPane {
    private final int firstDayNumber;
    private ScrollPane mainScrollPane;
    private HBox eventHBox;
    private int currentForwardOffset = 0;
    private int currentBackwardsOffset = 0;
    private boolean finishedGeneration = true;
    public WeekPane(int dayNumber){
        eventHBox = new HBox();
        firstDayNumber = dayNumber - DayOfTheWeek.computeDayOfTheWeek(dayNumber).number + 1;
        prepareMainScrollPane();
        generateWeekBar();
        generateTimeTable();
        generateDayPanesForward(0);
        mainScrollPane.setContent(eventHBox);
        this.setCenter(mainScrollPane);
        System.out.println("FirstDayNumber: " + firstDayNumber);

        mainScrollPane.addEventHandler(ScrollEvent.ANY, (ScrollEvent event)->{
            if(mainScrollPane.hvalueProperty().getValue() == 1){
                currentForwardOffset += 7;
                generateDayPanesForward(currentForwardOffset);
                System.out.println("Generated forward");
            }
            if(mainScrollPane.hvalueProperty().getValue() == 0 && finishedGeneration){
                currentBackwardsOffset += 7;
                double prevWidth = eventHBox.getWidth();
                generateDayPanesBackwards(currentBackwardsOffset);
                finishedGeneration = false;

                Platform.runLater(()->{
                    double newWidth = eventHBox.getWidth();
                    double newHValue = (newWidth - prevWidth)/newWidth;
                    mainScrollPane.setHvalue(newHValue);
                    System.out.println("New hvalue " + newHValue);
                    finishedGeneration = true;
                });

                System.out.println("Generated backwards");
            }
        });

        mainScrollPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->{
            System.out.println(mainScrollPane.hvalueProperty().getValue());
            System.out.println(eventHBox.getWidth());
        });
    }

    private void generateDayPanesForward(int offset){
        for(int i = firstDayNumber + offset; i < firstDayNumber + 7 + offset; i++){
            eventHBox.getChildren().add(new DayPane(i));
        }
    }

    private void generateDayPanesBackwards(int offset){
        for(int i = firstDayNumber - offset - 1; i > firstDayNumber - 7 - offset; i--){
            eventHBox.getChildren().add(0,new DayPane(i));
        }
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
        WeekBar weekBar = new WeekBar(firstDayNumber);
        weekBar.hvalueProperty().bindBidirectional(mainScrollPane.hvalueProperty());
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


    public ScrollPane getMainScrollPane() {
        return mainScrollPane;
    }

    public void refresh(){
        generateDayPanesForward(0);
    }
}
