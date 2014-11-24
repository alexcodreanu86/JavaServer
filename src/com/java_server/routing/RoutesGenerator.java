package com.java_server.routing;

import com.java_server.args.GlobalArguments;

import java.io.File;

/**
 * Created by Alex Codreanu on 11/23/14.
 */
public class RoutesGenerator {
    public static void generate() {
        generateMethodOptions();
        generateDirectoryContents();
        generateFormPath();

    }

    private static void generateMethodOptions() {
        Route route = new Route("/method_options",
                                new String[] {"GET", "HEAD", "POST", "OPTIONS", "PUT"},
                                new byte[0]);
        RoutesDispatcher.addRoute(route);
    }

    private static void generateFormPath() {
        Route route = new Route("/form",
                new String[] {"GET", "POST", "PUT", "DELETE"},
                new byte[0]);
        RoutesDispatcher.addRoute(route);
    }

    private static void generateDirectoryContents() {
        File directory = new File(GlobalArguments.getRootDirectory());
        DirectoryRoutesLoader.loadDirectoryContents(directory);
    }
}
