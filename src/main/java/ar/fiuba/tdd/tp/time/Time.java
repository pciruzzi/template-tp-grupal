package ar.fiuba.tdd.tp.time;


import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;

import java.util.ArrayList;
import java.util.Timer;

public class Time {

    private Timer timer;
    private ArrayList<TimeCommand> timeCommands;
    private Engine engine;
    private BroadcastQueue queue;

    public Time(Engine engine, BroadcastQueue queue) {
        timer = new Timer();
        timeCommands = new ArrayList<>();
        this.engine = engine;
        this.queue = queue;
    }

    public void start() {
        for (TimeCommand command: timeCommands) {
            command.setEngine(this.engine);
            command.setTimer(timer);
            command.setBroadcastQueue(queue);
            if ( command.getStart() ) {
                command.startTimeAction();
            }
        }
    }

    public void addTimeCommand(TimeCommand timeCommand) {
        timeCommands.add(timeCommand);
    }

    public void setTimeTasks(ArrayList<TimeCommand> timeTasks) {
        this.timeCommands = timeTasks;
    }
}
