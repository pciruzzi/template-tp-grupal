package ar.fiuba.tdd.tp.connection;

public class TCPInformation {

    private String ipAddress;
    private int port;

    public TCPInformation(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getIPaddress() {
        return ipAddress;
    }
}
