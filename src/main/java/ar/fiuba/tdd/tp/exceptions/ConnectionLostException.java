package ar.fiuba.tdd.tp.exceptions;

public class ConnectionLostException extends ConnectionException {

    public ConnectionLostException(String msg) {
        super(msg);
    }
}