package scheduler.organize;

import java.io.Serializable;
import java.util.ArrayList;

public class Day implements Serializable {
    protected ArrayList<Event> events;
    protected int number; //from 31st December 1999
    protected DayOfTheWeek dayOfTheWeek;
    public Day(int number){
        this.number = number;
        events = new ArrayList<>();
        this.dayOfTheWeek = DayOfTheWeek.computeDayOfTheWeek(number);
    }

    public static int computeNumber(int day, int month, int year){
        Date date = new Date(year, month, day);
        return date.computeNumber();
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void deleteEvent(Event event){
        events.remove(event);
    }

    private boolean eventCollidesWithOtherEvents(Event event){
        for(Event e : events){
            if(event.getStartTime() < e.getFinishTime() && event.getStartTime() >= e.getStartTime()){
                return true;
            }
        }
        return false;
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
