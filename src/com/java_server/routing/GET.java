package com.java_server.routing;

import com.java_server.Request.Request;
import com.java_server.response.Response;
import com.java_server.response.ResponseCodes;
import com.java_server.response.ResponseSender;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Alex Codreanu on 11/20/14.
 */
public class GET extends RouteMethod {
    private Request request;
    private DataOutputStream outputStream;
    private final String okCode = "200";
    private final String notFound = "404";
    public GET(Request inRequest, DataOutputStream inOutStream) {
        this.request = inRequest;
        this.outputStream = inOutStream;
    }

    public void processRequest() throws IOException {
        createSuccessResponse();
    }

    private void createSuccessResponse() throws IOException {
        Response response = new Response(okCode, ResponseCodes.getReasonPhrase(okCode));
        new ResponseSender(response, outputStream).send();
    }
}
