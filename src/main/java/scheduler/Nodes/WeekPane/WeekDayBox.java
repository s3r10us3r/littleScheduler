package scheduler.Nodes.WeekPane;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scheduler.organize.Date;
import scheduler.organize.Day;
import scheduler.organize.DayOfTheWeek;

public class WeekDayBox extends VBox {
    private Date date;
    private String name;
    private Label numberLabel;
    private Label nameLabel;

    private final static Border border = new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 1, 0, 0  )));
    public WeekDayBox(int dayNumber){
        date = new Date(dayNumber);
        name = DayOfTheWeek.computeDayOfTheWeek(dayNumber).longForm;

        makeNumberLabel();
        makeNameLabel();
        this.setMinSize(250, 100);
        this.setMaxSize(250, 100);
        this.setBorder(border);
    }

    private void makeNumberLabel(){
        int number = date.day;

        numberLabel = new Label();
        numberLabel.setText(Integer.toString(number));

        Font font = new Font("Helvetica now", 20);
        numberLabel.setFont(font);

        this.getChildren().add(numberLabel);
    }

    private void makeNameLabel(){
        nameLabel = new Label();
        nameLabel.setText(name);

        Font font = new Font("Helvetica now", 10);
        nameLabel.setFont(font);

        this.getChildren().add(nameLabel);
    }
}
