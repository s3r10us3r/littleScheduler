package scheduler.Nodes.WeekPane;

import javafx.collections.transformation.SortedList;
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
    private Label warningLabel;
    public EventCreator(DayPane dayPane){
        setUp();

        Button createButton = new Button("Create Event");
        createButton.setOnAction(event -> {
            if(checkIfInputIsCorrect()) {
                Color color = colorPicker.valueProperty().get();
                Event newEvent = new Event(nameTextField.getText(), startingTimeTextField.getTime(), finishTimeTextField.getTime(), color.getRed(), color.getGreen(), color.getBlue());
                dayPane.addEventLabel(newEvent);
                DaysHashMap.getDay(dayPane.getNumber()).addEvent(newEvent);
                this.hide();
            }
        });

        mainVBox.getChildren().add(createButton);

        this.getContent().add(mainVBox);
    }


    public EventCreator(EventLabel eventLabel){
        setUp();

        Button editButton = new Button("Edit Event");

        Event eventToEdit = eventLabel.getEvent();

        double[] colorValues = eventToEdit.getBackgroundColorValues();
        colorPicker.valueProperty().set(new Color(colorValues[0], colorValues[1], colorValues[2], 1));

        nameTextField.setText(eventToEdit.getName());

        String startingHour = String.valueOf(eventToEdit.getStartTime() / 60);
        String startingMinute = String.valueOf(eventToEdit.getStartTime() % 60);
        if(startingHour.length() == 1){
            startingHour = "0" + startingHour;
        }
        if(startingMinute.length() == 1){
            startingMinute = "0" + startingMinute;
        }
        startingTimeTextField.setText(startingHour+":"+startingMinute);

        String finishHour = String.valueOf(eventToEdit.getFinishTime() / 60);
        String finishMinute = String.valueOf(eventToEdit.getFinishTime() % 60);
        if(finishHour.length() == 1){
            finishHour = "0" + finishHour;
        }
        if(finishMinute.length() == 1){
            finishMinute = "0" + finishMinute;
        }

        finishTimeTextField.setText(finishHour+":"+finishMinute);



        editButton.setOnAction(eventAction -> {
            if(checkIfInputIsCorrect()) {
                Color color = colorPicker.valueProperty().get();
                Event event = eventLabel.getEvent();
                event.reset(nameTextField.getText(), startingTimeTextField.getTime(), finishTimeTextField.getTime(), color.getRed(), color.getGreen(), color.getBlue());
                eventLabel.refresh();
                this.hide();
            }
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

        warningLabel = new Label();
        warningLabel.setTextFill(Color.RED);
        mainVBox.getChildren().add(warningLabel);
    }

    private boolean checkIfInputIsCorrect(){
        if(nameTextField.getText().equals("")){
            makeWarning("INPUT A NAME");
            return false;
        }
        if(startingTimeTextField.getTime() > finishTimeTextField.getTime()){
            makeWarning("INVALID TIME");
            return false;
        }
        return true;
    }

    private void makeWarning(String warning){
        warningLabel.setText(warning);
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