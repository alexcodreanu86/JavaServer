package com.java_server.utils;

import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;

/**
 * Created by Alex Codreanu on 11/29/14.
 */
public class Logger {
    public static void log(String logLine) {
        Route logRoute = RoutesDispatcher.getRoute("/logs");
        byte[] newData = ArrayJoiner.join(logRoute.getData(), (logLine + "\r\n").getBytes());
        logRoute.setData(newData);
    }
}
