package com.java_server.routing;

import java.util.Hashtable;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class RoutesDispatcher {
    private static Hashtable<String, Route> Routes = new Hashtable<String, Route>();

    public static void addRoute(Route route) {
        Routes.put(route.getUrl(), route);
    }

    public static Route getRoute(String routeUrl) {
        return Routes.get(routeUrl);
    }

}
