package com.java_server.response;

import com.java_server.routing.RoutesDispatcher;


/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class ResponseGenerator {
    public static Response generateResponse(String url) {
        Response response =  new Response();
        String[] routes = getRouteMethods(url);
        response.addHeader("Allow", buildAllowHeader(routes));
        return response;
    }
    private static String buildAllowHeader(String[] routes) {
        String headerValue = "";
        for(String methodName : routes) {
            if (headerValue.length() > 0) {
                headerValue += ", ";
            }
            headerValue += methodName;
        }
        return headerValue;
    }

    public static String[] getRouteMethods(String url) {
        return RoutesDispatcher.getRouteMethods(url);
    }
}
