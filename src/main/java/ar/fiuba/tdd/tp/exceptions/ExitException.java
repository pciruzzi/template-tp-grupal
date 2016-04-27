package ar.fiuba.tdd.tp.exceptions;

public class ExitException extends Exception {

    private String msg;

    public ExitException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

}