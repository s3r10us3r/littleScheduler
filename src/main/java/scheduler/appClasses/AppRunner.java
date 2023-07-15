package scheduler.appClasses;

import scheduler.time.CurrentTime;

public class AppRunner {
    public static void main(String[] args){
        System.out.println(CurrentTime.getCurrentTime());
        HelloApplication.main(null);
    }
}