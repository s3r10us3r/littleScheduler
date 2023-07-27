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

    public int getMonthNumberOfDays(){
        return getMonthNumberOfDays(this.month, this.year);
    }

    public int computeNumber(){
        int yearFrom2000 = year - 2000;
        int number = 0;
        if(year != 2000){
            number += 365 * yearFrom2000;
            number += (yearFrom2000-1)/4 + 1;
        }
        number += getDaysPassedThisYear();
        number += day - 1;//this is because first of January 2000 has number '0'
        return number;
    }

    private int getDaysPassedThisYear(){
        int result = 0;
        if(month > 1){
            result += 31;
        }
        if(month > 2){
            if(year % 4 == 0){
                result += 29;
            }
            else{
                result += 28;
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

    public Date copy(){
        return new Date(this.year, this.month, this.day);
    }
}
