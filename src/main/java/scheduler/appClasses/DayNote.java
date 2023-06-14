package scheduler.appClasses;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import scheduler.time.WeekDay;

public class DayNote extends VBox {
    private final WeekDay weekDay;
    public DayNote(WeekDay day){
        super();
        weekDay = day;
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        this.setOpacity(0.6);
        this.setMinSize(300, 200);
        this.setMaxSize(300, 200);
        this.getStyleClass().add("oval-box");
        addWeekDayHeader();
    }

    private void addWeekDayHeader(){
        Label header = new Label();
        header.setText(weekDay.getString());
        header.setTextFill(Color.RED);
        this.getChildren().add(header);
    }
}
