package telran.appl.net;
import telran.net.*;

public class Main {
    private static final int PORT = 4000;

    public static void main(String[] args) {
        Protocol protocol = new ReverseLengthProtocol();
        TcpServer server = new TcpServer(protocol, PORT);
        new Thread(server).start();
    }
}