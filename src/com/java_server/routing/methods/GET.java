package com.java_server.routing.methods;

import com.java_server.request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseCodes;

import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class GET extends RouteMethod {
    private Request request;
    private final String okCode = "200";
    private final String notFound = "404";
    public GET(Request inRequest) {
        this.request = inRequest;
    }

    public Response getResponse() throws IOException {
        return createSuccessResponse();
    }

    private Response createSuccessResponse() throws IOException {
        return new Response(okCode, ResponseCodes.getReasonPhrase(okCode));
    }
}
