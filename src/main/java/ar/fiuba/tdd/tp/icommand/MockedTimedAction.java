package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.time.TimeCommand;

public class MockedTimedAction extends TimeCommand {


    public MockedTimedAction(int timeOfAction, String comandito) {
        super(timeOfAction, comandito);
    }

    @Override
    public void startTimeAction() {
    }

    public String startMockedAction() {
        String returnMessagge = engine.doTimeCommand(command);
        queue.pushBroadcast(returnMessagge);
        return returnMessagge;
    }

}
