package ar.fiuba.tdd.tp.time;

public class ScheduledTimedAction extends TimeCommand {
    public ScheduledTimedAction(int timeOfAction, String comandito) {
        super(timeOfAction, comandito);
    }

    public void startTimeAction() {

        System.out.println("this: " + this );
        timer.scheduleAtFixedRate(this.start(),this.getTimeOfAction(),this.getTimeOfAction());
    }
}
