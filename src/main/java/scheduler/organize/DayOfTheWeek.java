package scheduler.organize;

public enum DayOfTheWeek {
    MONDAY("Monday", "MON", 1), TUESDAY("Tuesday", "TUE", 2), WEDNESDAY("Wednesday", "WED", 3),
    THURSDAY("Thursday", "THUR", 4), FRIDAY("Friday", "FRI", 5), SATURDAY("Saturday", "SAT", 6), SUNDAY("Sunday", "SUN", 7);

    public final String longForm;
    public final String shortForm;

    public final int number;
    DayOfTheWeek(String longForm, String shortForm, int number){
        this.longForm = longForm;
        this.shortForm = shortForm;
        this.number = number;
    }
}
