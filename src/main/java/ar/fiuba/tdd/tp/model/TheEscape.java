package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.ChangeVisibility;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.MovePlayerTo;
import ar.fiuba.tdd.tp.icommand.MoveToPlayer;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

public class TheEscape implements GameBuilder {

    @SuppressWarnings("CPD-START")
    private Game game;

    //    // Los cuartos
//    private Element roomOne;
//    private Element roomHanoi;
//    private Element roomArchaeologist;
//
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

    private Element credencial;
    private Element fotoDesconocida;

    private Element puertaAPasillo;


    @Override
    public Game build() {

        game = new Game("Temple Quest");

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
        IInterpreter condicionCaja = new ContainsElements();
        ICommand abrirCaja = new ChangeVisibility("abrir", true, )

    }
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
