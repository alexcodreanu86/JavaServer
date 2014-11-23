package com.java_server.request;

import com.java_server.request.Request;
import com.java_server.routing.RoutesDispatcher;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class RequestValidator {
    private Request request;
    public RequestValidator(Request inRequest) {
       this.request = inRequest;
    }

    public boolean isValidRequest() {
        String[] routes = RoutesDispatcher.getRouteMethods(request.getUrl());
        return routes != null && isValidMethod(routes);
    }

    private boolean isValidMethod(String[] routes) {
        return -1 < Arrays.asList(routes).indexOf(request.getMethod());
    }
}
