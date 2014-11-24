package com.java_server.server;

import com.java_server.args.GlobalArguments;
import com.java_server.routing.DirectoryRoutesLoader;
import com.java_server.routing.RoutesGenerator;

import java.io.File;
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
        RoutesGenerator.generate();

        while (true) {
            new ClientConnection(serverSocket.accept()).run();
        }
    }
}
