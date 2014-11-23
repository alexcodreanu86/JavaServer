package com.java_server.routing.methods;

import com.java_server.data_storage.FilesData;
import com.java_server.request.Request;
import com.java_server.request.RequestValidator;
import com.java_server.response.Response;
import com.java_server.response.ResponseCodes;
import com.java_server.routing.Route;
import com.java_server.routing.RoutesDispatcher;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class GET extends RouteMethod {
    private Request request;
    private RequestValidator validator;
    String okCode = "200";
    String notFound = "404";
    String notAllowed = "405";
    public GET(Request inRequest) {
        this.request = inRequest;
        this.validator = new RequestValidator(request);
    }

    public Response getResponse() throws IOException {
        if (validator.isValidMethod()) {
            return createAllowedResponse();
        } else {
            return notAllowedResponse();
        }
    }

    private Response createAllowedResponse() throws IOException {
        Route route = RoutesDispatcher.getRoute(request.getUrl());
        byte[] responseBody = route.getData();
        if (responseBody != null) {
            return createSuccessResponse(responseBody);
        } else {
            return createInvalidResponse();
        }
    }

    private Response createInvalidResponse() {
        return new Response(notFound, ResponseCodes.getReasonPhrase(notFound));
    }

    private Response notAllowedResponse() {
        return new Response(notAllowed, ResponseCodes.getReasonPhrase(notAllowed));
    }

    private Response createSuccessResponse(byte[] responseBody) throws IOException {
        Response response = new Response(okCode, ResponseCodes.getReasonPhrase(okCode));
        response.addHeader("Content-Type", "text/html");
        response.addToBody(responseBody);
        return response;
    }
}
