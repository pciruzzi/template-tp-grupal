package ar.fiuba.tdd.tp.time;

public class ScheduledTimedAction extends TimeCommand {

    public ScheduledTimedAction(int timeOfAction, String comandito) {
        super(timeOfAction, comandito);
    }

    public void startTimeAction() {
        timer.scheduleAtFixedRate(this.start(),this.getTimeOfAction(),this.getTimeOfAction());
    }
}
