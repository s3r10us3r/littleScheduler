package scheduler.time;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class ResetTime extends Thread{
    private Label label;
    private int interval;

    public ResetTime(Label label, int intervalInMillis){
        this.label = label;
        this.interval = interval;
    }
    @Override
    public void run(){
        while(!isInterrupted()){
            try{
                Thread.sleep(interval);
                Platform.runLater(()-> {label.setText(CurrentTime.getCurrentTime());});
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
