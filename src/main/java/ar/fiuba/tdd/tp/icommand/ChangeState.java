package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.logic.TrueExpression;

public class ChangeState extends ITimeCommand {

    private IInterpreter condition;
    private boolean state;

    private String returnMessage;
    private String stateName;

    public ChangeState(String actionName, String stateName, boolean state) {
        this.name = actionName;
        this.stateName = stateName;
        this.state = state;
        this.correctMovementMessage = " is opened.";
        this.incorrectMovementMessage = "You can't do that.";
        this.auxiliarMessage = " is closed!.";
        this.condition = new TrueExpression();
        returnMessage = "";
    }

    public ChangeState(String actionName, String stateName, boolean state, IInterpreter condition) {
        this(actionName, stateName, state);
        this.condition = condition;
    }

    public String doTimeAction(Player player) {
        if (this.condition.interpret(player)) {
            player.changeState(stateName,state);
            if (state) {
                return "The " + player.getName() + correctMovementMessage + returnMessage;
            } else {
                return "The " + player.getName() + auxiliarMessage + returnMessage;
            }
        }
        return incorrectMovementMessage;
    }
}