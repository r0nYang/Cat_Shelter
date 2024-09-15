package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//CODE ATTRIBUTE to AlarmSystem as instructed (https://github.students.cs.ubc.ca/CPSC210/AlarmSystem)
/**
 * We use the Singleton Design Pattern to ensure that there is only
 * one EventLog in the system and that the system has global access
 * to the single instance of the EventLog.
 */
// Represents a log of alarm system events
public class EventLog implements Iterable<Event> {
    /**
     * the only EventLog in the system (Singleton Design Pattern)
     */
    private static EventLog theLog;
    private Collection<Event> events;

    /**
     * Prevent external construction.
     * (Singleton Design Pattern).
     */
    private EventLog() {
        events = new ArrayList<Event>();
    }


    //EFFECTS: gets the instance of EventLog. Creates an instance of EventLog if it doesn't already exist.
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }


    //EFFECTS: adds an Event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    //EFFECTS: clears the event log and logs the event.
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    //EFFECTS: returns the list of events
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
