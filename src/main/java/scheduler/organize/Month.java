package scheduler.organize;

public class Month implements Comparable<Month>{
    private Day[] days;
    private MonthInfo monthInfo;
    public Month(int number, boolean isLeapYear){
        this.monthInfo = MonthInfo.getMonth(number);
        if(isLeapYear && monthInfo == MonthInfo.FEBRUARY) {
            days = new Day[monthInfo.numberOfDays+1];
        }
        else{
            days = new Day[monthInfo.numberOfDays];
        }
    }

    @Override
    public int compareTo(Month o){
        return getNumber() - o.getNumber();
    }

    public void addDay(Day day) throws IllegalArgumentException{
        if(days[day.getNumber()] != null){
            throw new IllegalArgumentException("This day was already initialized!");
        }
        days[day.getNumber()] = day;
    }

    public int getNumber() {
        return monthInfo.number;
    }

    public int getNumberOfDays(){
        return monthInfo.numberOfDays;
    }

    public boolean hasDay(int number) throws IllegalArgumentException{
        if(number > monthInfo.numberOfDays || number <= 0){
            throw new IllegalArgumentException("Not such day in this month");
        }
        if(days[number] == null){
            return false;
        }
        else{
            return true;
        }
    }
}
