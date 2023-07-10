package scheduler.Nodes.WeekPane;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import scheduler.organize.DayOfTheWeek;

public class WeekPane extends VBox {
    private final int firstDayNumber;
    private ScrollPane mainScrollPane;
    private HBox eventHBox;
    public WeekPane(int dayNumber){
        eventHBox = new HBox();
        firstDayNumber = dayNumber - DayOfTheWeek.computeDayOfTheWeek(dayNumber).number + 1;
        prepareMainScrollPane();
        generateWeekBar();
        generateDayPanes();
    }

    private void generateDayPanes(){
        for(int i = firstDayNumber; i < firstDayNumber + 7; i++){
            eventHBox.getChildren().add(new DayPane(i));
        }
        eventHBox.setMinHeight(2880);
        eventHBox.setMaxHeight(2880);
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
    }

    private void generateWeekBar(){
        WeekBar weekBar = new WeekBar(firstDayNumber);
        weekBar.hvalueProperty().bindBidirectional(mainScrollPane.hvalueProperty());
        this.getChildren().add(weekBar);
    }

    public ScrollPane getMainScrollPane() {
        return mainScrollPane;
    }

    public void refresh(){
        generateDayPanes();
    }
}
