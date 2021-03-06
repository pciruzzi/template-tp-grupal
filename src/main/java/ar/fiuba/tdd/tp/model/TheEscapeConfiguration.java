package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.interpreter.logic.AndExpression;
import ar.fiuba.tdd.tp.interpreter.logic.OrExpression;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.ContainsPlayer;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.DoesNotContainElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("CPD-START")

public class TheEscapeConfiguration implements GameBuilder {

    private Game game;
    private Optional<Element> playerGenerico;

    private List<Player> players;
    private int maxPlayers;

    // Los cuartos
    private Element salonUno;
    private Element salonDos;
    private Element salonTres;
    private Element accesoBiblioteca;
    private Element accesoBibliotecaBis;
    private Element biblioteca;
    private Element pasillo;
    private Element sotano;
    private Element sotanoAbajo;
    private Element lastRoom;
    private Element cuartoDeLaMuerte;

    // Las puertas
    private Element doorAccesoABibliotecario;
    private Element doorBibliotecario;
    private Element doorBiblioteca;
    private Element doorSotano;
    private Element doorSalon1;
    private Element doorSalon2;
    private Element doorSalon3;
    private Element doorToPasillo;
    private Element doorBibliotecaBibliotecario;
    private Element pasajeAfuera;

    // Los elementos levantables
    private Element credencial;
    private Element botellaLicor;
    private Element vasoUno;
    private Element vasoDos;
    private Element fotoDesconocida;
    private Element martillo;
    private Element destornillador1;
    private Element destornillador2;
    private Element libroViejo;
    private Element libroUno;
    private Element libroDos;
    private Element libroTres;
    private Element libroCuatro;
    private Element libroCinco;
    private Element libroSeis;
    private Element libroSiete;
    private Element libroOcho;
    private Element libroNueve;

    // Otros Elements
    private Element mesa;
    private Element sillaUno;
    private Element sillaDos;
    private Element cuadroBarco;
    private Element cuadroTren;
    private Element cajaFuerte;
    private Element llave;
    private Element escalera;
    private Element barandaSotano;
    private Element barandaSotanoAbajo;
    private Element ventana;

    // Los ICommands
    private ICommand drop;
    private ICommand pick;
    private ICommand openDoor;
    private ICommand closeContainer;
    private ICommand question;
    private ICommand lookAround;
    private ICommand use;
    private ICommand romper;
    private ICommand pasarBibliotecario;
    private ICommand move;
    private ICommand moveLibroNada;


    @Override
    public Game build() {
        game = new Game("The Escape");
        game.setDescription("You're locked in a house; try to escape without die trying");
        maxPlayers = 4;
        game.setMaxPlayers(maxPlayers);
        players = new ArrayList<>();

        initializeElements();
        initializeRooms();
        initializeDoors();
        createICommands();
        createPlayer();
        createRooms();
        setHelpCommand();
        setExitCommand();

        return game;
    }

    private void createRooms() {
        createPasillo();
        createRoomOne();
        createRoomTwo();
        createBibliotecario();
        createRoomThree();
        createSotano();
        createSotanoAbajo();
        createAccesoABibliotecaBis();
        createBiblioteca();
        createLastRoomAndCondicionesDeMorir();

        game.setInitialPosition(pasillo);
    }

    private void setHelpCommand() {
        ICommand help = new Help("help", game);

        salonUno.addCommand(help);
        salonDos.addCommand(help);
        salonTres.addCommand(help);
        accesoBiblioteca.addCommand(help);
        accesoBibliotecaBis.addCommand(help);
        biblioteca.addCommand(help);
        pasillo.addCommand(help);
        sotano.addCommand(help);
        sotanoAbajo.addCommand(help);
        lastRoom.addCommand(help);
        cuartoDeLaMuerte.addCommand(help);
    }

    private void setExitCommand() {
        ICommand exit = new Exit(game);

        salonUno.addCommand(exit);
        salonDos.addCommand(exit);
        salonTres.addCommand(exit);
        accesoBiblioteca.addCommand(exit);
        accesoBibliotecaBis.addCommand(exit);
        biblioteca.addCommand(exit);
        pasillo.addCommand(exit);
        sotano.addCommand(exit);
        sotanoAbajo.addCommand(exit);
        lastRoom.addCommand(exit);
        cuartoDeLaMuerte.addCommand(exit);
    }

