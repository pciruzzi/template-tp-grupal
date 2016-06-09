package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.interpreter.IInterpreter;

public class Player extends Element {

    private int id;
    private Element playerPosition;
    private IInterpreter winInterpreter;
    private IInterpreter losingInterpreter;

    public Player(int id) {
        super("player");
        this.id = id;
    }

    public Player getClone() {
        try {
            return (Player) super.clone();
        } catch (CloneNotSupportedException c) {
            System.out.println("Error cuando se quiere clonar un Player");
            return null;
        }
    }

    public void setPlayerID(int id) {
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
}
