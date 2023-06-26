package scheduler.organize;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Day implements Comparable<Day>{
    private SortedSet<Event> events;
    private int number; //in month
    private Month month;
    private DayOfTheWeek dayOfTheWeek;
    public Day(int number, Month month, DayOfTheWeek dayOfTheWeek){
        this.number = number;
        events = new TreeSet<>();
        this.dayOfTheWeek = dayOfTheWeek;
        this.month = month;
    }
    public boolean addEvent(Event event){
        if(eventCollidesWithOtherEvents(event)){
            return false;
        }
        else{
            events.add(event);
            System.out.println("Added event");
            return true;
        }
    }

    private boolean eventCollidesWithOtherEvents(Event event){
        for(Event e : events){
            if(event.getStartTime() < e.getFinishTime() && event.getStartTime() >= e.getStartTime()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Day o){
        return number - o.getNumber();
    }

    public int getNumber() {
        return number;
    }

    public SortedSet<Event> getEvents() {
        return events;
    }

    public Month getMonth(){
        return month;
    }

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}
