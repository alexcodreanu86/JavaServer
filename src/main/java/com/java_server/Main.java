package com.java_server;

import com.java_server.args.GlobalArguments;
import com.java_server.server.Controller;
import com.java_server.utils.ConfigParser;
import com.java_server.utils.ConfigParserFactory;

import java.net.ServerSocket;

/**
 * Created by Alex Codreanu on 11/18/14.
 */
public class Main {
    public static void main(String[] args) {
        GlobalArguments.setArgs(args);
        ConfigParserFactory parserFactory = new ConfigParserFactory();
        try {
            ConfigParser parser = parserFactory.generate();
            new Controller(new ServerSocket(GlobalArguments.getPort())).listen();
        }
        catch (Exception e) {
            System.out.println("Server.com.java_server.server.Controller: " + e);
            System.exit(1);
        }
    }
}
