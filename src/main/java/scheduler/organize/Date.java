package scheduler.organize;

public class Date {
    public final int year;
    public final int month;
    public final int day;

    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(Day day){
        int number = day.getNumber();
        int year = 2000;
        int month = 1;
        int dayNumber = 1;

        while((year%4 == 0 && number >= 366) || (year%4 != 0 && number >= 365) ){
            if(year%4 == 0){
                number -= 366;
            }
            else{
                number -= 365;
            }
            year += 1;
        }

        while(number >= getMonthNumberOfDays(month, year) ){
            number -= getMonthNumberOfDays(month, year);
            month ++;
            if(month == 13){
                month = 1;
                year++;
            }
        }
        dayNumber += number;

        this.year = year;
        this.month = month;
        this.day = dayNumber;
    }

    private int getMonthNumberOfDays(int month, int year){
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            return 31;
        }
        if(month == 4 || month == 6 || month == 9 || month == 11){
            return 30;
        }
        if(month == 2 && year%4 == 0){
            return 29;
        }
        if(month == 2 && year%4 != 0){
            return 28;
        }
        throw new IllegalArgumentException("Invalid month/year");
    }
}
