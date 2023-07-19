package scheduler.organize;

public class Event implements Comparable<Event>{
    public static final double opacityValue = 0.7;
    private int startTime; //in minutes from 00:00
    private int finishTime; //in minutes from 00:00
    private int duration;
    private String name;
    private double[] backgroundColorValues;
    private double[] textColorValues;

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
        textColorValues = new double[3]; textColorValues[0] = 1; textColorValues[1] = 1; textColorValues[2] = 1;
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
        textColorValues = new double[3]; textColorValues[0] = 1; textColorValues[1] = 1; textColorValues[2] = 1;
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

    public double[] getTextColorValues() {
        return textColorValues;
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
