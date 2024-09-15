package ui;

import model.Event;
import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//CODE ATTRIBUTE: WindowAdapter from https://docs.oracle.com/javase/8/docs/api/java/awt/event/WindowAdapter.html
// Represents a class that prints the logged events
public class LogPrinter extends WindowAdapter {

    public LogPrinter() {

    }

    //EFFECTS: Prints logged events to console when window is closed
    @Override
    public void windowClosing(WindowEvent e) { // windowClosed didn't work?
        printLogs(EventLog.getInstance());
    }

    //CODE ATTRIBUTE: printLogs method from (https://github.students.cs.ubc.ca/CPSC210/AlarmSystem)
    //EFFECTS: prints logged events
    private void printLogs(EventLog el) {
        for (Event next: el) {
            System.out.println(next.toString());
            System.out.println();
        }
    }
}
