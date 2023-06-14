package scheduler.time;

public enum WeekDay {
    MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY("Thursday"),
    FRIDAY("Friday"), SATURDAY("Saturday"), SUNDAY("Sunday");

    private String string;

    private WeekDay(String string){
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
