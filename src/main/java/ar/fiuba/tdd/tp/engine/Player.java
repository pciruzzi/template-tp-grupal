package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.icommand.ITimeCommand;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

public class Player extends Element {

    private int id;
    private Element playerPosition;
    private IInterpreter winInterpreter;
    private IInterpreter losingInterpreter;

    public Player(int id) {
        super("player " + id);
        this.id = id;
    }

    public int getPlayerID() {
        return this.id;
    }

    public void setPlayerPosition(Element playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Element getPlayerPosition() {
        return this.playerPosition;
    }

    public void setWinInterpreter(IInterpreter winInterpreter) {
        this.winInterpreter = winInterpreter;
    }

    public IInterpreter getWinInterpreter() {
        return this.winInterpreter;
    }

    public void setLosingInterpreter(IInterpreter losingInterpreter) {
        this.losingInterpreter = losingInterpreter;
    }

    public IInterpreter getLosingInterpreter() {
        return this.losingInterpreter;
    }

    public String doTimeCommand(String commandName) {
        if (timeCommandMap.containsKey(commandName)) {
            ITimeCommand command = timeCommandMap.get(commandName);
            return command.doTimeAction(this);
        } else {
            return "I can't do that.";
        }
    }
}
