package com.java_server.request;

import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;

import java.util.Arrays;

/**
 * Created by Alex Codreanu on 11/21/14.
 */

public class RequestValidator {
    private Request request;
    private Route route;
    public RequestValidator(Request inRequest) {
        this.request = inRequest;
        this.route = RoutesDispatcher.getRoute(request.getUrl());
    }

    public boolean isValidRequest() {
        return this.route != null;
    }

    public boolean isValidMethod() {
        if (route != null && route.getMethods() != null) {
            return -1 < Arrays.asList(route.getMethods()).indexOf(request.getMethod());
        } else {
            return false;
        }
    }
}
