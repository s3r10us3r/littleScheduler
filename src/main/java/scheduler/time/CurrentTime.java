package scheduler.time;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class CurrentTime {
    public static String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static int getCurrentTimeAsNumber(){
        String timeString = getCurrentTime();
        String[] pair = timeString.split(":");
        String hourString = pair[0];
        String minuteString = pair[1];
        return Integer.parseInt(hourString) * 60 + Integer.parseInt(minuteString);
    }
}
