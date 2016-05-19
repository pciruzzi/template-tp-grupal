package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.ChangeVisibility;

import ar.fiuba.tdd.tp.icommand.ChangeVisibility;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.MovePlayerTo;
import ar.fiuba.tdd.tp.icommand.MoveToPlayer;
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
    private Element cuartoDeLaMuerte;

    // Los cuartos
    private Element accesoBiblioteca;
    private Element biblioteca;
    private Element pasillo;
    private Element salon2;
    private Element salon3;
//    private Element roomOne;
//    private Element roomHanoi;
//    private Element roomArchaeologist;
//
    // Las puertas
    private Element doorBibliotecario;
    private Element doorSotano;
    private Element doorSalon1;
    private Element doorSalon2;
    private Element doorSalon3;

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
    private Element salonUno;

    private Element mesa;
    private Element botellaLicor;
    private Element licor;
    private Element vasoUno;
    private Element vasoDos;

    private Element sillaUno;
    private Element sillaDos;

    private Element cuadroBarco;
    private Element cuadroTren;
    private Element cajaFuerte;

    private Element fotoDesconocida;

    private Element puertaAPasillo;

    @Override
    public Game build() {

        game = new Game("Temple Quest");
        credencial = new Element("Credencial");
        player = new Element("player");

        createPasillo();
        createSalon2();
        createSalon3();

        createSotano();
        createSotanoAbajo();
        createBibliotecario();
        createBiblioteca();
        cuartoDeLaMuerte = new Element("Cuarto de la muerte");
        sotano = new Element("Sotano");
        sotanoAbajo = new Element("Sotano abajo");
        lastRoom = new Element("A Fuera");


        createLastRoomAndCondicionesDeMorir();

        game.setPlayer(player);
        game.setPlayerPosition(pasillo);

        return game;
    }

    private void createRoomOne() {

        salonUno = new Element("Salon1");

        // Los elementos levantables
        botellaLicor = new Element("Botella");
        vasoUno = new Element("Vaso1");
        vasoDos = new Element("Vaso2");
        credencial = new Element("Credencial");

        // Los elementos contenedores
        cuadroBarco = new Element("CuadroBarco");
        cuadroTren = new Element("CuadroTren");
        cajaFuerte = new Element("CajaFuerte");
        puertaAPasillo = new Element("PuertaAPasillo");

        // Los elementos sin comportamiento
        mesa = new Element("Mesa");
        sillaUno = new Element("SillaUno");
        sillaDos = new Element("SillaDos");

        fotoDesconocida = new Element("FotoDesconocida");

        // Lleno los contenedores
        credencial.addElement(fotoDesconocida);
        cajaFuerte.addElement(credencial);
        cuadroBarco.addElement(cajaFuerte);

        // Seteo los visibles
        botellaLicor.setState(true);
        vasoUno.setState(true);
        vasoDos.setState(true);
        cuadroBarco.setState(true);
        cuadroTren.setState(true);
        mesa.setState(true);
        sillaDos.setState(true);
        sillaUno.setState(true);

        // Asigno comandos de levantar
        ICommand levantar = new MoveToPlayer("pick", game);
        botellaLicor.addCommand(levantar);
        vasoUno.addCommand(levantar);
        vasoDos.addCommand(levantar);
        credencial.addCommand(levantar);

        // Asigno los mover a los cuadros
        ICommand moverCuadro = new ChangeVisibility("move",true ,game);
        cuadroTren.addCommand(moverCuadro);
        cuadroBarco.addCommand(moverCuadro);

        // Abrir caja fuerte con llave
        ArrayList<String> condicionLlave = new ArrayList<>();
        condicionLlave.add("Llave");
        IInterpreter condicionCaja = new ContainsElements(player, condicionLlave);
        ICommand abrirCaja = new ChangeVisibility("abrir", true, condicionCaja, game);
        cajaFuerte.addCommand(abrirCaja);

        // Abrir puerta para hall
        puertaAPasillo.setObjectiveElement(pasillo);
        ICommand abrirPuerta = new MovePlayerTo(game, "abrir");
        puertaAPasillo.addCommand(abrirCaja);

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

    private void createPasillo() {
        pasillo = new Element("pasillo");

        doorSalon1 = new Element("Salon1");
        doorSalon2 = new Element("Salon2");
        doorSalon3 = new Element("Salon3");

        doorSalon1.setState(true);
        doorSalon2.setState(true);
        doorSalon3.setState(true);

        pasillo.addElement(doorSalon1);
        pasillo.addElement(doorSalon2);
        pasillo.addElement(doorSalon3);
    }

    private void createSalon2() {
        salon2 = new Element("salon2");
        doorSalon2.setObjectiveElement(salon2);
    }

    private void createSalon3() {
        salon3 = new Element("salon3");
        doorSalon3.setObjectiveElement(salon3);
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
