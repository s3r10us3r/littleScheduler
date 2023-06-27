package scheduler.organize;

import java.util.HashMap;

public class DaysHashMap {
    private final static HashMap<Integer, Day> days = new HashMap<>();//the day's number is the key

    public static Day getDay(int year, int month, int day){
        return days.get(Day.computeNumber(day, month, year));
    }

    public static Day getDay(int number){
        return days.get(number);
    }

    public static void addDay(Day day){
        days.put(day.getNumber(), day);
    }
}
