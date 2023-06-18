package scheduler.organize;

public class Event implements Comparable<Event>{
    public static final double opacityValue = 0.7;
    private int startTime; //in minutes from 00:00
    private int finishTime; //in minutes from 00:00
    private int duration;
    private String name;
    private String description;
    private double[] backgroundColorValues;
    private double[] textColorValues;

    public Event(String name, int startTime, int finishTime) throws IllegalArgumentException{
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.description = null;
        if(finishTime < startTime){
            throw new IllegalArgumentException("Finish time smaller than start time");
        }
        duration = finishTime - startTime;
        backgroundColorValues = new double[3]; backgroundColorValues[0] = 0.5; backgroundColorValues[1] = 1; backgroundColorValues[2] = 0;
        textColorValues = new double[3]; textColorValues[0] = 1; textColorValues[1] = 1; textColorValues[2] = 1;
    }

    public boolean isInConflict(Event event){
        if(startTime >= event.getFinishTime() || finishTime <= event.getStartTime()){
            return false;
        }
        else{
            return true;
        }
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

    public void setBackgroundColorValues(double[] backgroundColorValues) {
        this.backgroundColorValues = backgroundColorValues;
    }

    public void setTextColorValues(double[] textColorValues) {
        this.textColorValues = textColorValues;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    private void setDescription(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
