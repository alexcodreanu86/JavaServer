package com.java_server.server;

import com.java_server.routing.Router;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Alex Codreanu on 11/18/14.
 */
public class ClientConnection extends Thread {
    protected Socket clientConn;
    private BufferedReader reader;
    private DataOutputStream writer;
    public ClientConnection(Socket connection) throws IOException {
        this.clientConn = connection;
        this.reader = new BufferedReader(new InputStreamReader(clientConn.getInputStream()));
        this.writer = new DataOutputStream(clientConn.getOutputStream());
    };

    public void run() {
        Router router = new Router(reader, writer, clientConn);
        router.processRequest();
    }
}
