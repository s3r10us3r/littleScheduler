package scheduler.time;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class CurrentTime {
    public static String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
