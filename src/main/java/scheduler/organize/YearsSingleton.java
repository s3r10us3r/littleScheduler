package scheduler.organize;

import java.util.Calendar;

public class YearsSingleton {
    private static Year[] years = new Year[Calendar.getInstance().get(Calendar.YEAR) - 2000];

    public static void addInitializedYear(Year year){
        if(years[year.getNumber() - 2000] != null){
            throw new IllegalArgumentException("This year has already been initialized");
        }
        years[year.getNumber() - 2000] = year;
        System.out.println("Year added");
    }

    public static Year getYear(int number){
        return years[number - 2000];
    }
}
