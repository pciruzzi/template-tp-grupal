package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;

import ar.fiuba.tdd.tp.icommand.ChangeVisibility;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.MovePlayerTo;
import ar.fiuba.tdd.tp.interpreter.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@SuppressWarnings("CPD-START")

public class TheEscapeConfiguration implements GameBuilder {

    private Game game;
    private Element player;

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

    // Los elementos levantables
    private Element credencial;
    private Element libroViejo;
    private Element libroUno;
    private Element libroDos;
    private Element botellaLicor;
    private Element vasoUno;
    private Element vasoDos;
    private Element fotoBuena;
    private Element lapicera;
    private Element fotoDesconocida;
    private Element martillo;
    private Element destornillador1;
    private Element destornillador2;

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
    private ICommand openContainer;
    private ICommand closeContainer;
    private ICommand question;
    private ICommand lookAround;
    private ICommand use;
    private ICommand romper;
    private ICommand pasarBibliotecario;
    private ICommand move;

    @Override
    public Game build() {
        game = new Game("The Escape");

        initializeElements();
        initializeRooms();
        initializeDoors();
        createICommands();
        createPlayer();
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

        game.setPlayer(player);
        game.setPlayerPosition(pasillo);

        return game;
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
        lastRoom = new Element("Afuera");
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
        player = new Element("player");
        fotoBuena = new Element("Foto");
        fotoDesconocida = new Element("FotoDesconocida");
        lapicera = new Element("Lapicera");
        martillo = new Element("Martillo");
        destornillador1 = new Element("Destornillador 1");
        destornillador2 = new Element("Destornillador 2");
        botellaLicor = new Element("Botella");
        vasoUno = new Element("Vaso1");
        vasoDos = new Element("Vaso2");
        libroViejo = new Element("LibroViejo");
        libroUno = new Element("libro quimica");
        libroDos = new Element("libro fisica");
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
        doorSalon1.setState(true);
        doorSalon2.setState(true);
        doorSalon3.setState(true);
        doorAccesoABibliotecario.setState(true);
    }

    private void createPlayer() {
        player.setCapacity(4);

        fotoBuena.addCommand(drop);
        fotoBuena.addCommand(move);
        fotoBuena.addCommand(pick);

        lapicera.addCommand(drop);
        lapicera.addCommand(pick);

        player.addElement(fotoBuena);
        player.addElement(lapicera);
    }

    private void createRoomTwo() {
        salonDos.addCommand(lookAround);
        doorSalon2.setObjectiveElement(salonDos);

        configureElementsRoomTwo();

        doorToPasillo.setObjectiveElement(pasillo);
        doorToPasillo.addCommand(openDoor);
        doorToPasillo.addCommand(question);

        addElementsToRoomTwo();}

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
        martillo.setState(true);

        destornillador1.addCommand(pick);
        destornillador1.addCommand(drop);
        destornillador1.addCommand(question);
        destornillador1.setState(true);

        destornillador2.addCommand(pick);
        destornillador2.addCommand(drop);
        destornillador2.addCommand(question);
        destornillador2.setState(true);
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
        mesa.addCommand(question);
        sillaUno.addCommand(question);
        sillaDos.addCommand(question);
        vasoUno.addCommand(question);
        vasoDos.addCommand(question);
        cuadroBarco.addCommand(question);
        cuadroTren.addCommand(question);
        botellaLicor.addCommand(question);
        cajaFuerte.addCommand(question);

        // Abrir caja fuerte con llave
        setConditionsForCajaFuerte();

        // Abrir puerta para hall
        doorToPasillo.setObjectiveElement(pasillo);
        ICommand abrirPuerta = new MovePlayerTo(game, "goto");
        doorToPasillo.addCommand(abrirPuerta);
        doorToPasillo.setState(true);

