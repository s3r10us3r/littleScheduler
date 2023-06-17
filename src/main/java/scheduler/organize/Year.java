package scheduler.organize;

public class Year {
    private Month[] months;
    private int number;
    public final boolean isLeapYear;

    protected Year(int number){
        this.number = number;
        months = new Month[13];
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

    public void createMonth(int number){
        Month month = new Month(number, isLeapYear);
        months[month.getNumber()] = month;
    }

    public Month getMonth(int number){
        if(number < 1 || number > 12){
            throw new IllegalArgumentException("Invalid month number");
        }
        return months[number];
    }
}