    private void initializeRooms() {
        pasillo = new Element("Pasillo");
        salonUno = new Element("Salon1");
        salonDos = new Element("Salon2");
        salonTres = new Element("Salon3");
        accesoBiblioteca = new Element("BibliotecaAcceso");
        biblioteca = new Element("Biblioteca");
        sotano = new Element("Sotano");
        sotanoAbajo = new Element("Sotano Abajo");
        lastRoom = new Element("LastRoom");
        cuartoDeLaMuerte = new Element("Cuarto de la muerte");
    }

    private void initializeElements() {
        initializeFirstGroupOfElements();
        credencial = new Element("Credencial");
        cuadroBarco = new Element("CuadroBarco");
        cuadroTren = new Element("CuadroTren");
        cajaFuerte = new Element("CajaFuerte");
        mesa = new Element("Mesa");
        sillaUno = new Element("SillaUno");
        sillaDos = new Element("SillaDos");
        escalera = new Element("Escalera");
        barandaSotano = new Element("Baranda");
        ventana = new Element("Ventana");
        llave = new Element("Llave");
    }

    private void initializeFirstGroupOfElements() {
        playerGenerico = Optional.empty();
        fotoDesconocida = new Element("FotoDesconocida");
        martillo = new Element("Martillo");
        destornillador1 = new Element("Destornillador 1");
        destornillador2 = new Element("Destornillador 2");
        botellaLicor = new Element("Botella");
        vasoUno = new Element("Vaso1");
        vasoDos = new Element("Vaso2");
        initializeBooks();
    }

    private void initializeBooks() {
        libroViejo = new Element("LibroViejo");
        libroUno = new Element("libro quimica");
        libroDos = new Element("libro fisica");
        libroTres = new Element("libro disenio");
        libroCuatro = new Element("libro programacion");
        libroCinco = new Element("libro c");
        libroSeis = new Element("libro c++");
        libroSiete = new Element("libro fisica II");
        libroOcho = new Element("libro matematica");
        libroNueve = new Element("libro literatura");
    }

    private void initializeDoors() {
        doorToPasillo = new Element("Pasillo");
        doorSalon1 = new Element("Salon1");
        doorSalon2 = new Element("Salon2");
        doorSalon3 = new Element("Salon3");
        doorAccesoABibliotecario = new Element("BibliotecaAcceso");
        doorBibliotecario = new Element("Bibliotecario");
        accesoBibliotecaBis = new Element("Biblioteca");
        doorBiblioteca = new Element("Biblioteca");
        doorBibliotecaBibliotecario = new Element("Bibliotecario");
        doorSotano = new Element("Sotano");
        pasajeAfuera = new Element("Afuera");
        doorSalon1.changeState("visible", true);
        doorSalon2.changeState("visible", true);
        doorSalon3.changeState("visible", true);
        doorAccesoABibliotecario.changeState("visible", true);
    }

    private void createPlayer() {
        for (int i = 0; i < maxPlayers; i++) {
            Player newPlayer = new Player(i);
            newPlayer.setCapacity(4);

            Element fotoBuena = new Element("Foto");
            fotoBuena.addCommand(drop);
            fotoBuena.addCommand(move);
            fotoBuena.addCommand(pick);

            Element lapicera = new Element("Lapicera");
            lapicera.addCommand(drop);
            lapicera.addCommand(pick);

            newPlayer.addElement(fotoBuena);
            newPlayer.addElement(lapicera);

            players.add(newPlayer);
        }
        game.setPlayers(players);
    }

    private void createRoomTwo() {
        salonDos.addCommand(lookAround);
        doorSalon2.setObjectiveElement(salonDos);
        configureElementsRoomTwo();

        doorToPasillo.setObjectiveElement(pasillo);
        doorToPasillo.addCommand(openDoor);
        doorToPasillo.addCommand(question);
        addElementsToRoomTwo();
    }

    private void addElementsToRoomTwo() {
        salonDos.addElement(martillo);
        salonDos.addElement(destornillador1);
        salonDos.addElement(destornillador2);
        salonDos.addElement(doorToPasillo);
    }

    private void configureElementsRoomTwo() {
        martillo.addCommand(pick);
        martillo.addCommand(drop);
        martillo.addCommand(question);
        martillo.changeState("visible", true);

        destornillador1.addCommand(pick);
        destornillador1.addCommand(drop);
        destornillador1.addCommand(question);
        destornillador1.changeState("visible", true);

        destornillador2.addCommand(pick);
        destornillador2.addCommand(drop);
        destornillador2.addCommand(question);
        destornillador2.changeState("visible", true);
    }

