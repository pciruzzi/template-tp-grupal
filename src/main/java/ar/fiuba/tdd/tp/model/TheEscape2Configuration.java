package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.time.ScheduledTimedAction;
import ar.fiuba.tdd.tp.time.SingleTimedAction;
import ar.fiuba.tdd.tp.time.TimeCommand;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.tp.Constants.NONE;
import static ar.fiuba.tdd.tp.Constants.NON_PLAYER;

@SuppressWarnings("CPD-START")

public class TheEscape2Configuration implements GameBuilder {

    private Game game;
    private Player playerGenerico;
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
    private Element doorBibliotecaAccesoBiblioteca;
    private Element doorVueltaAccesoBiblioteca;

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

    // Los elementos que se ejecutan por tiempo
//    private Element relojCucu;
    private Player bibliotecario;

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
    private ICommand giveItem;

    private TimeCommand despertarBibliotecario;
    private TimeCommand enojarBibliotecario;
    private TimeCommand moverBibliotecario;

    private MoveRandom moveRandom;

    public TheEscape2Configuration() {
        this.despertarBibliotecario = new SingleTimedAction(9999, "despertar Bibliotecario");
        this.enojarBibliotecario    = new SingleTimedAction(9998, "enojar Bibliotecario");
        this.moverBibliotecario     = null;

    }

    public TheEscape2Configuration(TimeCommand despertarBibliotecario, TimeCommand enojarBibliotecario,
                                   TimeCommand moverBibliotecario) {

        this.despertarBibliotecario = despertarBibliotecario;
        this.enojarBibliotecario    = enojarBibliotecario;
        this.moverBibliotecario     = moverBibliotecario;
    }

    @Override
    public Game build() {
        game = new Game("The Escape 2");
        game.setDescription("You're locked in a house; try to escape without die trying");
        maxPlayers = 4;
        game.setMaxPlayers(maxPlayers);
        players = new ArrayList<>();

        initializeRooms();
        initializeElements();
        initializeDoors();
        createICommands();
        createPlayer();
        createRooms();
        createLastRoomAndCondicionesDeMorir();
//        createTimeEvents();

        setHelpCommand();
        setExitCommand();

        game.setInitialPosition(pasillo);

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

//    private void createTimeEvents() {
//        ICommand cucu = new PrintMessage("sonar","CUCU... CUCU...");
//        relojCucu.addCommand(cucu);
//        relojCucu.changeState("visible", true);
//        pasillo.addElement(relojCucu);
//        TimeCommand cucuClock = new ScheduledTimedAction(10000,"sonar Reloj");
//        game.addTimeCommand(cucuClock);
//    }

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

//        relojCucu = new Player(NON_PLAYER);
        setMovementOfBibliotecario();
    }

    private void setMovementOfBibliotecario() {
        bibliotecario = new Player(NON_PLAYER);
        bibliotecario.setName("Bibliotecario");

        if ( moverBibliotecario == null ) {
            moverBibliotecario = new ScheduledTimedAction(6000, "move Bibliotecario");
            moveRandom = new MoveRandom("move");
//            moveRandom.addProhibitedRoom(biblioteca);
//            moveRandom.addProhibitedRoom(pasillo);
        } else {
            moveRandom = new MockedMoveRandom("move", biblioteca);
        }
        moveRandom.auxiliarMessage("El Bibliotecario se desperto y esta enfurecido!!!\n");

        ITimeCommand despertar = new ChangeState("despertar", "dormido", false);
        despertar.auxiliarMessage(" se ha despertado");
        ITimeCommand enojar = new ChangeState("enojar", "enojado", true);
        enojar.correctMovementMessage("se ha enojado");

        List<TimeCommand> timedActions = setTimeCommandsBibliotecario(despertar, enojar);

        IInterpreter startTimer = new HasValueState(bibliotecario, "dormido", true);
        giveItem        = new MoveFromPlayer("give Licor", game, "Botella", startTimer, timedActions );
        giveItem.correctMovementMessage("");
        giveItem.incorrectMovementMessage("Hic!");
        giveItem.auxiliarMessage("");

        game.addTimeElement(bibliotecario);
    }

