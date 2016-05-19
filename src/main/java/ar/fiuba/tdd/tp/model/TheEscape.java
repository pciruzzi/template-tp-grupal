package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.ChangeVisibility;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.MovePlayerTo;
import ar.fiuba.tdd.tp.interpreter.AndExpression;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

public class TheEscape implements GameBuilder {

    @SuppressWarnings("CPD-START")
    private Game game;
    private Element player;

    private Element sotano;
    private Element sotanoAbajo;
    private Element lastRoom;

    // Los cuartos
    private Element accesoBiblioteca;
    private Element biblioteca;
//    private Element roomOne;
//    private Element roomHanoi;
//    private Element roomArchaeologist;
//
    // Las puertas
    private Element doorBibliotecario;
    private Element doorSotano;
//    private Element doorOneHanoi;
//    private Element doorHanoiArchaeologist;
//    private Element doorArchaeologistOutside;
//
//    // Los elementos levantables
    private Element credencial;
    private Element libroViejo;
    private Element libroUno;
    private Element libroDos;
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
        credencial = new Element("Credencial");

        createSotano();
        createSotanoAbajo();
        createLastRoom();
        createBibliotecario();
        createBiblioteca();
        
        return game;
    }

    private void createSotano() {

        sotano = new Element("Sotano");
        Element escalera = new Element("escalera");
        Element baranda = new Element("baranda");
        baranda.setState(true);
        escalera.setState(true);

    }

    private void createSotanoAbajo() {

        sotanoAbajo = new Element("Sotano abajo");

        Element ventana = new Element("ventana");
        Element escalera = new Element("escalera");
        Element baranda = new Element("baranda");
//        TODO aca hay que hacer el using de santi.
//        todo tambien hay que hacer la condicion de perder si uso la escalera.
//     ventana.addCommand();
        ventana.setState(true);
        escalera.setState(true);
        baranda.setState(true);

    }

    private void createLastRoom() {

        lastRoom = new Element("A Fuera");
        ArrayList<String> winConditionArray = new ArrayList<>();
        winConditionArray.add("player");

        IInterpreter winCondition = new ContainsElements(lastRoom, winConditionArray);

        game.setWinInterpreter(winCondition);
    }

    private void createBibliotecario() {
        accesoBiblioteca = new Element("accesoBiblioteca");

        ArrayList<String> tieneCredencial = new ArrayList<>();
        tieneCredencial.add("credencial");

        IInterpreter playerWithCredential = new ContainsElements(player, tieneCredencial);

        ArrayList<String> tieneFotoBuena = new ArrayList<>();
        tieneFotoBuena.add("foto buena");

        IInterpreter credencialBuena = new ContainsElements(credencial, tieneFotoBuena);

        IInterpreter credencialConFoto = new AndExpression(playerWithCredential, credencialBuena);

        ArrayList<String> tieneLicor = new ArrayList<>();
        tieneFotoBuena.add("Licor");

        IInterpreter conLicor = new ContainsElements(player, tieneLicor);

        IInterpreter puedePasar = new AndExpression(conLicor, credencialConFoto);

        ICommand pasarBibliotecario = new MovePlayerTo(game, puedePasar,"show Credencial");


        doorBibliotecario = new Element("Bibliotecario");
        doorBibliotecario.setState(true);
        doorBibliotecario.addCommand(pasarBibliotecario);

        accesoBiblioteca.addElement(doorBibliotecario);

    }

    private void createBiblioteca() {
        biblioteca = new Element("biblioteca");
        doorBibliotecario.setObjectiveElement(biblioteca);

        libroViejo = new Element("LibroViejo");
        libroUno = new Element("libro quimica");
        libroDos = new Element("libro fisica");

        libroViejo.setState(true);
        libroViejo.addCommand(new ChangeVisibility("move", true, game));
        libroUno.setState(true);
        libroDos.setState(true);

        doorSotano = new Element("Sotano");
        libroViejo.addElement(doorSotano);
        doorSotano.addCommand(new MovePlayerTo(game, "goto"));

        biblioteca.addElement(libroViejo);
        biblioteca.addElement(libroUno);
        biblioteca.addElement(libroDos);
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