    private void createRoomOne() {
        salonUno.addCommand(lookAround);

        // Lleno los contenedores
        credencial.addElement(fotoDesconocida);
        cajaFuerte.addElement(credencial);
        cuadroBarco.addElement(cajaFuerte);
        setVisibleElements();

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

        // Asigno las preguntas
        addQuestionCommandToRoomOneElements();
        // Abrir caja fuerte con llave
        setConditionsForCajaFuerte();

        // Abrir puerta para hall
        doorToPasillo.setObjectiveElement(pasillo);
        ICommand abrirPuerta = new MovePlayerTo(game, "goto");
        doorToPasillo.addCommand(abrirPuerta);

        // Agrego los elementos al salon
        setElementsToSalon1();
    }

    private void addQuestionCommandToRoomOneElements() {
        mesa.addCommand(question);
        sillaUno.addCommand(question);
        sillaDos.addCommand(question);
        vasoUno.addCommand(question);
        vasoDos.addCommand(question);
        cuadroBarco.addCommand(question);
        cuadroTren.addCommand(question);
        botellaLicor.addCommand(question);
        cajaFuerte.addCommand(question);
    }

    private void setConditionsForCajaFuerte() {
        ArrayList<String> condicionLlave = new ArrayList<>();
        condicionLlave.add("Llave");
        IInterpreter condicionCaja = new ContainsElements(playerGenerico, condicionLlave);
        condicionCaja.setFailMessage("¿Que haces? Necesitas la Llave para abrir la CajaFuerte");
        ICommand abrirCaja = new ChangeVisibility("open", true, condicionCaja, game);
        abrirCaja.incorrectMovementMessage("¿Que hacés? Necesitas la Llave para abrir la CajaFuerte.");
        cajaFuerte.addCommand(abrirCaja);
        cajaFuerte.addCommand(closeContainer);
    }

    private void setElementsToSalon1() {
        salonUno.addElement(mesa);
        salonUno.addElement(botellaLicor);
        salonUno.addElement(vasoDos);
        salonUno.addElement(vasoUno);
        salonUno.addElement(sillaDos);
        salonUno.addElement(sillaUno);
        salonUno.addElement(cuadroBarco);
        salonUno.addElement(cuadroTren);
        salonUno.addElement(doorToPasillo);
    }

    private void setVisibleElements() {
        // Seteo los visibles
        botellaLicor.changeState("visible", true);
        vasoUno.changeState("visible", true);
        vasoDos.changeState("visible", true);
        cuadroBarco.changeState("visible", true);
        cuadroTren.changeState("visible", true);
        mesa.changeState("visible", true);
        sillaDos.changeState("visible", true);
        sillaUno.changeState("visible", true);
        doorToPasillo.changeState("visible", true);
    }

    private void createSotano() {
        sotano.addCommand(lookAround);
        barandaSotano.changeState("visible", true);
        escalera.changeState("visible", true);

        barandaSotano.setObjectiveElement(sotanoAbajo);
        barandaSotano.addCommand(use);
        barandaSotano.addCommand(question);

        escalera.setObjectiveElement(cuartoDeLaMuerte);
        escalera.addCommand(use);
        escalera.addCommand(question);

        sotano.addElement(escalera);
        sotano.addElement(barandaSotano);
    }

    private void createSotanoAbajo() {
        sotanoAbajo.addCommand(lookAround);
        barandaSotanoAbajo = new Element("Baranda");
        barandaSotanoAbajo.changeState("visible", true);

        sotanoAbajo.addElement(barandaSotanoAbajo);
        sotanoAbajo.addElement(escalera);
        barandaSotanoAbajo.changeState("visible", true);

        ICommand barandaNada = new ChangeVisibility("use", true, game);
        barandaNada.correctMovementMessage(" is not able to climb!");
        barandaSotanoAbajo.addCommand(barandaNada);
        barandaSotanoAbajo.addCommand(question);

        pasajeAfuera.addCommand(openDoor);
        pasajeAfuera.setObjectiveElement(lastRoom);
        ventana.addElement(pasajeAfuera);
        ventana.addCommand(romper);
        ventana.addCommand(question);
        ventana.changeState("visible", true);
        sotanoAbajo.addElement(ventana);
    }

