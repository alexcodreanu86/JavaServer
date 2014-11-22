package com.java_server;

import com.java_server.args.ArgumentProcessor;
import com.java_server.server.Controller;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/18/14.
 */
public class Main {
    static final String defaultPort = "5000";
    static final String defaultDirectoryPath = "";
    public static void main(String[] args) {
        ArgumentProcessor argProcessor = new ArgumentProcessor(args, defaultPort, defaultDirectoryPath);
        int port = argProcessor.getPort();

        System.out.println("Starting com.server.server on port " + port);
        try {
            new Controller(port).listen();
        }
        catch (IOException e) { System.out.println("Server.com.java_server.server.Controller: " + e); }
    }
}
