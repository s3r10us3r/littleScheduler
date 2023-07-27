package scheduler.Nodes.MonthPane;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import scheduler.appClasses.SchedulerApp;
import scheduler.organize.Date;
import scheduler.organize.DayOfTheWeek;

public class MonthPane extends GridPane {
    public static final double WIDTH = 1000;
    public static final double HEIGHT = 600;
    public MonthPane(){
        ColumnConstraints constraint = new ColumnConstraints();
        constraint.setPercentWidth((double) 1 /7 * 100);
        this.getColumnConstraints().add(constraint);

        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPercentHeight((double) 1/6 * 100);
        this.getRowConstraints().add(rowConstraint);

        this.setMinSize(WIDTH, HEIGHT);
        this.setMaxSize(WIDTH, HEIGHT);

        Date firstDayDate = SchedulerApp.focusedDate.copy();
        firstDayDate.day = 1;
        DayOfTheWeek firstDayOfTheWeek = DayOfTheWeek.computeDayOfTheWeek(firstDayDate.computeNumber());

        int passed = 0;
        int column = firstDayOfTheWeek.number;
        int currentNumber = firstDayDate.computeNumber();
        for(int i = 0; i < column; i++){
            this.add(new DayCell(), i, 0);
        }

        for(int i = 0; i < 6; i++){
            for(int j = column; j < 7; j++){
                if(passed < firstDayDate.getMonthNumberOfDays()){
                    this.add(new DayCell(currentNumber++), j, i);
                }
                else{
                    this.add(new DayCell(), j, i);
                }
                passed++;
                column = 0;
            }
        }
    }
}
