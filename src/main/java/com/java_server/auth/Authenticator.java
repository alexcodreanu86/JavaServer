package com.java_server.auth;

import com.java_server.request.Request;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by Alex Codreanu on 11/28/14.
 */
public class Authenticator {
    private Request request;
    public Authenticator(Request inRequest) {
        this.request = inRequest;
    }

    public boolean isAuthorized() {
        return isPublicUrl() || hasValidAuthentication();
    }

    private boolean isPublicUrl() {
        Route route = RoutesDispatcher.getRoute(request.getUrl());
        return !route.requiresAuthentication();
    }

    private boolean hasValidAuthentication() {
        String[] credentials = getDecodedCredentials();
        return  credentials.length == 2 &&
                "admin".equals(credentials[0]) &&
                "hunter2".equals(credentials[1]);
    }

    private String[] getDecodedCredentials() {
        String authorization = request.getHeaders().get("Authorization");
        if (authorization != null) {
            String encodedToken = authorization.split(" ")[1];
            byte[] decodedToken = DatatypeConverter.parseBase64Binary(encodedToken);
            return new String(decodedToken).split(":");
        } else {
            return new String[] {"invalid", "request"};
        }
    }
}
