package com.java_server.request;

import com.java_server.routing.RoutesDispatcher;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/21/14.
 */

public class RequestValidator {
    private Request request;
    private String[] routes;
    public RequestValidator(Request inRequest) {
        this.request = inRequest;
        this.routes = RoutesDispatcher.getRouteMethods(request.getUrl());
    }

    public boolean isValidRequest() {
        return this.routes != null;
    }

    public boolean isValidMethod() {
        if (routes != null) {
            return -1 < Arrays.asList(routes).indexOf(request.getMethod());
        } else {
            return false;
        }
    }
}
