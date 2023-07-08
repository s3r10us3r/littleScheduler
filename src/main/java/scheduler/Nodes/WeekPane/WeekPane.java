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

    public WeekBar generateWeekBar(){
        WeekBar weekBar = new WeekBar(firstDayNumber);
        weekBar.hvalueProperty().bind(mainScrollPane.hvalueProperty());
        return weekBar;
    }

    public ScrollPane getMainScrollPane() {
        return mainScrollPane;
    }

    public void refresh(){
        generateDayPanes();
    }
}
