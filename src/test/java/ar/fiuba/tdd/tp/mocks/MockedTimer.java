package ar.fiuba.tdd.tp.mocks;

import java.util.*;

public class MockedTimer extends Timer {

    private int elapsedTime;
    private Map<Integer,List<MockedTimedCommand>> actionsMap;

    public MockedTimer(int initialTime) {
        this.elapsedTime = initialTime;
        this.actionsMap = new HashMap<>();
    }

    public void addAction(int timeInMilliseconds, MockedTimedCommand command) {
        List<MockedTimedCommand> commandList = actionsMap.get(timeInMilliseconds);
        if (commandList == null) {
            commandList = new ArrayList<>();
        }
        commandList.add(command);
        actionsMap.put(timeInMilliseconds, commandList);
    }

    public String avanzarTiempo(int milliseconds) {
        this.elapsedTime += milliseconds;
        return ejecutarAcciones();
    }

    private String ejecutarAcciones() {
        String returnMessage = "";
        for (Integer time : actionsMap.keySet()) {
            if (time >= elapsedTime) {
                for ( MockedTimedCommand command : actionsMap.get(time) ) {
                    returnMessage = command.startMockedAction();
                }
                actionsMap.remove(time);
            }
        }
        return returnMessage;
    }
}
