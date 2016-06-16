package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.model.Game;

import java.util.List;

import static ar.fiuba.tdd.tp.Constants.EXIT;

public class Exit extends ICommand {

    private Game game;

    public Exit(Game game) {
        this.name = EXIT;
        this.game = game;
    }

    public String doAction(Element element, int playerId) {
        //Cuando un jugador sale del juego todos sus elementos quedan en el ultimo lugar donde estuvo.
        Player player = game.getPlayer(playerId);
        List<Element> playerElementList = player.getElementList();
        //Para cada uno de los elementos del player tengo que:
        // 1. Sacarlo del player
        // 2. Hacerlo visible
        // 3. Agregarlo al lugar donde el player salio
        for (Element actualElement : playerElementList) {
            player.removeElement(actualElement);
            actualElement.changeState("visible", true);
            element.addElement(actualElement);
        }
        //Le aviso al game que el jugador salio.
        game.notifyExitPlayer(playerId);
        return "You exit the game. Goodbye!";
    }
}

