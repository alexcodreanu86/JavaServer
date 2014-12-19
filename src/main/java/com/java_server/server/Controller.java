package com.java_server.server;

import com.java_server.routing.RoutesGenerator;
import com.java_server.parser.ConfigParser;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Alex Codreanu on 11/18/14.
 */
public class Controller {
    private ServerSocket serverSocket;
    private ConfigParser configParser;
    public Controller (ServerSocket inServerSocket, ConfigParser inParser) throws IOException{
        serverSocket = inServerSocket;
        configParser = inParser;
    }

    public void listen() throws IOException {
        System.out.println("Listening on port " + serverSocket.getLocalPort());
        RoutesGenerator.generate(configParser);

        while(!serverSocket.isClosed()) {
            new ClientConnection(serverSocket.accept()).start();
        }
    }
}
