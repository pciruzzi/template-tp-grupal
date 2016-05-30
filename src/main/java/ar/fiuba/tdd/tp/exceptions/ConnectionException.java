package ar.fiuba.tdd.tp.exceptions;

public abstract class ConnectionException extends Exception {

    private String msg;

    public ConnectionException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
