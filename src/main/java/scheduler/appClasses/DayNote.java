package scheduler.appClasses;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scheduler.time.WeekDay;

import java.util.List;

public class DayNote extends VBox {
    private final WeekDay weekDay;
    public DayNote(WeekDay day){
        super();
        weekDay = day;
        this.setAlignment(Pos.TOP_CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        this.setOpacity(0.6);
        this.setMinSize(200, 200);
        this.setMaxSize(200, 200);
        this.getStyleClass().add("oval-box");
        addWeekDayHeader();
    }

    private void addWeekDayHeader(){
        Label header = new Label();
        header.setText(weekDay.getString());
        header.setTextFill(Color.WHITE);
        Font font  = new Font("Georgia", 35);
        header.setFont(font);
        this.getChildren().add(header);
        this.setOpacity(0.8);
    }
}
