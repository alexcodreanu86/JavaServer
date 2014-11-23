package com.java_server.response;

import com.java_server.request.Request;
import com.java_server.request.RequestValidator;
import com.java_server.routing.methods.RouteMethod;
import com.java_server.routing.RouteMethodFactory;
import com.java_server.routing.RoutesDispatcher;

import java.io.IOException;


/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class ResponseGenerator {
    static String notFoundCode = "404";

    public static Response generate(Request request) {
        if (new RequestValidator(request).isValidRequest()) {
            try {
                return validResponse(request);
            }
            catch (IOException e) {
                return invalidResponse(request);
            }
        } else {
            return invalidResponse(request);
        }
    }

    private static Response validResponse (Request request) throws IOException {
        RouteMethod method = RouteMethodFactory.buildRouteMethod(request);
        Response response = method.getResponse();
        addOptionsToHeaders(response, request);
        return response;
    }

    private static void addOptionsToHeaders(Response response, Request request) {
        String[] routeMethods = getRouteMethods(request.getUrl());
        response.addHeader("Allow", buildAllowHeader(routeMethods));
    }

    private static Response invalidResponse(Request request) {
        return new Response(notFoundCode, ResponseCodes.getReasonPhrase(notFoundCode));
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
        return RoutesDispatcher.getRouteMethods(url);
    }
}
