package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.MovePlayerTo;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

public class TheEscape implements GameBuilder {

    @SuppressWarnings("CPD-START")
    private Game game;
    private Element player;
    // Los cuartos
//    private Element roomOne;
//    private Element roomHanoi;
//    private Element roomArchaeologist;
    private Element sotano;
    private Element sotanoAbajo;
    private Element lastRoom;
    private Element cuartoDeLaMuerte;



//    // Las puertas
//    private Element doorOneHanoi;
//    private Element doorHanoiArchaeologist;
//    private Element doorArchaeologistOutside;
//
//    // Los elementos levantables
//    private Element key;
//    private Element antidote;
//    private Element apple;
//
//    // Los elementos contenedores
//    private Element skeleton;
//    private Element chest;
//
//    // Los ICommands
//    private ICommand drop;
//    private ICommand pick;
//    private ICommand openDoor;
//    private ICommand openContainer;
//    private ICommand closeContainer;
//    private ICommand question;
//    private ICommand lookAround;
//    private ICommand moveWithComparator;
//
    @Override
    public Game build() {

        game = new Game("Temple Quest");
        cuartoDeLaMuerte = new Element("Cuarto de la muerte");
        sotano = new Element("Sotano");
        sotanoAbajo = new Element("Sotano abajo");
        lastRoom = new Element("A Fuera");


        createSotano();
        createSotanoAbajo();
        createLastRoomAndCondicionesDeMorir();
        
        return game;
    }

    private void createSotano() {

        Element escalera = new Element("escalera");
        Element baranda = new Element("baranda");
        baranda.setState(true);
        escalera.setState(true);

        //Acciones
        ICommand use = new MovePlayerTo(game, "use");

        baranda.setObjectiveElement(sotano);
        baranda.addCommand(use);

        escalera.setObjectiveElement(cuartoDeLaMuerte);
        escalera.addCommand(use);
    }

    private void createSotanoAbajo() {

        Element ventana = new Element("ventana");
        Element escalera = new Element("escalera");
        Element baranda = new Element("baranda");
//        TODO aca hay que hacer el using de santi.
//     ventana.addCommand();

        ventana.setState(true);

        ArrayList<String> listaParaRomperVentana = new ArrayList<>();
        listaParaRomperVentana.add("martillo");
        IInterpreter requisitosRomper = new ContainsElements(player, listaParaRomperVentana);

        //Acciones
        ICommand use = new MovePlayerTo(game, "use");

        ICommand romper = new MovePlayerTo(game,requisitosRomper, "break");

        escalera.setState(true);
        baranda.setState(true);

        escalera.addCommand(use);
        escalera.setObjectiveElement(cuartoDeLaMuerte);

        ventana.setObjectiveElement(lastRoom);
        ventana.addCommand(romper);
    }

    private void createLastRoomAndCondicionesDeMorir() {

        ArrayList<String> winConditionArray = new ArrayList<>();
        winConditionArray.add("player");
        IInterpreter winCondition = new ContainsElements(lastRoom, winConditionArray);

        game.setWinInterpreter(winCondition);

        ArrayList<String> playerEstaEnCuartoDeLaMuerte = new ArrayList<>();
        playerEstaEnCuartoDeLaMuerte.add("player");
        IInterpreter estasEnCuartoDeLaMuerte = new ContainsElements(cuartoDeLaMuerte, playerEstaEnCuartoDeLaMuerte);

        //Aca estan las condiciones de perder
        game.setLosingInterpreter(estasEnCuartoDeLaMuerte);
    }

//
//    private void createRoomOne() {
//        roomOne     = new Element("roomOne");
//
//        // Los elementos levantables
//        antidote    = new Element("antidote");
//        apple       = new Element("apple");
//
//        // Los elementos contenedores
//        skeleton    = new Element("skeleton");
//        chest       = new Element("chest");
//    }
//
//    private void createRoomHanoi() {
//        roomHanoi = new Element("roomHanoi");
//    }
//
//    private void createRoomArchaeologist() {
//        roomArchaeologist = new Element("roomArchaeologist");
//    }
//
//    @SuppressWarnings("CPD-END")
//
//    private void createICommands() {
//        drop            = new DropOnPosition("drop", game);
//        pick            = new MoveToPlayer("pick", game);
//        openDoor        = new MovePlayerTo(game, "open");
//        openContainer   = new ChangeVisibility("open", true, game);
//        closeContainer  = new ChangeVisibility("close", false, game);
//        question        = new Question("ask");
//        lookAround      = new LookAround("look around", game);
//    }
}
