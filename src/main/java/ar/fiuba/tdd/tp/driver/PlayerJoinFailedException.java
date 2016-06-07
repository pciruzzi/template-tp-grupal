package ar.fiuba.tdd.tp.driver;

public class PlayerJoinFailedException extends Exception {

    private String msg;

    public PlayerJoinFailedException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

}
