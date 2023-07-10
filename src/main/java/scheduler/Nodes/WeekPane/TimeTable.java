package scheduler.Nodes.WeekPane;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TimeTable extends ScrollPane {
    public TimeTable(){
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        this.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        generateVBox();
    }

    private void generateVBox() {
        VBox vbox = new VBox();
        vbox.setMinHeight(2880);
        vbox.setMaxHeight(2880);
        Label emptyLabel = new Label();
        emptyLabel.setMinHeight(60);
        emptyLabel.setMaxHeight(60);
        vbox.getChildren().add(emptyLabel);
        for (int i = 1; i < 24; i++) {
            Label hourLabel = new Label(String.format("%d:00", i));
            hourLabel.setMinSize(50, 120);
            hourLabel.setMaxSize(50, 120);
            vbox.getChildren().add(hourLabel);
        }
        Label emptyLabel2 = new Label();
        emptyLabel2.setMinHeight(60);
        emptyLabel2.setMaxHeight(60);
        vbox.getChildren().add(emptyLabel2);
        this.setContent(vbox);
    }
}
