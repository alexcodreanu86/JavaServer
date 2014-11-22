package com.java_server.routing;


import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public abstract class RouteMethod {
    public abstract void processRequest() throws IOException;
}
