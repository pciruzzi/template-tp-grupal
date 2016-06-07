package ar.fiuba.tdd.tp.driver;

public class UnknownPlayerException extends Exception {

    private String msg;

    public UnknownPlayerException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

}