    private void createLastRoomAndCondicionesDeMorir() {
        lastRoom.addCommand(lookAround);
        ArrayList<String> winConditionArray = new ArrayList<>();
        winConditionArray.add("player");
        IInterpreter winCondition = new ContainsPlayer(Optional.of(lastRoom), winConditionArray);
        IInterpreter losingInterpreter = createLosingInterpreter();

        for (Player player : players) {
            player.setWinInterpreter(winCondition);
            player.setLosingInterpreter(losingInterpreter);
        }
    }

    private IInterpreter createLosingInterpreter() {
        ArrayList<String> playerEstaEnCuartoDeLaMuerte = new ArrayList<>();
        playerEstaEnCuartoDeLaMuerte.add("player");
        IInterpreter estasEnCuartoDeLaMuerte = new ContainsPlayer(Optional.of(cuartoDeLaMuerte), playerEstaEnCuartoDeLaMuerte);

        ArrayList<String> playerEnSotanoAbajo = new ArrayList<>();
        playerEnSotanoAbajo.add("player");
        IInterpreter estasEnSotanoAbajo = new ContainsPlayer(Optional.of(sotanoAbajo), playerEnSotanoAbajo);

        ArrayList<String> playerNoTieneMartillo = new ArrayList<>();
        playerNoTieneMartillo.add("Martillo");
        IInterpreter noTenesMartillo = new DoesNotContainElements(playerGenerico, playerNoTieneMartillo);

        IInterpreter playerNoTieneMartilloYEstaEnSotanoAbajo = new AndExpression(estasEnSotanoAbajo, noTenesMartillo);
        return new OrExpression(estasEnCuartoDeLaMuerte, playerNoTieneMartilloYEstaEnSotanoAbajo);
    }

    private void createBibliotecario() {
        accesoBiblioteca.addCommand(lookAround);

        doorToPasillo.changeState("visible", true);
        doorToPasillo.addCommand(openDoor);
        doorToPasillo.setObjectiveElement(pasillo);

        accesoBiblioteca.addElement(doorToPasillo);
        doorAccesoABibliotecario.setObjectiveElement(accesoBiblioteca);

        setBibliotecarioCondition();

        doorBibliotecario.changeState("visible", true);
        doorBibliotecario.addCommand(pasarBibliotecario);
        doorBibliotecario.addCommand(question);

        accesoBiblioteca.addElement(doorBibliotecario);
    }

    private void setBibliotecarioCondition() {
        ArrayList<String> tieneCredencial = new ArrayList<>();
        tieneCredencial.add("Credencial");
        IInterpreter playerWithCredential = new ContainsElements(playerGenerico, tieneCredencial);

        ArrayList<String> tieneFotoBuena = new ArrayList<>();
        tieneFotoBuena.add("Foto");
        IInterpreter credencialBuena = new ContainsElements(Optional.of(credencial), tieneFotoBuena);
        IInterpreter credencialConFoto = new AndExpression(playerWithCredential, credencialBuena);

        ArrayList<String> tieneLicor = new ArrayList<>();
        tieneLicor.add("Botella");
        IInterpreter conLicor = new ContainsElements(playerGenerico, tieneLicor);

        IInterpreter puedePasar = new OrExpression(conLicor, credencialConFoto);
        puedePasar.setFailMessage("No podes pasar y me voy a acordar de tu cara!");

        pasarBibliotecario = new MovePlayerTo(game, puedePasar,"show credencial");
    }

    private void createAccesoABibliotecaBis() {
        doorBibliotecario.setObjectiveElement(accesoBibliotecaBis);
        doorBiblioteca.changeState("visible", true);
        doorBiblioteca.addCommand(openDoor);
        doorBiblioteca.addCommand(question);

        accesoBibliotecaBis.addElement(doorBiblioteca);
        accesoBibliotecaBis.addElement(doorToPasillo);

        accesoBibliotecaBis.addCommand(lookAround);
        accesoBiblioteca.addCommand(lookAround);
    }

    private void createBiblioteca() {
        doorBiblioteca.setObjectiveElement(biblioteca);

        doorBibliotecaBibliotecario.changeState("visible", true);
        doorBibliotecaBibliotecario.setObjectiveElement(accesoBibliotecaBis);
        doorBibliotecaBibliotecario.addCommand(openDoor);
        doorBibliotecaBibliotecario.addCommand(question);
        setLibrosVisibles();

        ICommand moveLibroViejo = new ChangeVisibility("move", true, game);
        moveLibroViejo.correctMovementMessage(" opened a secret passage to the basement!");
        addCommandsToLibros();

        libroViejo.addCommand(moveLibroViejo);
        libroViejo.addCommand(question);

        libroViejo.addElement(doorSotano);
        doorSotano.addCommand(openDoor);
        doorSotano.setObjectiveElement(sotano);

        addElementsToBiblioteca();
        biblioteca.addCommand(lookAround);
    }

