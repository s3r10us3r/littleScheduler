package scheduler.organize;

public class Date {
    public int year;
    public int month;
    public int day;

    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(Day day){
        setTheNums(day.getNumber());
    }

    public Date(int number){
        setTheNums(number);
    }

    private void setTheNums(int number){
        System.out.println(number);
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
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
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

    public int getMonthNumberOfDays(){
        return getMonthNumberOfDays(this.month, this.year);
    }

    public String getMonthName(int num){
        switch(num){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                throw new IllegalArgumentException();
        }
    }
    public String getMonthName(){
        return getMonthName(this.month);
    }
}
