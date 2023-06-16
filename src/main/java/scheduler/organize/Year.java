package scheduler.organize;

public class Year {
    private Month[] months;
    private int number;
    public final boolean isLeapYear;

    public Year(int number){
        this.number = number;
        months = new Month[12];
        if(number % 4 == 0){
            isLeapYear = true;
        }
        else{
            isLeapYear = false;
        }
    }

    public int getNumber() {
        return number;
    }

    public void addMonth(Month month){
        if(months[month.getNumber()] != null){
            throw new IllegalArgumentException("This month has already been initialized");
        }
        else{
            months[month.getNumber()] = month;
        }
    }
}
