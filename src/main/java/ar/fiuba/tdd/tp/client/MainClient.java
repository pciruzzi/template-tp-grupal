package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.connection.TCPInformation;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();
        TCPInformation tcpInfo = client.readServerIPAndPort();
        try {
            client.connect(tcpInfo);
            System.out.println("Connection established! You can start playing by writing...");
            client.playGame();
        } catch (Exception e) {
            System.err.println("Unable to connect to host " + tcpInfo.getIPaddress() + ":" + tcpInfo.getPort());
        }
    }
}
