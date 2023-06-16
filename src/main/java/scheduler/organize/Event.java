package scheduler.organize;

public class Event implements Comparable<Event>{
    private int startTime; //in minutes from 00:00
    private int finishTime; //in minutes from 00:00
    private int duration;
    private String name;
    private String description;

    public Event(String name, int startTime, int finishTime) throws IllegalArgumentException{
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.description = null;
        if(finishTime < startTime){
            throw new IllegalArgumentException("Finish time smaller than start time");
        }
        duration = finishTime - startTime;
    }

    @Override
    public int compareTo(Event o){
        return startTime - o.getStartTime();
    }

    public int getStartTime() {
        return startTime;
    }

    private void setDescription(String description){
        this.description = description;
    }
}
