package scheduler.organize;

import scheduler.appClasses.SchedulerApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class DaysHashMap {
    private static HashMap<Integer, Day> days = new HashMap<>();//the day's number is the key

    public static Day getDay(int year, int month, int day){
        return days.get(Day.computeNumber(day, month, year));
    }

    public static Day getDay(int number){
        return days.get(number);
    }

    public static void addDay(Day day){
        days.put(day.getNumber(), day);
    }

    public static final String saveFilePath = System.getProperty("user.home") + File.separator + "MyLittleScheduler" + File.separator + "data.ser";


    public static void load(){
        File file = new File(saveFilePath);
        if(file.exists()){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFilePath))){
                days = (HashMap<Integer, Day>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                days = new HashMap<>();
            }
        }
        else{
            File directory = new File(System.getProperty("user.home") + File.separator + "MyLittleScheduler");
            directory.mkdir();
            days = new HashMap<>();
        }
    }

    public static void save(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFilePath))) {
            oos.writeObject(days);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;
    }

}
