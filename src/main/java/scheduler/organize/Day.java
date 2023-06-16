package scheduler.organize;

import java.util.SortedSet;
import java.util.TreeSet;

public class Day implements Comparable<Day>{
    SortedSet<Event> events;
    int number; //in month
    DayOfTheWeek dayOfTheWeek;
    public Day(int number, DayOfTheWeek dayOfTheWeek){
        this.number = number;
        events = new TreeSet<>();
        this.dayOfTheWeek = dayOfTheWeek;
    }
    public void addEvent(Event event){
        events.add(event);
    }
    public SortedSet<Event> getEvents() {
        return events;
    }

    @Override
    public int compareTo(Day o){
        return number - o.getNumber();
    }

    public int getNumber() {
        return number;
    }
}
