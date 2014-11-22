package com.java_server.routing;

import com.java_server.Request.Request;
import com.java_server.Request.RequestGenerator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Alex Codreanu on 11/19/14.
 */
public class Router {
    private BufferedReader reader;
    private DataOutputStream writer;
    private Socket connection;
    private RouteMethodFactory routeMethodFactory = new RouteMethodFactory();

    public Router(BufferedReader inReader, DataOutputStream inWriter, Socket inConnection) {
        this.reader = inReader;
        this.writer = inWriter;
        this.connection = inConnection;
    }

    public void processRequest() {
        try {
            this.respond(generateRequest());
        }
        catch (IOException e) {
            //TODO invalid request
            closeConnection();
        }
    }

    private void respond(Request request) throws IOException {
        RouteMethod routeMethod = routeMethodFactory.buildRouteMethod(request, writer);
        routeMethod.processRequest();
        closeConnection();
    }

    private void closeConnection() {
        try {
            connection.close();
        }
        catch (IOException e) {
            System.exit(1);
        }
    }

    private Request generateRequest() throws IOException {
        return RequestGenerator.generate(reader);
    }
}

