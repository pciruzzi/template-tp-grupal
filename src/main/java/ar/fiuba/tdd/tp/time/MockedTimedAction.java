package ar.fiuba.tdd.tp.time;

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
