package scheduler.Nodes.WeekPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import scheduler.organize.DaysHashMap;
import scheduler.organize.Event;

public class EventCreator extends Popup {
    private VBox mainVBox;
    private ColorPicker colorPicker;
    private TextField nameTextField;
    private TimeTextField startingTimeTextField;
    private TimeTextField finishTimeTextField;
    public EventCreator(DayPane dayPane){
        setUp();

        Button createButton = new Button("Create Event");
        createButton.setOnAction(event -> {
            Color color = colorPicker.valueProperty().get();
            Event newEvent = new Event(nameTextField.getText(), startingTimeTextField.getTime(), finishTimeTextField.getTime(), color.getRed(), color.getGreen(), color.getBlue());
            dayPane.addEventLabel(newEvent);
            DaysHashMap.getDay(dayPane.getNumber()).addEvent(newEvent);
            this.hide();
        });

        mainVBox.getChildren().add(createButton);

        this.getContent().add(mainVBox);
    }


    public EventCreator(EventLabel eventLabel){
        setUp();

        Button editButton = new Button("Edit Event");
        editButton.setOnAction(eventAction -> {
            Color color = colorPicker.valueProperty().get();
            Event event = eventLabel.getEvent();
            event.reset(nameTextField.getText(), startingTimeTextField.getTime(), finishTimeTextField.getTime(), color.getRed(), color.getGreen(), color.getBlue());
            eventLabel.refresh();
            this.hide();
        });

        mainVBox.getChildren().add(editButton);

        this.getContent().add(mainVBox);
    }

    private void setUp(){
        this.hideOnEscapeProperty().set(true);
        this.autoHideProperty().set(true);

        mainVBox = new VBox();
        mainVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setSpacing(10);
        mainVBox.setPadding(new Insets(10));
        mainVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

        colorPicker = new ColorPicker();
        mainVBox.getChildren().add(colorPicker);

        HBox nameHBox = new HBox();
        nameHBox.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Name: ");
        nameTextField = new TextField();
        nameHBox.getChildren().addAll(nameLabel, nameTextField);
        mainVBox.getChildren().add(nameHBox);

        HBox startingTimeHBox = new HBox();
        startingTimeHBox.setAlignment(Pos.CENTER);
        Label startingTimeLabel = new Label("Starting time: ");
        startingTimeTextField = new TimeTextField();
        startingTimeHBox.getChildren().addAll(startingTimeLabel, startingTimeTextField);
        mainVBox.getChildren().add(startingTimeHBox);

        HBox finishTimeHBox = new HBox();
        finishTimeHBox.setAlignment(Pos.CENTER);
        Label finishTimeLabel = new Label("Finish time: ");
        finishTimeTextField = new TimeTextField();
        finishTimeHBox.getChildren().addAll(finishTimeLabel, finishTimeTextField);
        mainVBox.getChildren().add(finishTimeHBox);
    }

    private class TimeTextField extends TextField{
        private final static String TIME_PATTERN = "\\d{0,2}:\\d{0,2}";
        private String previousText = "00:00";
        public TimeTextField(){
            this.setText("00:00");

            setOnKeyTyped(event -> {
                String newText = getText();

                if(!newText.matches(TIME_PATTERN) || newText.length() > 5){
                    event.consume();
                    setText(previousText);
                }
                else{
                    previousText = newText;
                }
            });
        }

        public int getTime(){
            String[] array = getText().split(":");
            return Integer.parseInt(array[0]) * 60 + Integer.parseInt(array[1]);
        }
    }
}
