package telran.appl.net;
import telran.view.*;
import telran.net.TcpClient;

import java.io.IOException;

public class Main {
    static TcpClient tcpClient;
    public static void main(String[] args) {
        Item[] items = {
            Item.of("start session", Main::startSession),
            Item.of("exit", Main::exit, true)
    };
    Menu menu = new Menu("TCP Application", items);
    menu.perform(new StandardInputOutput());
}

static void startSession(InputOutput io) {
    String host = io.readString("Enter hostname");
    int port = io.readNumberRange("Enter port", "Wrong port", 3000, 50000).intValue();
    closeTCP();
    tcpClient = new TcpClient(host, port);

    Menu menu = new Menu("Run Session",
            Item.of("Send command", Main::sendCommand), Item.ofExit());
    menu.perform(io);

}

static void sendCommand(InputOutput io) {
    String data = io.readString("Enter the data:");
    String operation = io.readString("Enter operation (reverse or length):");

    try {
        String response = tcpClient.sendAndReceive(operation, data);
        io.writeLine("Response from server: " + response);
    } catch (Exception e) {
        io.writeLine("Error: " + e.getMessage());
    }
}

static void exit(InputOutput io) {
    closeTCP();
}
static void closeTCP(){
    if (tcpClient != null) {
        try {
            tcpClient.close();
        } catch (IOException e) {
            System.err.println("Error while closing connection");
        }
    }
}
}