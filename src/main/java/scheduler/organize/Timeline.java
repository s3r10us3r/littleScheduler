package scheduler.organize;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Timeline implements Iterable<Event>{
    SortedSet<Event> events;

    protected Timeline(){
        events = new TreeSet<>();
    }
    public boolean addEvent(Event event){//returns false if there is a conflicting event in this timeline.
        for(Event e : events){
            if(event.isInConflict(e)){
                return false;
            }
        }
        events.add(event);
        return true;
    }


    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
