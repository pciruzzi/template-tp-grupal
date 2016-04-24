package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.Writer;
import ar.fiuba.tdd.tp.connection.TCPInformation;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();
        Writer writer = new Console();
        TCPInformation tcpInfo = client.readServerIPAndPort();
        try {
            client.connect(tcpInfo);
            writer.write("Connection established! You can start playing by writing...");
            client.playGame();
        } catch (Exception e) {
            writer.writeError("Unable to connect to host " + tcpInfo.getIPaddress() + ":" + tcpInfo.getPort());
        }
    }
}
