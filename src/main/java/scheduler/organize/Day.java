package scheduler.organize;

import java.util.SortedSet;
import java.util.TreeSet;

public class Day implements Comparable<Day>{
    private SortedSet<Event> events;
    private int number; //from 31st December 1999
    private DayOfTheWeek dayOfTheWeek;
    public Day(int number){
        this.number = number;
        events = new TreeSet<>();
        this.dayOfTheWeek = DayOfTheWeek.computeDayOfTheWeek(number);
    }

    public Day(int day, int month, int year){
        this.number = computeNumber(day, month, year);
        events = new TreeSet<>();
        this.dayOfTheWeek = DayOfTheWeek.computeDayOfTheWeek(number);
    }

    public static int computeNumber(int day, int month, int year){
        int yearFrom2000 = year - 2000;
        int number = 0;
        if(year != 2000){
            number += 365 * yearFrom2000;
            number += yearFrom2000/4 + 1;
        }
        number += getDaysPassedThisYear(month, year);
        number += day - 1;//this is because first of January 2000 has number '0'
        return number;
    }

    private static int getDaysPassedThisYear(int month, int year){
        int result = 0;
        if(month > 1){
            result += 31;
        }
        if(month > 2){
            result += 28;
            if(year % 4 == 0){
                result += 1;
            }
        }
        if(month > 3){
            result += 31;
        }
        if(month > 4){
            result += 30;
        }
        if(month > 5){
            result += 31;
        }
        if(month > 6){
            result += 30;
        }
        if(month > 7){
            result += 31;
        }
        if(month > 8){
            result += 31;
        }
        if(month > 9){
            result += 30;
        }
        if(month > 10){
            result += 31;
        }
        if(month > 11){
            result += 30;
        }
        return result;
    }


    public boolean addEvent(Event event){
        if(eventCollidesWithOtherEvents(event)){
            return false;
        }
        else{
            events.add(event);
            return true;
        }
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

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}
