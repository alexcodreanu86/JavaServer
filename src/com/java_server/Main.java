package com.java_server;

import com.java_server.args.GlobalArguments;
import com.java_server.server.Controller;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Alex Codreanu on 11/18/14.
 */
public class Main {
    public static void main(String[] args) {
        GlobalArguments.setArgs(args);
        try {
            new Controller(new ServerSocket(GlobalArguments.getPort())).listen();
        }
        catch (IOException e) {
            System.out.println("Server.com.java_server.server.Controller: " + e);
            System.exit(1);
        }
    }
}