    private void setLibrosVisibles() {
        libroViejo.changeState("visible", true);
        libroUno.changeState("visible", true);
        libroDos.changeState("visible", true);
        libroTres.changeState("visible", true);
        libroCuatro.changeState("visible", true);
        libroCinco.changeState("visible", true);
        libroSeis.changeState("visible", true);
        libroSiete.changeState("visible", true);
        libroOcho.changeState("visible", true);
        libroNueve.changeState("visible", true);
    }

    private void addCommandsToLibros() {
        libroUno.addCommand(question);
        libroDos.addCommand(question);
        libroTres.addCommand(question);
        libroCuatro.addCommand(question);
        libroCinco.addCommand(question);
        libroSeis.addCommand(question);
        libroSiete.addCommand(question);
        libroOcho.addCommand(question);
        libroNueve.addCommand(question);

        libroUno.addCommand(moveLibroNada);
        libroDos.addCommand(moveLibroNada);
        libroTres.addCommand(moveLibroNada);
        libroCuatro.addCommand(moveLibroNada);
        libroCinco.addCommand(moveLibroNada);
        libroSeis.addCommand(moveLibroNada);
        libroSiete.addCommand(moveLibroNada);
        libroOcho.addCommand(moveLibroNada);
        libroNueve.addCommand(moveLibroNada);
    }

    private void addElementsToBiblioteca() {
        biblioteca.addElement(doorBibliotecaBibliotecario);
        biblioteca.addElement(libroViejo);
        biblioteca.addElement(libroUno);
        biblioteca.addElement(libroDos);
        biblioteca.addElement(libroTres);
        biblioteca.addElement(libroCuatro);
        biblioteca.addElement(libroCinco);
        biblioteca.addElement(libroSeis);
        biblioteca.addElement(libroSiete);
        biblioteca.addElement(libroOcho);
        biblioteca.addElement(libroNueve);
    }

    private void createPasillo() {
        pasillo.addCommand(lookAround);

        doorSalon1.addCommand(openDoor);
        doorSalon2.addCommand(openDoor);
        doorSalon3.addCommand(openDoor);
        doorAccesoABibliotecario.addCommand(openDoor);

        pasillo.addElement(doorSalon1);
        pasillo.addElement(doorSalon2);
        pasillo.addElement(doorSalon3);
        pasillo.addElement(doorAccesoABibliotecario);

        doorSalon1.addCommand(question);
        doorSalon2.addCommand(question);
        doorSalon3.addCommand(question);
        doorAccesoABibliotecario.addCommand(question);

        doorSalon1.setObjectiveElement(salonUno);
        doorSalon2.setObjectiveElement(salonDos);
        doorSalon3.setObjectiveElement(salonTres);
        doorAccesoABibliotecario.setObjectiveElement(accesoBiblioteca);
    }

    private void createRoomThree() {
        llave.changeState("visible", true);
        salonTres.addCommand(lookAround);
        salonTres.addElement(llave);
        llave.addCommand(pick);
        llave.addCommand(drop);
        llave.addCommand(question);
        salonTres.addElement(doorToPasillo);
        doorSalon3.setObjectiveElement(salonTres);
    }

    @SuppressWarnings("CPD-END")

    private void createICommands() {
        drop            = new DropOnPosition("drop", game);
        pick            = new MoveToPlayer("pick", game);
        openDoor        = new MovePlayerTo(game, "goto");
        closeContainer  = new ChangeVisibility("close", false, game);
        question        = new Question("ask");
        lookAround      = new LookAround("look around", game);
        use             = new MovePlayerTo(game, "use");
        move            = new Move("put",game);

        moveLibroNada = new ChangeVisibility("move", true, game);
        moveLibroNada.correctMovementMessage(" did nothing.");

        ArrayList<String> listaParaRomperVentana = new ArrayList<>();
        listaParaRomperVentana.add("Martillo");
        IInterpreter requisitosRomper = new ContainsElements(playerGenerico, listaParaRomperVentana);
        romper = new ChangeVisibility("break", true, requisitosRomper, game);
        romper.correctMovementMessage("is broken.");
    }
}