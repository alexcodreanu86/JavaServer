package com.java_server.response;

import com.java_server.auth.Authenticator;
import com.java_server.request.Request;
import com.java_server.request.RequestValidator;
import com.java_server.routing.methods.RouteMethod;
import com.java_server.routing.methods.RouteMethodFactory;
import com.java_server.routing.RoutesDispatcher;

import java.io.IOException;


/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class ResponseGenerator {
    public static Response generate(Request request) {
        RequestValidator validator = new RequestValidator(request);
        if (validator.isValidUrl()) {
            return validatedRequest(request, validator);
        } else {
            return ResponseFactory.NotFound();
        }
    }

    private static Response validatedRequest(Request request, RequestValidator validator) {
        try {
            if (new Authenticator(request).isAuthorized()) {
                return validResponse(request, validator);
            } else {
                return unauthorizedResponse();
            }
        }
        catch (IOException e) {
            return ResponseFactory.NotFound();
        }
    }

    private static Response validResponse(Request request, RequestValidator validator) throws IOException {
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request, validator);
        Response response = method.getResponse();
        addOptionsToHeaders(response, request);
        return response;
    }

    private static Response unauthorizedResponse() {
        Response response = ResponseFactory.Unauthorized();
        response.addToBody("Authentication required".getBytes());
        return response;
    }

    private static void addOptionsToHeaders(Response response, Request request) {
        String[] routeMethods = getRouteMethods(request.getUrl());
        response.addHeader("Allow", buildAllowHeader(routeMethods));
    }

    private static String buildAllowHeader(String[] routes) {
        String headerValue = "";
        for(String methodName : routes) {
            if (headerValue.length() > 0) {
                headerValue += ",";
            }
            headerValue += methodName;
        }
        return headerValue;
    }

    private static String[] getRouteMethods(String url) {
        return RoutesDispatcher.getRoute(url).getMethods();
    }
}
