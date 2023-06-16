package scheduler.organize;

public enum MonthInfo {
    JANUARY(1, "Januray", "JAN", 31), FEBRUARY(2, "February", "FEB", 28),
    MARCH(3, "March", "MAR", 31), APRIL(4, "April", "APR", 30),
    MAY(5, "May", "MAY", 31), JUNE(6,"June", "JUN", 30),
    JULY(7, "July", "JUL", 31), AUGUST(8, "August", "AUG", 31),
    SEPTEMBER(9, "September", "SEP", 30), OCTOBER(10, "October", "OCT", 31),
    NOVEMBER(11, "November", "NOV", 30), DECEMBER(12, "December", "DEC", 31);

    public final int number;
    public final String fullForm;
    public final String shortForm;
    public final int numberOfDays;
    MonthInfo(int number, String fullForm, String shortForm, int numberOfDays){
        this.number = number;
        this.fullForm = fullForm;
        this.shortForm = shortForm;
        this.numberOfDays = numberOfDays;
    }
    public static MonthInfo getMonth(int number){
        MonthInfo monthInfo;
        switch(number){
            case 1:
                monthInfo = JANUARY;
                break;
            case 2:
                monthInfo = FEBRUARY;
                break;
            case 3:
                monthInfo = MARCH;
                break;
            case 4:
                monthInfo = APRIL;
                break;
            case 5:
                monthInfo = MAY;
                break;
            case 6:
                monthInfo = JUNE;
                break;
            case 7:
                monthInfo = JULY;
                break;
            case 8:
                monthInfo = AUGUST;
                break;
            case 9:
                monthInfo = SEPTEMBER;
                break;
            case 10:
                monthInfo = OCTOBER;
                break;
            case 11:
                monthInfo = NOVEMBER;
                break;
            case 12:
                monthInfo = DECEMBER;
                break;
            default:
                throw new IllegalArgumentException("Month number can be only between 1 to 12");
        }
        return monthInfo;
    }
}
