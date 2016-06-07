package ar.fiuba.tdd.tp.engine;

/**
 * Created by gg on 5/31/2016.
 */
public class SchedualedTimedAction extends TimeCommand {
    public SchedualedTimedAction(int timeOfAction, String comandito) {
        super(timeOfAction, comandito);
    }

    public void startTimeAction() {
        time.scheduleAtFixedRate(this.start(),this.getTimeOfAction(),this.getTimeOfAction());
    }
}
