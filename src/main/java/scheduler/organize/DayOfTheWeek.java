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

    public static DayOfTheWeek computeDayOfTheWeek(int number){
        int res = (number + 5)%7;//We are counting from January 1st 2000 which was Saturday
        switch (res) {
            case 0 -> {
                return MONDAY;
            }
            case 1 -> {
                return TUESDAY;
            }
            case 2 -> {
                return WEDNESDAY;
            }
            case 3 -> {
                return THURSDAY;
            }
            case 4 -> {
                return FRIDAY;
            }
            case 5 -> {
                return SATURDAY;
            }
            case 6 -> {
                return SUNDAY;
            }
            default -> {
                return null;
            }
        }
    }
}
