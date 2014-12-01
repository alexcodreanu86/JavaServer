package com.java_server.routing;

import com.java_server.request.Request;
import com.java_server.request.RequestGenerator;
import com.java_server.response.Response;
import com.java_server.response.ResponseGenerator;
import com.java_server.response.ResponseSender;
import com.java_server.server.Logger;

import java.io.*;

/**
 * Created by Alex Codreanu on 11/19/14.
 */
public class Router {
    private BufferedReader reader;
    private OutputStream writer;
    private Closeable connection;

    public Router(BufferedReader inReader, OutputStream inWriter, Closeable inConnection) {
        this.reader = inReader;
        this.writer = inWriter;
        this.connection = inConnection;
    }

    public void processRequest() {
        try {
            Request request = generateRequest();
            this.logRequest(request);
            this.respond(request);
        }
        catch (IOException e) {
            closeConnection();
        }
    }

    private void logRequest(Request request) {
        Logger.log(request.getRequestLine());
    }

    private void respond(Request request) throws IOException {
        Response response = ResponseGenerator.generate(request);
        ResponseSender sender = new ResponseSender(response, writer);
        sender.send();

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
