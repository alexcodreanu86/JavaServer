package com.java_server.routing;

import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class RoutesDispatcher {
    private static Hashtable<String, String[]> RoutesMethods =
            new Hashtable<String, String[]>(){{
                put("/", new String[]{"GET"});
                put("/form", new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS"});
                put("/method_options", new String[]{"GET", "HEAD", "POST", "OPTIONS", "PUT"});
            }};

    public static String[] getRouteMethods(String route) {
        return RoutesMethods.get(route);
    }

    public static void addRouteWithMethods(String route, String[] methods) {
        RoutesMethods.put(route, methods);
    }

}
