package com.java_server.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Alex Codreanu on 11/18/14.
 */
public class Controller {
    private int port;
    private ServerSocket serverSocket;
    public Controller (int inPort) throws IOException{
        port = inPort;
        serverSocket = new ServerSocket(port);
    }

    public void listen() throws IOException {
        System.out.println("Listening on port " + port);
        while (true) {
            new ClientConnection(serverSocket.accept()).run();
        }
    }
}
