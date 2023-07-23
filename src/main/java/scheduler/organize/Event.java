package scheduler.organize;

import java.io.Serializable;

public class Event implements Comparable<Event>, Serializable{
    public static final long serialVersionUID = 1L;
    public static final double opacityValue = 0.7;
    protected int startTime; //in minutes from 00:00
    protected int finishTime; //in minutes from 00:00
    protected int duration;
    protected String name;
    protected double[] backgroundColorValues;

    public Event(String name, int startTime, int finishTime, double red, double green, double blue) throws IllegalArgumentException{
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
        if(finishTime < startTime){
            throw new IllegalArgumentException("Finish time smaller than start time");
        }
        duration = finishTime - startTime;
        if(duration < 5){
            duration = 0;
        }
        backgroundColorValues = new double[3]; backgroundColorValues[0] = red; backgroundColorValues[1] = green; backgroundColorValues[2] = blue;
    }

    public void reset(String name, int startTime, int finishTime, double red, double green, double blue) throws IllegalArgumentException{
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
        if(finishTime < startTime){
            throw new IllegalArgumentException("Finish time smaller than start time");
        }
        duration = finishTime - startTime;
        if(duration < 5){
            duration = 0;
        }
        backgroundColorValues = new double[3]; backgroundColorValues[0] = red; backgroundColorValues[1] = green; backgroundColorValues[2] = blue;
    }

    public boolean isInConflict(Event event){
        return startTime < event.getFinishTime() && finishTime > event.getStartTime();
    }

    @Override
    public int compareTo(Event o){
        return startTime - o.getStartTime();
    }

    public double[] getBackgroundColorValues() {
        return backgroundColorValues;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDuration() {
        return duration;
    }
}