        // Agrego los elementos al salon
        setElementsToSalon1();
    }

    private void setConditionsForCajaFuerte() {
        ArrayList<String> condicionLlave = new ArrayList<>();
        condicionLlave.add("Llave");
        IInterpreter condicionCaja = new ContainsElements(player, condicionLlave);
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
        botellaLicor.setState(true);
        vasoUno.setState(true);
        vasoDos.setState(true);
        cuadroBarco.setState(true);
        cuadroTren.setState(true);
        mesa.setState(true);
        sillaDos.setState(true);
        sillaUno.setState(true);
    }

    private void createSotano() {
        sotano.addCommand(lookAround);

        barandaSotano.setState(true);
        escalera.setState(true);

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
        barandaSotanoAbajo.setState(true);


        sotanoAbajo.addElement(barandaSotanoAbajo);
        sotanoAbajo.addElement(escalera);

//        escalera.setState(true);
        barandaSotanoAbajo.setState(true);

//        escalera.addCommand(use);
//        escalera.setObjectiveElement(cuartoDeLaMuerte);

        ventana.setObjectiveElement(lastRoom);
        ventana.addCommand(romper);
        ventana.addCommand(question);
        ventana.setState(true);
        sotanoAbajo.addElement(ventana);
    }

    private void createLastRoomAndCondicionesDeMorir() {

        lastRoom.addCommand(lookAround);
        ArrayList<String> winConditionArray = new ArrayList<>();
        winConditionArray.add("player");
        IInterpreter winCondition = new ContainsElements(lastRoom, winConditionArray);

        game.setWinInterpreter(winCondition);

        IInterpreter losingInterpreter = createLosingInterpreter();
        //Aca estan las condiciones de perder
        game.setLosingInterpreter(losingInterpreter);
    }

    private IInterpreter createLosingInterpreter() {
        ArrayList<String> playerEstaEnCuartoDeLaMuerte = new ArrayList<>();
        playerEstaEnCuartoDeLaMuerte.add("player");
        IInterpreter estasEnCuartoDeLaMuerte = new ContainsElements(cuartoDeLaMuerte, playerEstaEnCuartoDeLaMuerte);

        ArrayList<String> playerEnSotanoAbajo = new ArrayList<>();
        playerEnSotanoAbajo.add("player");
        IInterpreter estasEnSotanoAbajo = new ContainsElements(sotanoAbajo, playerEnSotanoAbajo);

        ArrayList<String> playerNoTieneMartillo = new ArrayList<>();
        playerNoTieneMartillo.add("Martillo");
        IInterpreter noTenesMartillo = new DoesNotContainElements(player, playerNoTieneMartillo);

        IInterpreter playerNoTieneMartilloYEstaEnSotanoAbajo = new AndExpression(estasEnSotanoAbajo, noTenesMartillo);

        return new OrExpression(estasEnCuartoDeLaMuerte, playerNoTieneMartilloYEstaEnSotanoAbajo);
    }

    private void createBibliotecario() {
        accesoBiblioteca.addCommand(lookAround);

        doorToPasillo.setState(true);
        doorToPasillo.addCommand(openDoor);
        doorToPasillo.setObjectiveElement(pasillo);

        accesoBiblioteca.addElement(doorToPasillo);
        doorAccesoABibliotecario.setObjectiveElement(accesoBiblioteca);

        setBibliotecarioCondition();

        doorBibliotecario.setState(true);
        doorBibliotecario.addCommand(pasarBibliotecario);
        doorBibliotecario.addCommand(question);

        accesoBiblioteca.addElement(doorBibliotecario);

    }

    private void setBibliotecarioCondition() {
        ArrayList<String> tieneCredencial = new ArrayList<>();
        tieneCredencial.add("Credencial");

        IInterpreter playerWithCredential = new ContainsElements(player, tieneCredencial);

        ArrayList<String> tieneFotoBuena = new ArrayList<>();
        tieneFotoBuena.add("Foto");

        IInterpreter credencialBuena = new ContainsElements(credencial, tieneFotoBuena);

        IInterpreter credencialConFoto = new AndExpression(playerWithCredential, credencialBuena);

        ArrayList<String> tieneLicor = new ArrayList<>();
        tieneLicor.add("Botella");

        IInterpreter conLicor = new ContainsElements(player, tieneLicor);

        IInterpreter puedePasar = new OrExpression(conLicor, credencialConFoto);

        puedePasar.setFailMessage("No podes pasar y me voy a acordar de tu cara!");

        pasarBibliotecario = new MovePlayerTo(game, puedePasar,"show credencial");
    }

    private void createAccesoABibliotecaBis() {
        doorBibliotecario.setObjectiveElement(accesoBibliotecaBis);
        doorBiblioteca.setState(true);
        doorBiblioteca.addCommand(openDoor);
        doorBiblioteca.addCommand(question);

        accesoBibliotecaBis.addElement(doorBiblioteca);
        accesoBibliotecaBis.addElement(doorToPasillo);

        accesoBibliotecaBis.addCommand(lookAround);
        accesoBiblioteca.addCommand(lookAround);
    }

    private void createBiblioteca() {
        doorBiblioteca.setObjectiveElement(biblioteca);

        doorBibliotecaBibliotecario.setState(true);
        doorBibliotecaBibliotecario.setObjectiveElement(accesoBibliotecaBis);
        doorBibliotecaBibliotecario.addCommand(openDoor);
        doorBibliotecaBibliotecario.addCommand(question);

        libroViejo.setState(true);
        libroUno.setState(true);
        libroDos.setState(true);

        ICommand moveLibro = new ChangeVisibility("move", true, game);
        moveLibro.correctMovementMessage(" opened a secret passage to the basement!");

        libroViejo.addCommand(moveLibro);
        libroViejo.addCommand(question);
        libroUno.addCommand(question);
        libroDos.addCommand(question);

        libroViejo.addElement(doorSotano);
        doorSotano.addCommand(openDoor);

        doorSotano.setObjectiveElement(sotano);

        biblioteca.addElement(libroViejo);
        biblioteca.addElement(libroUno);
        biblioteca.addElement(libroDos);
        biblioteca.addElement(doorBibliotecaBibliotecario);

        biblioteca.addCommand(lookAround);
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
        llave.setState(true);
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
        openContainer   = new ChangeVisibility("open", true, game);
        closeContainer  = new ChangeVisibility("close", false, game);
        question        = new Question("ask");
        lookAround      = new LookAround("look around", game);
        use             = new MovePlayerTo(game, "use");
        move            = new Move("put",game);

        ArrayList<String> listaParaRomperVentana = new ArrayList<>();
        listaParaRomperVentana.add("Martillo");
        IInterpreter requisitosRomper = new ContainsElements(player, listaParaRomperVentana);
        romper = new MovePlayerTo(game,requisitosRomper, "break");
    }
}
