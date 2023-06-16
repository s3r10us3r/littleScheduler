package scheduler.organize;

public enum DayOfTheWeek {
    MONDAY("Monday", "MON"), TUESDAY("Tuesday", "TUE"), WEDNESDAY("Wednesday", "WED"),
    THURSDAY("Thursday", "THUR"), FRIDAY("Firday", "FRI"), SATURDAY("Saturday", "SAT"), SUNDAY("Sunday", "SUN");

    public final String longForm;
    public final String shortForm;
    DayOfTheWeek(String longForm, String shortForm){
        this.longForm = longForm;
        this.shortForm = shortForm;
    }
}