    private List<TimeCommand> setTimeCommandsBibliotecario(ITimeCommand despertar, ITimeCommand enojar) {
        bibliotecario.addTimeCommand(despertar);
        bibliotecario.addTimeCommand(enojar);
        bibliotecario.addTimeCommand(moveRandom);

        moverBibliotecario.setStart(false);
        despertarBibliotecario.setStart(false);
        enojarBibliotecario.setStart(false);

        ArrayList<TimeCommand> timedActions = new ArrayList<>();
        timedActions.add(despertarBibliotecario);
        timedActions.add(enojarBibliotecario);
        timedActions.add(moverBibliotecario);

        game.addTimeCommand(moverBibliotecario);
        game.addTimeCommand(despertarBibliotecario);
        game.addTimeCommand(enojarBibliotecario);
        return timedActions;
    }

    private void initializeFirstGroupOfElements() {
        playerGenerico = new Player(NONE);
        fotoDesconocida = new Element("FotoDesconocida");
        martillo = new Element("Martillo");
        destornillador1 = new Element("Destornillador 1");
        destornillador2 = new Element("Destornillador 2");
        botellaLicor = new Element("Botella");
        configurarBotellaLicor();
        vasoUno = new Element("Vaso1");
        vasoDos = new Element("Vaso2");
        initializeBooks();
    }

    private void configurarBotellaLicor() {
        State stateBotella = new State("dormido", true, false);
        stateBotella.setEffectMessage("El Bibliotecario se quedo dormido de la borrachera.");
        botellaLicor.setStateToAffect(stateBotella);
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
        doorVueltaAccesoBiblioteca = new Element("BibliotecaAcceso");
        doorVueltaAccesoBiblioteca.addState(new State("visible", true, false));
        bibliotecario.setPlayerPosition(accesoBiblioteca);

        doorBiblioteca = new Element("Biblioteca");
        doorBibliotecaBibliotecario = new Element("Bibliotecario");
        doorSotano = new Element("Sotano");
        pasajeAfuera = new Element("Afuera");
        doorSalon1.changeState("visible", true);
        doorSalon2.changeState("visible", true);
        doorSalon3.changeState("visible", true);
        doorAccesoABibliotecario.changeState("visible", true);
        doorBibliotecaAccesoBiblioteca = new Element("doorBiblioteca");
    }

    private void createPlayer() {
        playerGenerico.setCapacity(4);
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
        ICommand moverCuadro = new ChangeVisibility("move", true, game);
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
        IInterpreter winCondition = new ContainsPlayer(lastRoom, winConditionArray);

        for (Player player : players) {
            player.setWinInterpreter(winCondition);
            IInterpreter losingInterpreter = createLosingInterpreter();
            player.setLosingInterpreter(losingInterpreter);
        }
    }

    private IInterpreter createLosingInterpreter() {
        ArrayList<String> playerEnSotanoAbajo = new ArrayList<>();
        playerEnSotanoAbajo.add("player");
        IInterpreter estasEnSotanoAbajo = new ContainsPlayer(sotanoAbajo, playerEnSotanoAbajo);

        ArrayList<String> playerNoTieneMartillo = new ArrayList<>();
        playerNoTieneMartillo.add("Martillo");
        IInterpreter noTenesMartillo = new DoesNotContainElements(playerGenerico, playerNoTieneMartillo);

        IInterpreter playerNoTieneMartilloYEstaEnSotanoAbajo = new AndExpression(estasEnSotanoAbajo, noTenesMartillo);

        IInterpreter sameRoomBibliotecarioAndPlayer = new ElementsInSameContainer(playerGenerico, bibliotecario, game);
        IInterpreter bibliotecarioEnojado = new HasValueState(bibliotecario, "enojado", true);
        IInterpreter bibliotecarioDespierto = new HasValueState(bibliotecario, "dormido", false);

        IInterpreter bibliotecarioDespiertoYEnojado = new AndExpression(bibliotecarioDespierto, bibliotecarioEnojado);
        IInterpreter sameRoomAndBibliotecarioEnojado = new AndExpression(sameRoomBibliotecarioAndPlayer,
                bibliotecarioDespiertoYEnojado);

        addRoomsToGame();

        ArrayList<String> playerEstaEnCuartoDeLaMuerte = new ArrayList<>();
        playerEstaEnCuartoDeLaMuerte.add("player");
        IInterpreter estasEnCuartoDeLaMuerte = new ContainsPlayer(cuartoDeLaMuerte, playerEstaEnCuartoDeLaMuerte);
        IInterpreter escapeOneDeaths = new OrExpression(estasEnCuartoDeLaMuerte, playerNoTieneMartilloYEstaEnSotanoAbajo);

        return new OrExpression(escapeOneDeaths, sameRoomAndBibliotecarioEnojado);
    }

