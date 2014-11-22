package com.java_server.routing;

import com.java_server.Request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseCodes;
import com.java_server.response.ResponseSender;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/21/14.
 */
public class OPTIONS extends RouteMethod {
    private Request request;
    private DataOutputStream outputStream;
    private final String okCode = "200";
    private final String notFound = "404";

    public OPTIONS(Request inRequest, DataOutputStream inOStream) {
        this.request = inRequest;
        this.outputStream = inOStream;
    }
    public void processRequest() throws IOException {
        String[] routes = getRouteOptions();
        Response response;
        if (routes != null) {
            response = createResponseWithRoutes(routes);
        } else {
            response = createFailResponse();
        }
        new ResponseSender(response, outputStream).send();
    }

    private Response createResponseWithRoutes(String[] routes) throws IOException {
        Response response = new Response(okCode, ResponseCodes.getReasonPhrase(okCode));
        response.addHeader("Allow", buildAllowHeader(routes));
        return response;
    }

    private String buildAllowHeader(String[] routes) {
        String headerValue = "";
        for(String methodName : routes) {
            if (headerValue.length() > 0) {
               headerValue += ", ";
            }
            headerValue += methodName;
        }
        return headerValue;
    }

    private Response createFailResponse() throws IOException {
        return new Response(notFound, ResponseCodes.getReasonPhrase(notFound));
    }

    public String[] getRouteOptions() {
        return RoutesDispatcher.getRouteMethods(request.getUrl());
    }
}
