package scheduler.appClasses;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import scheduler.Nodes.EventLabel;
import scheduler.organize.Event;
import scheduler.time.CurrentTime;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private VBox testView;
    @FXML
    private Label time;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        time.setText(CurrentTime.getCurrentTime());
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1000), event -> updateTime())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Event event = new Event("Test Label", 1, 2);
        Label testLabel = new EventLabel(event, 200, 200);
        testView.getChildren().add(testLabel);
    }

    private void updateTime(){
        time.setText(CurrentTime.getCurrentTime());
    }

}