    // Agrega los cuartos por donde puede encontrarse el bibliotecario con el player y hacer que este pierda
    private void addRoomsToGame() {
        game.addContainer(salonUno);
        game.addContainer(salonDos);
        game.addContainer(salonTres);
        game.addContainer(biblioteca);
        game.addContainer(accesoBiblioteca);
        game.addContainer(pasillo);
    }

    private void createBibliotecario() {
        accesoBiblioteca.addCommand(lookAround);

        doorToPasillo.changeState("visible", true);
        doorToPasillo.addCommand(openDoor);
        doorToPasillo.setObjectiveElement(pasillo);

        setCommandsBibliotecario();

        doorBibliotecaAccesoBiblioteca.changeState("visible", true);
        doorBibliotecaAccesoBiblioteca.setObjectiveElement(biblioteca);

        setOpenWhenSleeped();

        accesoBiblioteca.addElement(doorToPasillo);
        doorAccesoABibliotecario.setObjectiveElement(accesoBiblioteca);

        setBibliotecarioCondition();

        doorBibliotecario.changeState("visible", true);
        doorBibliotecario.addCommand(question);

        accesoBiblioteca.addElement(doorBibliotecario);
        accesoBiblioteca.addElement(bibliotecario);
        accesoBiblioteca.addElement(doorBibliotecaAccesoBiblioteca);
    }

    private void setCommandsBibliotecario() {
        bibliotecario.changeState("visible", true);
        bibliotecario.addState(new State("dormido", false, false));
        bibliotecario.addState(new State("enojado", false, false));
        bibliotecario.addCommand(giveItem);

        bibliotecario.addCommand(question);
        bibliotecario.setObjectiveElement(accesoBibliotecaBis);
    }

    private void setOpenWhenSleeped() {
        IInterpreter sleepedBibliotecario = new HasValueState(bibliotecario, "dormido", true);
        sleepedBibliotecario.setFailMessage("You can't cross because bibliotecario is angry with you!");
        ICommand openDoorSleeped = new MovePlayerTo(game, sleepedBibliotecario, "goto");
        openDoorSleeped.incorrectMovementMessage("You can't cross");
        openDoorSleeped.correctMovementMessage("You have Crossed to the Biblioteca");
        doorBibliotecaAccesoBiblioteca.addCommand(openDoorSleeped);
    }

    private void setBibliotecarioCondition() {
        ArrayList<String> tieneCredencial = new ArrayList<>();
        tieneCredencial.add("Credencial");
        IInterpreter playerWithCredential = new ContainsElements(playerGenerico, tieneCredencial);

        ArrayList<String> tieneFotoBuena = new ArrayList<>();
        tieneFotoBuena.add("Foto");
        IInterpreter credencialBuena = new ContainsElements(credencial, tieneFotoBuena);

        IInterpreter credencialConFoto = new AndExpression(playerWithCredential, credencialBuena);
        credencialBuena.setFailMessage("No podes pasar sin una credencial valida!!");
        playerWithCredential.setFailMessage("No podes pasar sin una credencial valida!!");

//        ArrayList<String> tieneLicor = new ArrayList<>();
//        tieneLicor.add("Botella");
//        IInterpreter conLicor = new ContainsElements(playerGenerico, tieneLicor);

//        IInterpreter puedePasar = new OrExpression(conLicor, credencialConFoto);
//        puedePasar.setFailMessage("No podes pasar y me voy a acordar de tu cara!");

        pasarBibliotecario = new MovePlayerTo(game, credencialConFoto,"show credencial");
        bibliotecario.addCommand(pasarBibliotecario);
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

    @SuppressWarnings("CPD-END")

    private void addElementsToBiblioteca() {
        biblioteca.addElement(doorBibliotecaBibliotecario);
        biblioteca.addElement(doorVueltaAccesoBiblioteca);
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

        doorVueltaAccesoBiblioteca.setObjectiveElement(accesoBiblioteca);
        doorVueltaAccesoBiblioteca.addCommand(openDoor);
        doorVueltaAccesoBiblioteca.addCommand(question);
    }
}