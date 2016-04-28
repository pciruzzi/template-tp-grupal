package ar.fiuba.tdd.tp;

public final class Constants {

    public static final char EO_MSG = (char) 13;
    public static final String ENCODING = "US-ASCII";
    public static final int PORT = 8080;

    public static final String INVALID_ACTION = "Invalid Action.";

    public static final String HANOI_STACK = "stack";
    public static final String HANOI_DISK = "disk";
    public static final String HANOI_QUESTION = "You can check top/move top.";
    public static final String HANOI_CHECKSIZE = "Size of top from stack is ";
    public static final String HANOI_SIZE = "The stack is empty.";
    public static final String HANOI_STACK_ERROR = "That's not a name of a valid " + HANOI_STACK;
    public static final String HANOI_MOVEERROR = "You can't stack a bigger disk over smaller one.";
    public static final int HANOI_AMOUNT_DISKS = 3;
    public static final int HANOI_STACKS = 3;

    public static final String EVIL_THIEF = "You can talk with thief: \"Hello\", \"Bye\".";
}
