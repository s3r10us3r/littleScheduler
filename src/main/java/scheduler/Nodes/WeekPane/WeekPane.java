package scheduler.Nodes.WeekPane;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import scheduler.organize.DayOfTheWeek;

public class WeekPane extends BorderPane {
    private final int firstDayNumber;
    private ScrollPane mainScrollPane;
    private HBox eventHBox;
    private WeekBar weekBar;
    private int forwardOffset = 0;
    private int backwardsOffset = 0;
    private boolean finishedGeneration = true;
    public WeekPane(int dayNumber){
        eventHBox = new HBox();
        firstDayNumber = dayNumber - DayOfTheWeek.computeDayOfTheWeek(dayNumber).number + 1;
        prepareMainScrollPane();
        generateWeekBar();
        generateTimeTable();
        generateDayPanesForward();
        mainScrollPane.setContent(eventHBox);
        this.setCenter(mainScrollPane);
        System.out.println("FirstDayNumber: " + firstDayNumber);

        mainScrollPane.addEventHandler(ScrollEvent.ANY, (ScrollEvent event)->{
            if(mainScrollPane.hvalueProperty().getValue() == 1){
                weekBar.generateWeekForward();
                generateDayPanesForward();
                System.out.println("Generated forward");
                System.out.println(mainScrollPane.getHvalue());
            }
            if(mainScrollPane.hvalueProperty().getValue() == 0 && finishedGeneration){
                weekBar.generateWeekBackwards();
                generateDayPanesBackwards();
                System.out.println("Generated backwards");
            }
        });

        mainScrollPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->{
            System.out.println(mainScrollPane.hvalueProperty().getValue());
            System.out.println(eventHBox.getWidth());
            System.out.println(mainScrollPane.getWidth() + " " + mainScrollPane.getViewportBounds());
        });
    }

    private void generateDayPanesForward(){
        for(int i = firstDayNumber + forwardOffset; i < firstDayNumber + 7 + forwardOffset; i++){
            eventHBox.getChildren().add(new DayPane(i));
        }

        forwardOffset += 7;
    }

    private void generateDayPanesBackwards(){
        double prevWidth = eventHBox.getWidth();

        for(int i = firstDayNumber - backwardsOffset - 1; i > firstDayNumber - 8 - backwardsOffset; i--){
            eventHBox.getChildren().add(0,new DayPane(i));
        }

        backwardsOffset += 7;
        finishedGeneration = false;


        Platform.runLater(() -> {
            double newWidth = eventHBox.getWidth();
            double newHValue = (newWidth - prevWidth) / (newWidth - mainScrollPane.getWidth());
            System.out.println("New hvalue: " + newHValue);

            mainScrollPane.setHvalue(newHValue);
            finishedGeneration = true;
        });
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
        weekBar = new WeekBar(firstDayNumber);
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
        generateDayPanesForward();
    }
}
