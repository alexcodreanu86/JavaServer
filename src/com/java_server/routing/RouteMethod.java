package com.java_server.routing;


import com.java_server.response.Response;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public abstract class RouteMethod {
    public abstract Response getResponse() throws IOException;
}
