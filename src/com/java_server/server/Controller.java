package com.java_server.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Alex Codreanu on 11/18/14.
 */
public class Controller {
    private ServerSocket serverSocket;
    public Controller (ServerSocket inServerSocket) throws IOException{
        this.serverSocket = inServerSocket;
    }

    public void listen() throws IOException {
        System.out.println("Listening on port " + serverSocket.getLocalPort());
        while (true) {
            new ClientConnection(serverSocket.accept()).run();
        }
    }
}
