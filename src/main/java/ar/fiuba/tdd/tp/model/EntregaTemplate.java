package ar.fiuba.tdd.tp.model;

public class EntregaTemplate implements GameBuilder {

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
    @Override
    public Game build() {

        game = new Game("Temple Quest");

        return game;
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
