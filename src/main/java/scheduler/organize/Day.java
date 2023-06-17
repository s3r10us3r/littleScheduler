package scheduler.organize;

import java.util.ArrayList;

public class Day implements Comparable<Day>{
    private ArrayList<Timeline> timelines;
    private int number; //in month
    private DayOfTheWeek dayOfTheWeek;
    public Day(int number, DayOfTheWeek dayOfTheWeek){
        this.number = number;
        timelines = new ArrayList<Timeline>();
        this.dayOfTheWeek = dayOfTheWeek;
    }
    public void addEvent(Event event){
        for(Timeline timeline : timelines){
            if(timeline.addEvent(event)){
                return;
            }
        }
        Timeline timeline = new Timeline();
        timeline.addEvent(event);
        timelines.add(timeline);
    }
    public ArrayList<Timeline> getTimelines() {
        return timelines;
    }

    @Override
    public int compareTo(Day o){
        return number - o.getNumber();
    }

    public int getNumber() {
        return number;
    }
}
