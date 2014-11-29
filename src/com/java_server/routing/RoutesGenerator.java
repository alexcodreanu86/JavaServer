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
        generateRedirectPath();
        generateParamsRoute();
        generateLogsRoute();
    }

    private static void generateLogsRoute() {
        addAuthenticatedRoute("/logs",
                            new String[] {"GET"},
                            true);
    }

    private static void generateMethodOptions() {
        addRouteWithMethods("/method_options",
                            new String[] {"GET", "HEAD", "POST", "OPTIONS", "PUT"});
    }

    private static void generateFormPath() {
        addRouteWithMethods("/form",
                            new String[] {"GET", "POST", "PUT", "DELETE"});
    }

    private static void generateParamsRoute() {
        addRouteWithMethods("/parameters", new String[]{"GET"});
    }

    private static void generateRedirectPath() {
        addRouteWithMethods("/redirect", new String[] {"GET"});
    }

    private static void addRouteWithMethods(String routeUrl, String[] methods) {
        addAuthenticatedRoute(routeUrl, methods, false);
    }

    private static void addAuthenticatedRoute(String routeUrl, String[] methods, boolean requiresAuth) {
        Route route = new Route(routeUrl,
                methods,
                new byte[0],
                requiresAuth);
        RoutesDispatcher.addRoute(route);
    }
    private static void generateDirectoryContents() {
        File directory = new File(GlobalArguments.getRootDirectory());
        DirectoryRoutesLoader.loadDirectoryContents(directory);
    }
}
