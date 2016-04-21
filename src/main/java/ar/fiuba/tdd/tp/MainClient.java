package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.client.*;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("This is just a tp project");
        Client client = new Client();
        TCPInformation tcpInfo = client.readServerIPAndPort();
        try {
            client.connect(tcpInfo);
            client.playGame();
        } catch (Exception e) {
            System.err.println("Unable to connect to host " + tcpInfo.getIPaddress() + ":" + tcpInfo.getPort());
        }
    }
}
